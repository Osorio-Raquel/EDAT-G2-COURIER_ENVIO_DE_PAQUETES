package Vista;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import Modelo.ClienteFactura;
import Modelo.EnviarEmailFactura;
import Modelo.FacturaEnPDF;
import Modelo.VentasFactura;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Factura extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nombre;
    private JTextField nitci;
    private JTextField correo;
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
        contentPane.setLayout(null);
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
        sucursal.setBounds(10, 10, 150, 30);
        sucursal.setForeground(Color.WHITE);
        sucursal.setFont(new Font("Arial", Font.BOLD, 16));
        contentPane.add(sucursal);

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
        detalleFactura.setFont(new Font("Arial", Font.PLAIN, 14));
        detalleFactura.setForeground(Color.BLACK);
        detalleFactura.setBackground(Color.WHITE);
        detalleFactura.setRowHeight(30);
        detalleFactura.setFocusable(false); // Deshabilitar el enfoque para evitar la edición por teclado
        JScrollPane scrollPane = new JScrollPane(detalleFactura);
        scrollPane.setBounds(43, 341, 900, 232);
        contentPane.add(scrollPane);

        // Nuevas etiquetas con colores y tipografía ajustados
        JLabel avenida = new JLabel("Av. Hernando Siles");
        avenida.setForeground(Color.WHITE);
        avenida.setFont(new Font("Arial", Font.PLAIN, 14));
        avenida.setBounds(10, 45, 220, 30);
        contentPane.add(avenida);

        JLabel calle = new JLabel("Calle 3 de Obrajes");
        calle.setForeground(Color.WHITE);
        calle.setFont(new Font("Arial", Font.PLAIN, 14));
        calle.setBounds(10, 70, 220, 30);
        contentPane.add(calle);

        JLabel telefono = new JLabel("Telefono: 72036743");
        telefono.setForeground(Color.WHITE);
        telefono.setFont(new Font("Arial", Font.PLAIN, 14));
        telefono.setBounds(10, 95, 250, 30);
        contentPane.add(telefono);

        JLabel ciudad = new JLabel("La Paz - Bolivia");
        ciudad.setForeground(Color.WHITE);
        ciudad.setFont(new Font("Arial", Font.PLAIN, 14));
        ciudad.setBounds(10, 120, 250, 30);
        contentPane.add(ciudad);

        // Título de la factura
        JLabel lblFactura = new JLabel("FACTURA");
        lblFactura.setForeground(Color.WHITE);
        lblFactura.setFont(new Font("Arial", Font.BOLD, 72));
        lblFactura.setBounds(280, 80, 500, 120);
        contentPane.add(lblFactura);

        // Logo
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Documentos\\imag\\logo220x150.png"));
        lblNewLabel.setBounds(750, 10, 220, 150);
        contentPane.add(lblNewLabel);

        // Campos de entrada
        JLabel lblnitci = new JLabel("NIT/CI:");
        lblnitci.setForeground(Color.WHITE);
        lblnitci.setFont(new Font("Arial", Font.BOLD, 18));
        lblnitci.setBounds(43, 219, 120, 29);
        contentPane.add(lblnitci);

        JLabel lblnombre = new JLabel("Nombre/Razon Social:");
        lblnombre.setForeground(Color.WHITE);
        lblnombre.setFont(new Font("Arial", Font.BOLD, 18));
        lblnombre.setBounds(43, 258, 211, 29);
        contentPane.add(lblnombre);

        nombre = new JTextField();
        nombre.setForeground(new Color(0, 0, 0));
        nombre.setFont(new Font("Arial", Font.PLAIN, 18));
        nombre.setBounds(252, 260, 254, 29);
        contentPane.add(nombre);
        nombre.setColumns(10);
        // Prellenar el campo 'nombre'
        nombre.setText("Juan Pérez");

        nitci = new JTextField();
        nitci.setForeground(new Color(0, 0, 0));
        nitci.setFont(new Font("Arial", Font.PLAIN, 18));
        nitci.setBackground(new Color(255, 255, 255));
        nitci.setColumns(10);
        nitci.setBounds(122, 219, 183, 29);
        contentPane.add(nitci);
        // Prellenar el campo 'nitci'
        nitci.setText("123456789");

        // Cambiar el método de pago a un diseño más amigable
        String[] metodos = { "Tarjeta", "Efectivo" };
        JComboBox<String> metodoPago = new JComboBox<>(metodos);
        metodoPago.setFont(new Font("Arial", Font.PLAIN, 18));
        metodoPago.setBackground(new Color(13, 71, 170));
        metodoPago.setBounds(689, 258, 254, 29);
        contentPane.add(metodoPago);

        JLabel lblmetodo = new JLabel("Método de pago:");
        lblmetodo.setForeground(Color.WHITE);
        lblmetodo.setFont(new Font("Arial", Font.BOLD, 18));
        lblmetodo.setBounds(689, 219, 211, 29);
        contentPane.add(lblmetodo);

        JLabel lblcorreo = new JLabel("Correo Electrónico:");
        lblcorreo.setForeground(Color.WHITE);
        lblcorreo.setFont(new Font("Arial", Font.BOLD, 18));
        lblcorreo.setBounds(43, 299, 211, 29);
        contentPane.add(lblcorreo);
        
        JButton btnFacturar = new JButton("Facturar");
        btnFacturar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        				FacturaEnPDF facPDF = new FacturaEnPDF ();
        				EnviarEmailFactura ev = new EnviarEmailFactura();
        				ev.EnviarCorreo();
            			Ventas v = new Ventas();
            			v.setVisible(true);
            			dispose();
        	}
        });
        btnFacturar.setForeground(Color.WHITE);
        btnFacturar.setFont(new Font("Roboto Medium", Font.BOLD, 25));
        btnFacturar.setFocusPainted(false);
        btnFacturar.setBorder(new LineBorder(new Color(7, 54, 127), 2));
        btnFacturar.setBackground(new Color(21, 101, 192));
        btnFacturar.setBounds(741, 589, 202, 46);
        contentPane.add(btnFacturar);
    }
}
