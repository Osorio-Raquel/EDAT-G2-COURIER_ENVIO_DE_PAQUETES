import javax.swing.*;
import java.awt.*;

public class DashboardScreen extends JFrame {
    private String role;
    private Cliente cliente; // Instancia de Cliente para mostrar datos personalizados

    public DashboardScreen(String role, Cliente cliente) {
        this.role = role;
        this.cliente = cliente;

        // Configuración de la ventana
        setTitle("Dashboard - " + role);
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Título
        JLabel headerLabel = new JLabel("Panel Principal - " + role, SwingConstants.CENTER);
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Contenido dinámico según el rol
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridLayout(0, 1, 10, 10));

        if (role.equals("Administrador") || role.equals("Empleado")) {
            addAdminEmployeeFeatures(contentPanel);
        } else if (role.equals("Cliente")) {
            // Crear o recuperar el cliente
            addClientFeatures(contentPanel, cliente); // Pasar el cliente al método
        }

        // Botón de cierre de sesión
        JButton logoutButton = new JButton("Cerrar Sesión");
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginScreen(); // Regresar a la pantalla de inicio de sesión
        });

        // Añadir componentes al panel principal
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        mainPanel.add(logoutButton, BorderLayout.SOUTH);

        // Añadir panel principal al marco
        add(mainPanel);
        setVisible(true);
    }

    private void addAdminEmployeeFeatures(JPanel contentPanel) {
        JButton viewShipmentsButton = new JButton("Ver Envíos por Estado");
        JButton viewVehiclesButton = new JButton("Ver Disponibilidad de Vehículos");
        JButton viewClientsButton = new JButton("Total de Clientes");
        JButton viewInvoicesButton = new JButton("Facturación Acumulada");

        // Agregar funcionalidad a los botones
        viewShipmentsButton.addActionListener(e -> showMessage("Envíos por Estado", "10 enviados, 5 pendientes, 3 devueltos"));
        viewVehiclesButton.addActionListener(e -> showMessage("Disponibilidad de Vehículos", "8 disponibles, 2 en uso"));
        viewClientsButton.addActionListener(e -> showMessage("Total de Clientes", "Clientes registrados: 150"));
        viewInvoicesButton.addActionListener(e -> showMessage("Facturación Acumulada", "Total: $150,000"));

        // Añadir botones al panel
        contentPanel.add(viewShipmentsButton);
        contentPanel.add(viewVehiclesButton);
        contentPanel.add(viewClientsButton);
        contentPanel.add(viewInvoicesButton);
    }

    private void addClientFeatures(JPanel contentPanel, Cliente cliente) {
        // Botones específicos para el cliente
        JButton viewProfileButton = new JButton("Ver Perfil");
        JButton sendPackageButton = new JButton("Realizar Envío");
        JButton returnPackageButton = new JButton("Devolver Paquete");
        JButton viewInvoiceButton = new JButton("Consultar Facturas");
    
        // Funcionalidades de los botones
        viewProfileButton.addActionListener(e -> showMessage("Perfil del Cliente", cliente.toString()));
        sendPackageButton.addActionListener(e -> new ClienteEnviosGUI(cliente)); // Abre ClienteEnviosGUI
        returnPackageButton.addActionListener(e -> showMessage("Devolver Paquete", "Función para devolver un paquete."));
        viewInvoiceButton.addActionListener(e -> showMessage("Consultar Facturas", "Factura #1234, Total: $200."));
    
        // Añadir botones al panel
        contentPanel.add(viewProfileButton);
        contentPanel.add(sendPackageButton);
        contentPanel.add(returnPackageButton);
        contentPanel.add(viewInvoiceButton);
    }
    

    private void showMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}
