import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginScreen extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel messageLabel;

    private AuthController authController;

    public LoginScreen() {
        // Initialize the AuthController
        authController = new AuthController();

        // Frame settings
        setTitle("Inicio de Sesión");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal con fondo
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(60, 63, 65));

        // Header con título estilizado
        JLabel headerLabel = new JLabel("Bienvenido", SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        headerLabel.setForeground(new Color(187, 187, 187));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Panel de formulario con un diseño GridBag
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(60, 63, 65));
        formPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Componentes del formulario
        JLabel emailLabel = new JLabel("Correo Electrónico:");
        emailLabel.setForeground(new Color(187, 187, 187));
        emailField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordLabel.setForeground(new Color(187, 187, 187));
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBackground(new Color(98, 121, 176));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);

        // Mensaje de error o éxito
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setForeground(new Color(255, 69, 58));

        // Agregar componentes al panel de formulario
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(emailLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        formPanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(passwordLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(passwordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(loginButton, gbc);

        // Layout final
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(messageLabel, BorderLayout.SOUTH);

        // Action Listener para el botón
        loginButton.addActionListener(new LoginAction());

        // Añadir el panel principal al frame
        add(mainPanel);
        setVisible(true);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            String role = authController.authenticate(email, password);
            if (role != null) {
                messageLabel.setText("¡Inicio de sesión exitoso como " + role + "!");
                messageLabel.setForeground(new Color(76, 175, 80));
                dispose(); // Close the login screen
                if (role.equals("Administrador")) {
                    new DashboardScreen("Administrador", null);
                } else if (role.equals("Empleado")) {
                    new DashboardScreen("Empleado", null);
                } else if (role.equals("Cliente")) {
                    Cliente cliente = authController.getClientByEmail(email);
                    new DashboardScreen("Cliente", cliente);
                }
            } else {
                messageLabel.setText("Credenciales incorrectas.");
                messageLabel.setForeground(new Color(255, 69, 58));
            }
        }
    }

    public static void main(String[] args) {
        new LoginScreen();
    }
}
