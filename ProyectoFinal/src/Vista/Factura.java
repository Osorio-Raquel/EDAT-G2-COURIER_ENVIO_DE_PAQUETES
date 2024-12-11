package Vista;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import Modelo.ClienteFactura;
import Modelo.VentasFactura;
import java.util.ArrayList;

public class Factura extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nombre;
    private JTextField nitci;
    private JTable detalleFactura;
    private int facturaID = 1;
    private ArrayList<String> productos;
    private ArrayList<Double> cantidades;

    public Factura() {
        setType(Type.UTILITY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 995, 700);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 123, 255));  // Azul claro de fondo
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout()); // Usamos BorderLayout para organizar la interfaz
        setLocationRelativeTo(null);

        // Inicializando los datos predeterminados
        productos = new ArrayList<>();
        cantidades = new ArrayList<>();
        productos.add("Producto 1");
        productos.add("Producto 2");
        cantidades.add(1.0); // Cantidad del Producto 1
        cantidades.add(2.0); // Cantidad del Producto 2

        // ClienteFactura es utilizado aquí, pero los valores pueden ser predeterminados también
        ClienteFactura cfa = new ClienteFactura("", "", 0);

        // Títulos e información de la factura
        JLabel sucursal = new JLabel("Sucursal #1");
        sucursal.setForeground(Color.WHITE);
        sucursal.setFont(new Font("Arial", Font.BOLD, 16));  // Fuente Arial, tamaño 16
        JPanel datosPanel = new JPanel();  // Panel para los datos de la factura
        datosPanel.setLayout(new BoxLayout(datosPanel, BoxLayout.Y_AXIS));  // Disposición vertical
        datosPanel.setBackground(new Color(0, 123, 255));

        datosPanel.add(sucursal);

        String[] datos = { "Nombre", "Cantidad", "Precio Unitario", "Subtotal" };

        // Crear los productos y cantidades predeterminadas
        VentasFactura vf = new VentasFactura(cantidades, productos);
        DefaultTableModel tablaFac = new DefaultTableModel();
        tablaFac.setColumnIdentifiers(datos);

        // Agregar dos envíos a la tabla (productos)
        double[] precios = { 100.0, 150.0 };  // Precios predeterminados de los productos
        for (int i = 0; i < productos.size(); i++) {
            double subtotal = precios[i] * cantidades.get(i);  // Calcular subtotal
            tablaFac.addRow(new Object[]{productos.get(i), cantidades.get(i), precios[i], subtotal});
        }

        // Se crea la tabla con los productos y cantidades predeterminadas
        detalleFactura = new JTable(tablaFac);
        detalleFactura.setFont(new Font("Arial", Font.PLAIN, 14));  // Cambié la fuente de la tabla
        detalleFactura.setForeground(Color.BLACK);
        detalleFactura.setBackground(Color.WHITE);
        detalleFactura.setRowHeight(30);
        detalleFactura.setFocusable(false); // Deshabilitar el enfoque para evitar la edición por teclado
        JScrollPane scrollPane = new JScrollPane(detalleFactura);

        // Panel para la tabla
        JPanel tablaPanel = new JPanel();
        tablaPanel.setLayout(new BorderLayout());
        tablaPanel.setBackground(new Color(0, 123, 255));
        tablaPanel.add(scrollPane, BorderLayout.CENTER);

        // Añadir los paneles al contentPane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, tablaPanel, datosPanel);
        splitPane.setDividerLocation(700);  // Dividir la ventana: tabla en la izquierda y datos a la derecha
        splitPane.setEnabled(false);  // Evitar que el usuario mueva la división
        contentPane.add(splitPane, BorderLayout.CENTER);

        // Logo
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Documentos\\imag\\logo220x150.png"));
        lblNewLabel.setBounds(750, 10, 220, 150);
        contentPane.add(lblNewLabel, BorderLayout.NORTH);

        // Campos de entrada
        JPanel fieldsPanel = new JPanel();
        fieldsPanel.setLayout(new BoxLayout(fieldsPanel, BoxLayout.Y_AXIS)); // Alineación vertical para los campos
        fieldsPanel.setBackground(new Color(0, 123, 255));
        
        JLabel lblnitci = new JLabel("NIT/CI:");
        lblnitci.setForeground(Color.WHITE);
        lblnitci.setFont(new Font("Arial", Font.BOLD, 18));
        fieldsPanel.add(lblnitci);

        JLabel lblnombre = new JLabel("Nombre/Razon Social:");
        lblnombre.setForeground(Color.WHITE);
        lblnombre.setFont(new Font("Arial", Font.BOLD, 18));
        fieldsPanel.add(lblnombre);

        nombre = new JTextField();
        nombre.setForeground(new Color(0, 0, 0));
        nombre.setFont(new Font("Arial", Font.PLAIN, 18));
        fieldsPanel.add(nombre);
        nombre.setColumns(10);
        nombre.setText("Juan Pérez");

        nitci = new JTextField();
        nitci.setForeground(new Color(0, 0, 0));
        nitci.setFont(new Font("Arial", Font.PLAIN, 18));
        nitci.setBackground(new Color(255, 255, 255));
        nitci.setColumns(10);
        fieldsPanel.add(nitci);
        nitci.setText("123456789");

        // Método de pago
        String[] metodos = { "Tarjeta", "Efectivo" };
        JComboBox<String> metodoPago = new JComboBox<>(metodos);
        metodoPago.setFont(new Font("Arial", Font.PLAIN, 18));
        fieldsPanel.add(metodoPago);

        // Botón de facturar
        JButton btnFacturar = new JButton("Facturar");
        btnFacturar.setFont(new Font("Arial", Font.BOLD, 18));
        btnFacturar.setForeground(Color.WHITE);
        btnFacturar.setBackground(new Color(0, 123, 255));
        btnFacturar.setFocusable(false);
        btnFacturar.setAlignmentX(Component.CENTER_ALIGNMENT);
        fieldsPanel.add(Box.createVerticalStrut(20));  // Espacio antes del botón
        fieldsPanel.add(btnFacturar);
        
        btnFacturar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	Ventas ventana1 = new Ventas();
        		ventana1.setVisible(true);
        		dispose();
            }
        });

        // Agregar el panel de campos al panel de datos
        datosPanel.add(fieldsPanel);
    }
}
