package aulaall.controlador;

import aulaall.modelo.dao.UsuarioDAO;   // AJUSTAR: nombre real del DAO de usuarios
import aulaall.util.CifradoContrasenia;
import aulaall.vista.VentanaLogin;
import aulaall.vista.VentanaPrincipal;

import javax.swing.SwingUtilities;

public class LoginControlador {

    private final VentanaLogin vista;
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();   // AJUSTAR

    public LoginControlador(VentanaLogin vista) {
        this.vista = vista;
        this.vista.addLoginListener(e -> handleLogin());
    }

    private void handleLogin() {
        String correo = vista.getCorreo().trim();
        String password = vista.getPassword();

        // Criterio 1: campos vacíos
        if (correo.isEmpty() || password.isEmpty()) {
            vista.mostrarMensaje("Por favor, ingresa correo y contraseña.");
            return;
        }

        // Criterio 2: formato de correo institucional
        if (!correo.contains("@") || !correo.endsWith(".edu.co")) {
            vista.mostrarMensaje("Ingresa un correo institucional válido (*.edu.co).");
            return;
        }

        // Autenticación con BCrypt (SCRUM-56)
        if (autenticar(correo, password)) {
            abrirVentanaPrincipal();
        } else {
            vista.mostrarMensaje("Correo o contraseña incorrectos.");
        }
    }

    // AJUSTAR: usa los métodos reales de tu DAO y de CifradoContrasenia
    private boolean autenticar(String correo, String password) {
        String hashGuardado = usuarioDAO.obtenerHashPorCorreo(correo);
        if (hashGuardado == null) {
            return false;
        }
        return CifradoContrasenia.verificar(password, hashGuardado);
    }

    private void abrirVentanaPrincipal() {
        try {
            new VentanaPrincipal().setVisible(true);
            vista.dispose();
        } catch (Exception e) {
            e.printStackTrace();
            vista.mostrarMensaje("Error al cargar la pantalla principal.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaLogin ventana = new VentanaLogin();
            new LoginControlador(ventana);
            ventana.setVisible(true);
        });
    }
}