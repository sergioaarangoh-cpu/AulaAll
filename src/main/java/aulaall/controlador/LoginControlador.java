package aulaall.controlador;

import aulaall.modelo.dao.UsuarioDAO;   // AJUSTAR: nombre real del DAO de usuarios
import aulaall.util.CifradoContrasenia;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginControlador {

    @FXML private TextField txtCorreo;
    @FXML private PasswordField txtPassword;
    @FXML private Label lblMensaje;

    private final UsuarioDAO usuarioDAO = new UsuarioDAO();   // AJUSTAR

    @FXML
    private void handleLogin(ActionEvent event) {
        String correo = txtCorreo.getText().trim();
        String password = txtPassword.getText();

        // Criterio 1: campos vacíos
        if (correo.isEmpty() || password.isEmpty()) {
            mostrarAlerta("Por favor, ingresa correo y contraseña.");
            return;
        }

        // Criterio 2: formato de correo institucional
        if (!correo.contains("@") || !correo.endsWith(".edu.co")) {
            mostrarAlerta("Ingresa un correo institucional válido (*.edu.co).");
            return;
        }

        // Autenticación con BCrypt (SCRUM-56)
        boolean loginValido = autenticar(correo, password);

        if (loginValido) {
            abrirVentanaPrincipal(event);
        } else {
            mostrarAlerta("Correo o contraseña incorrectos.");
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

    private void mostrarAlerta(String mensaje) {
        lblMensaje.setText(mensaje);
        lblMensaje.setVisible(true);
    }

    private void abrirVentanaPrincipal(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/aulaall/vista/home-view.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("AulaAll - Panel Principal");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            mostrarAlerta("Error al cargar la pantalla principal.");
        }
    }
}