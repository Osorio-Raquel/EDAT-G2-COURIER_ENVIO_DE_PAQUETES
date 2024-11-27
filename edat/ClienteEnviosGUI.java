import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class ClienteEnviosGUI extends JFrame {
    private ClienteEnvios clienteEnvios;
    private Ruta rutas; // Grafo de rutas
    private DefaultListModel<String> enviosModel; // Modelo para mostrar envíos
    private DefaultListModel<String> facturasModel; // Modelo para mostrar facturas
    private Cliente cliente; // Cliente actual

    public ClienteEnviosGUI(Cliente cliente) {
        this.cliente = cliente;

        // Inicializar cliente y rutas
        clienteEnvios = new ClienteEnvios(cliente);
        rutas = new Ruta();
        inicializarRutas();

        // Configuración de la ventana
        setTitle("Gestión de Envíos - Cliente");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Botón para regresar al Dashboard
        JButton backButton = new JButton("Volver al Dashboard");
        backButton.addActionListener(e -> {
            dispose(); // Cierra la ventana actual
            new DashboardScreen("Cliente", cliente); // Abre el Dashboard para el cliente
        });

        // Crear pestañas
        JTabbedPane tabbedPane = new JTabbedPane();

        // Panel de envíos
        JPanel enviosPanel = crearEnviosPanel();
        tabbedPane.addTab("Envíos", enviosPanel);

        // Panel de facturas
        JPanel facturasPanel = crearFacturasPanel();
        tabbedPane.addTab("Facturas", facturasPanel);

        // Agregar componentes al panel principal
        mainPanel.add(backButton, BorderLayout.NORTH); // Botón en la parte superior
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Agregar el panel principal al marco
        add(mainPanel);
        setVisible(true);
    }

    /**
     * Inicializa las rutas disponibles en el grafo.
     */
    private void inicializarRutas() {
        rutas.agregarRuta("Guadalajara", "Ciudad de México", 550);
        rutas.agregarRuta("Ciudad de México", "Cancún", 1300);
        rutas.agregarRuta("Guadalajara", "Monterrey", 700);
        rutas.agregarRuta("Monterrey", "Cancún", 1800);
        rutas.agregarRuta("Guadalajara", "Cancún", 4800);
    }

    /**
     * Crea el panel para la gestión de envíos.
     */
    private JPanel crearEnviosPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Lista para mostrar envíos
        enviosModel = new DefaultListModel<>();
        JList<String> enviosList = new JList<>(enviosModel);
        enviosList.setBorder(BorderFactory.createTitledBorder("Mis Envíos"));

        // Botones para agregar, cancelar y actualizar envíos
        JButton agregarEnvioButton = new JButton("Agregar Envío");
        JButton cancelarEnvioButton = new JButton("Cancelar Envío");
        JButton mostrarEnviosButton = new JButton("Actualizar Lista");
        JButton simButton = new JButton("Simmulacion");

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agregarEnvioButton);
        buttonPanel.add(cancelarEnvioButton);
        buttonPanel.add(mostrarEnviosButton);
        buttonPanel.add(simButton);

        // Eventos
        agregarEnvioButton.addActionListener(e -> agregarEnvio());
        cancelarEnvioButton.addActionListener(e -> cancelarEnvio(enviosList.getSelectedIndex()));
        mostrarEnviosButton.addActionListener(e -> actualizarEnvios());
        simButton.addActionListener(e -> simulacion());

        // Agregar componentes al panel
        panel.add(new JScrollPane(enviosList), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }
    private void simulacion() {
        Paquete paquete = new Paquete(1, "Electrodoméstico", 15.5, "50x40x30 cm", 2000);
        EstadoEnvio estado = new EstadoEnvio(1, "PENDIENTE");
        Envio envio = new Envio(1, cliente, null, null, rutas, estado, new Date(), null, 0, paquete, null);
        new SeguimientoEnvioGUI(envio);
    }

    /**
     * Crea el panel para la gestión de facturas.
     */
    private JPanel crearFacturasPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        // Lista para mostrar facturas
        facturasModel = new DefaultListModel<>();
        JList<String> facturasList = new JList<>(facturasModel);
        facturasList.setBorder(BorderFactory.createTitledBorder("Mis Facturas"));

        // Botones para generar y mostrar facturas
        JButton generarFacturaButton = new JButton("Generar Factura");
        JButton mostrarFacturasButton = new JButton("Actualizar Lista");

        // Panel de botones
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(generarFacturaButton);
        buttonPanel.add(mostrarFacturasButton);

        // Eventos
        generarFacturaButton.addActionListener(e -> generarFactura());
        mostrarFacturasButton.addActionListener(e -> actualizarFacturas());

        // Agregar componentes al panel
        panel.add(new JScrollPane(facturasList), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        return panel;
    }

    /**
     * Acción para agregar un nuevo envío.
     */
    private void agregarEnvio() {
        JTextField descripcionField = new JTextField(20);
        JTextField pesoField = new JTextField(10);
        JTextField dimensionesField = new JTextField(15);
        JComboBox<String> origenComboBox = new JComboBox<>(rutas.obtenerCiudades().toArray(new String[0]));
        JComboBox<String> destinoComboBox = new JComboBox<>(rutas.obtenerCiudades().toArray(new String[0]));

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.add(new JLabel("Descripción del Paquete:"));
        formPanel.add(descripcionField);
        formPanel.add(new JLabel("Peso (kg):"));
        formPanel.add(pesoField);
        formPanel.add(new JLabel("Dimensiones:"));
        formPanel.add(dimensionesField);
        formPanel.add(new JLabel("Origen:"));
        formPanel.add(origenComboBox);
        formPanel.add(new JLabel("Destino:"));
        formPanel.add(destinoComboBox);

        int result = JOptionPane.showConfirmDialog(this, formPanel, "Agregar Envío", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            try {
                String descripcion = descripcionField.getText().trim();
                double peso = Double.parseDouble(pesoField.getText().trim());
                String dimensiones = dimensionesField.getText().trim();
                String origen = (String) origenComboBox.getSelectedItem();
                String destino = (String) destinoComboBox.getSelectedItem();

                clienteEnvios.agregarEnvio(descripcion, peso, dimensiones, 0, origen, destino, rutas);
                JOptionPane.showMessageDialog(this, "Envío agregado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                actualizarEnvios();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al agregar el envío: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Acción para cancelar un envío.
     *
     * @param index Índice del envío a cancelar en la lista
     */
    private void cancelarEnvio(int index) {
        if (index < 0 || index >= clienteEnvios.envios.size()) {
            JOptionPane.showMessageDialog(this, "Seleccione un envío válido para cancelar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        EstadoEnvio estado = clienteEnvios.envios.get(index).getEstadoEnvio();
        estado.setNombreEstado("CANCELADO");

        JOptionPane.showMessageDialog(this, "Envío cancelado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        actualizarEnvios();
    }

    /**
     * Acción para generar una factura.
     */
    private void generarFactura() {
        try {
            clienteEnvios.generarFactura();
            JOptionPane.showMessageDialog(this, "Factura generada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            actualizarFacturas();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al generar la factura: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Actualiza la lista de envíos en la interfaz.
     */
    private void actualizarEnvios() {
        enviosModel.clear();
        for (Envio envio : clienteEnvios.envios) {
            enviosModel.addElement(envio.toString());
        }
    }

    /**
     * Actualiza la lista de facturas en la interfaz.
     */
    private void actualizarFacturas() {
        facturasModel.clear();
        for (Factura factura : clienteEnvios.facturas) {
            facturasModel.addElement(factura.toString());
        }
    }
}
