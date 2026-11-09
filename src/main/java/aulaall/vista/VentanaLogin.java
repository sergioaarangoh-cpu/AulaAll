package aulaall.vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class VentanaLogin extends JFrame {

    private static final Color FONDO = Color.decode("#f4f6f9");
    private static final Color TITULO = Color.decode("#172b4d");
    private static final Color SUBTITULO = Color.decode("#5e6c84");
    private static final Color ERROR = Color.decode("#de350b");
    private static final Color AZUL = Color.decode("#0052cc");

    private final JLabel lblMensaje = new JLabel(" ");
    private final JTextField txtCorreo = new JTextField();
    private final JPasswordField txtPassword = new JPasswordField();
    private final JButton btnLogin = new JButton("Iniciar sesión");

    public VentanaLogin() {
        super("AulaAll");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);

        JPanel root = new JPanel();
        root.setLayout(new BoxLayout(root, BoxLayout.Y_AXIS));
        root.setBackground(FONDO);
        root.setBorder(new EmptyBorder(30, 30, 30, 30));

        JLabel titulo = new JLabel("AulaAll");
        titulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        titulo.setForeground(TITULO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Inicio de Sesión");
        subtitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitulo.setForeground(SUBTITULO);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        lblMensaje.setForeground(ERROR);
        lblMensaje.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblMensaje.setVisible(false);

        root.add(titulo);
        root.add(Box.createVerticalStrut(5));
        root.add(subtitulo);
        root.add(Box.createVerticalStrut(15));
        root.add(lblMensaje);
        root.add(Box.createVerticalStrut(10));
        root.add(campo("Correo institucional:", txtCorreo));
        root.add(Box.createVerticalStrut(15));
        root.add(campo("Contraseña:", txtPassword));
        root.add(Box.createVerticalStrut(20));

        btnLogin.setBackground(AZUL);
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 13));
        btnLogin.setFocusPainted(false);
        btnLogin.setOpaque(true);
        btnLogin.setBorderPainted(false);
        btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnLogin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 38));
        root.add(btnLogin);

        getRootPane().setDefaultButton(btnLogin); // Enter inicia sesión
        setContentPane(root);
    }

    private JPanel campo(String etiqueta, JTextField campo) {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setOpaque(false);

        JLabel lbl = new JLabel(etiqueta);
        lbl.setFont(new Font("SansSerif", Font.BOLD, 12));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);

        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setPreferredSize(new Dimension(0, 35));
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 35));

        p.add(lbl);
        p.add(Box.createVerticalStrut(5));
        p.add(campo);
        p.setMaximumSize(new Dimension(Integer.MAX_VALUE, 65));
        return p;
    }

    // --- Métodos que usa el controlador ---

    public String getCorreo() {
        return txtCorreo.getText();
    }

    public String getPassword() {
        return new String(txtPassword.getPassword());
    }

    public void addLoginListener(ActionListener listener) {
        btnLogin.addActionListener(listener);
    }

    public void mostrarMensaje(String texto) {
        lblMensaje.setText(texto);
        lblMensaje.setVisible(true);
    }
}