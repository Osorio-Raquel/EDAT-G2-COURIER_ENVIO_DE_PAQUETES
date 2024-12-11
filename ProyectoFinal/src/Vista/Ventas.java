package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Ventas extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField pesoField;
    private JTextField dimensionesField;
    private JComboBox<String> rutaComboBox;
    public static final ArrayList<Envio> envios = new ArrayList<>();
    public String[] columnNames = {"Ruta", "Peso (kg)", "Dimensiones (cm)", "Precio"};
    public DefaultTableModel model = new DefaultTableModel(null, columnNames);

    // Clase auxiliar para representar un Envío
    public static class Envio {
        private String ruta;
        private double peso;
        private String dimensiones;
        private double precio;

        public Envio(String ruta, double peso, String dimensiones, double precio) {
            this.ruta = ruta;
            this.peso = peso;
            this.dimensiones = dimensiones;
            this.precio = precio;
        }

        public String getRuta() {
            return ruta;
        }

        public double getPeso() {
            return peso;
        }

        public String getDimensiones() {
            return dimensiones;
        }

        public double getPrecio() {
            return precio;
        }
    }

    /**
     * Create the frame.
     */
    public Ventas() {
        setType(Type.UTILITY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1111, 758);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 123, 255));  // Azul brillante
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        // Panel Izquierdo con BoxLayout Vertical
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(new Color(0, 51, 102));  // Azul oscuro
        panelIzquierdo.setBounds(0, 0, 350, 758);
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setBorder(new EmptyBorder(10, 10, 10, 10)); // Espaciado interno
        contentPane.add(panelIzquierdo);

        // Logo
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon("C:\\Documentos\\imag\\logo330x200.png"));
        lblLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelIzquierdo.add(lblLogo);

        // Espacio entre logo y botones
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de Botones de Envío
        JPanel panelBotonesEnvio = new JPanel();
        panelBotonesEnvio.setLayout(new BoxLayout(panelBotonesEnvio, BoxLayout.Y_AXIS));
        panelBotonesEnvio.setBackground(new Color(0, 51, 102));  // Azul oscuro
        panelIzquierdo.add(panelBotonesEnvio);

        // Lista de nombres y categorías de botones
        String[][] botones = {
            {"Envíos Nacionales", "1"},
            {"Envíos Internacionales", "2"},
            {"Envíos Express", "3"},
            {"Envíos Premium", "4"},
            {"Envíos Int. Express", "5"},
            {"Envíos Int. Premium", "6"}
        };

        // Crear y agregar botones
        for (String[] botonInfo : botones) {
            JButton boton = crearBotonEnvio(botonInfo[0], botonInfo[1]);
            panelBotonesEnvio.add(boton);
            panelBotonesEnvio.add(Box.createRigidArea(new Dimension(0, 10))); // Espacio entre botones
        }

        // Separador
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 20)));
        JSeparator separator = new JSeparator();
        separator.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));
        panelIzquierdo.add(separator);
        panelIzquierdo.add(Box.createRigidArea(new Dimension(0, 20)));

        // Panel de Campos de Envío
        JPanel panelCamposEnvio = new JPanel();
        panelCamposEnvio.setLayout(new BoxLayout(panelCamposEnvio, BoxLayout.Y_AXIS));
        panelCamposEnvio.setBackground(new Color(0, 51, 102));  // Azul oscuro
        panelIzquierdo.add(panelCamposEnvio);

        // Campo de Peso
        JLabel lblPeso = new JLabel("Peso del paquete (kg):");
        lblPeso.setForeground(Color.WHITE);
        lblPeso.setFont(new Font("Arial", Font.BOLD, 18));  // Cambio de tipografía
        lblPeso.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCamposEnvio.add(lblPeso);
        panelCamposEnvio.add(Box.createRigidArea(new Dimension(0, 5)));

        pesoField = new JTextField();
        pesoField.setFont(new Font("Arial", Font.PLAIN, 16));  // Cambio de tipografía
        pesoField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelCamposEnvio.add(pesoField);
        panelCamposEnvio.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo de Dimensiones
        JLabel lblDimensiones = new JLabel("Dimensiones del paquete (cm):");
        lblDimensiones.setForeground(Color.WHITE);
        lblDimensiones.setFont(new Font("Arial", Font.BOLD, 18));  // Cambio de tipografía
        lblDimensiones.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCamposEnvio.add(lblDimensiones);
        panelCamposEnvio.add(Box.createRigidArea(new Dimension(0, 5)));

        dimensionesField = new JTextField();
        dimensionesField.setFont(new Font("Arial", Font.PLAIN, 16));  // Cambio de tipografía
        dimensionesField.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelCamposEnvio.add(dimensionesField);
        panelCamposEnvio.add(Box.createRigidArea(new Dimension(0, 15)));

        // Campo de Ruta
        JLabel lblRuta = new JLabel("Ruta:");
        lblRuta.setForeground(Color.WHITE);
        lblRuta.setFont(new Font("Arial", Font.BOLD, 18));  // Cambio de tipografía
        lblRuta.setAlignmentX(Component.LEFT_ALIGNMENT);
        panelCamposEnvio.add(lblRuta);
        panelCamposEnvio.add(Box.createRigidArea(new Dimension(0, 5)));

        String[] rutas = {
            "Ruta A: La Paz - Cochabamba",
            "Ruta B: La Paz - Santa Cruz",
            "Ruta C: La Paz - Oruro"
        };
        rutaComboBox = new JComboBox<>(rutas);
        rutaComboBox.setFont(new Font("Arial", Font.PLAIN, 16));  // Cambio de tipografía
        rutaComboBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        panelCamposEnvio.add(rutaComboBox);
        panelCamposEnvio.add(Box.createRigidArea(new Dimension(0, 20)));

        // Botón para Agregar Envío
        JButton btnAgregarEnvio = new JButton("Agregar Envío");
        btnAgregarEnvio.setForeground(Color.WHITE);
        btnAgregarEnvio.setFont(new Font("Arial", Font.BOLD, 18));  // Cambio de tipografía
        btnAgregarEnvio.setBackground(new Color(0, 123, 255));  // Azul brillante
        btnAgregarEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCamposEnvio.add(Box.createRigidArea(new Dimension(0, 15)));
        panelCamposEnvio.add(btnAgregarEnvio);

        // Configuración de la tabla
        JTable tabla = new JTable(model);
        tabla.setFont(new Font("Arial", Font.PLAIN, 14));  // Cambio de tipografía
        tabla.setBackground(new Color(240, 248, 255));  // Azul claro
        JScrollPane scrollPane = new JScrollPane(tabla);
        scrollPane.setBounds(370, 10, 700, 700);
        contentPane.add(scrollPane);

    // Botón para eliminar un envío
        JButton btnEliminarEnvio = new JButton("Eliminar Envío");
        btnEliminarEnvio.setForeground(Color.WHITE);
        btnEliminarEnvio.setFont(new Font("Roboto Black", Font.BOLD, 18));
        btnEliminarEnvio.setBackground(new Color(255, 51, 51));
        btnEliminarEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCamposEnvio.add(Box.createRigidArea(new Dimension(0, 15)));
        panelCamposEnvio.add(btnEliminarEnvio);

        // Botón para Facturar
        JButton btnFacturar = new JButton("Facturar");
        btnFacturar.setForeground(Color.WHITE);
        btnFacturar.setFont(new Font("Roboto Black", Font.BOLD, 18));
        btnFacturar.setBackground(new Color(0, 153, 51)); // Color verde para facturación
        btnFacturar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCamposEnvio.add(Box.createRigidArea(new Dimension(0, 15)));
        panelCamposEnvio.add(btnFacturar);
        
        // Botón para eliminar un envío
        JButton btnSalir = new JButton("Salir");
        btnEliminarEnvio.setForeground(Color.WHITE);
        btnEliminarEnvio.setFont(new Font("Roboto Black", Font.BOLD, 18));
        btnEliminarEnvio.setBackground(new Color(255, 51, 51));
        btnEliminarEnvio.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCamposEnvio.add(Box.createRigidArea(new Dimension(0, 15)));
        panelCamposEnvio.add(btnSalir);
        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VentanaEnvio meng = new VentanaEnvio();
                meng.setVisible(true);
                dispose();
            }
        });

        // Acciones para agregar un envío
        btnAgregarEnvio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener los valores ingresados
                try {
                    double peso = Double.parseDouble(pesoField.getText());
                    String dimensiones = dimensionesField.getText();
                    String ruta = (String) rutaComboBox.getSelectedItem();

                    // Calcular el precio de acuerdo al peso (puedes ajustar esta lógica según tus necesidades)
                    double precio = calcularPrecio(peso);

                    // Crear el nuevo Envío y agregarlo a la lista
                    Envio envio = new Envio(ruta, peso, dimensiones, precio);
                    envios.add(envio);

                    // Actualizar la tabla con el nuevo envío
                    model.addRow(new Object[]{envio.getRuta(), envio.getPeso(), envio.getDimensiones(), envio.getPrecio()});

                    // Limpiar los campos después de agregar
                    pesoField.setText("");
                    dimensionesField.setText("");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Ventas.this, "Por favor ingresa valores válidos para el peso.");
                }
            }
        });

        // Acciones para eliminar un envío
        btnEliminarEnvio.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener la fila seleccionada
                int selectedRow = tabla.getSelectedRow();

                if (selectedRow != -1) {
                    // Eliminar el envío de la lista y actualizar la tabla
                    envios.remove(selectedRow);
                    model.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(Ventas.this, "Por favor selecciona un envío para eliminar.");
                }
            }
        });

        // Acción para el botón de facturación
        btnFacturar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llamada al método de facturación (puedes modificarlo según la lógica de tu aplicación)
                facturar();
            }
        });
    }

    // Método para crear los botones de envío
    private JButton crearBotonEnvio(String texto, String categoria) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        boton.setBackground(new Color(51, 102, 255)); // Azul
        boton.setForeground(Color.WHITE);
        boton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

        // Acción al hacer clic en el botón
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí puedes definir qué sucede cuando se hace clic en cada botón, por ejemplo:
                JOptionPane.showMessageDialog(Ventas.this, "Seleccionaste la opción de " + texto);
            }
        });

        return boton;
    }

    // Método para calcular el precio del envío basado en el peso
    private double calcularPrecio(double peso) {
        double precioBase = 10.0;  // Precio base
        if (peso <= 1) {
            return precioBase;
        } else if (peso <= 5) {
            return precioBase + 5.0;
        } else {
            return precioBase + 10.0;
        }
    }

    // Método de facturación
    private void facturar() {
        if (envios.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay envíos para facturar.");
        } else {
            double total = 0;
            for (Envio envio : envios) {
                total += envio.getPrecio();
            }            

            JOptionPane.showMessageDialog(this, "Factura generada con éxito. Total a pagar: Bs. " + total);
            Factura factura = new Factura();
            factura.setVisible(true);
            dispose();
        }
    }

}
