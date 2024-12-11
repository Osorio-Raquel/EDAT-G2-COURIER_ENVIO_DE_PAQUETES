package Vista;

import javax.swing.JPanel;
import java.sql.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

import conexionBase.conexionBD;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Panel2 extends JPanel {

    private static final long serialVersionUID = 1L;
    public static final JTextField idvehiculo = new JTextField();
    public static final JTextField marca = new JTextField();
    public static final JTextField modelo = new JTextField();
    public static final JTextField anio = new JTextField();
    public static final JTextField color = new JTextField();
    public static final JTextField placa = new JTextField();

    // Lista de vehículos para mostrar
    private DefaultListModel<String> listaModel;
    private JList<String> listaVehiculos;

    /**
     * Create the panel.
     */
    public Panel2() {
        setBackground(new Color(13, 71, 170));
        setLayout(null);

        JLabel lblListaDeVehiculos = new JLabel("ADMINISTRAR VEHÍCULOS");
        lblListaDeVehiculos.setForeground(new Color(255, 255, 255));
        lblListaDeVehiculos.setFont(new Font("Roboto Black", Font.BOLD, 30));
        lblListaDeVehiculos.setBounds(137, 26, 400, 29);
        add(lblListaDeVehiculos);

        JLabel lblIngreseAlgunCampo = new JLabel("Ingrese algún campo para buscar el vehículo:");
        lblIngreseAlgunCampo.setForeground(Color.WHITE);
        lblIngreseAlgunCampo.setFont(new Font("Roboto Medium", Font.ITALIC, 18));
        lblIngreseAlgunCampo.setBounds(23, 65, 400, 29);
        add(lblIngreseAlgunCampo);

        // Etiquetas y campos de texto para la búsqueda
        JLabel lblId = new JLabel("ID:");
        lblId.setForeground(Color.WHITE);
        lblId.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
        lblId.setBounds(23, 104, 73, 29);
        add(lblId);

        JLabel lblMarca = new JLabel("Marca:");
        lblMarca.setForeground(Color.WHITE);
        lblMarca.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
        lblMarca.setBounds(23, 145, 73, 29);
        add(lblMarca);

        JLabel lblModelo = new JLabel("Modelo:");
        lblModelo.setForeground(Color.WHITE);
        lblModelo.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
        lblModelo.setBounds(23, 186, 73, 29);
        add(lblModelo);

        JLabel lblAnio = new JLabel("Año:");
        lblAnio.setForeground(Color.WHITE);
        lblAnio.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
        lblAnio.setBounds(23, 227, 73, 29);
        add(lblAnio);

        JLabel lblColor = new JLabel("Color:");
        lblColor.setForeground(Color.WHITE);
        lblColor.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
        lblColor.setBounds(23, 268, 73, 29);
        add(lblColor);

        JLabel lblPlaca = new JLabel("Placa:");
        lblPlaca.setForeground(Color.WHITE);
        lblPlaca.setFont(new Font("Roboto Medium", Font.PLAIN, 18));
        lblPlaca.setBounds(23, 309, 73, 29);
        add(lblPlaca);

        idvehiculo.setForeground(new Color(128, 128, 128));
        idvehiculo.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        idvehiculo.setColumns(10);
        idvehiculo.setBounds(106, 104, 175, 29);
        add(idvehiculo);

        marca.setForeground(new Color(128, 128, 128));
        marca.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        marca.setColumns(10);
        marca.setBounds(106, 146, 175, 29);
        add(marca);

        modelo.setForeground(new Color(128, 128, 128));
        modelo.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        modelo.setColumns(10);
        modelo.setBounds(106, 187, 175, 29);
        add(modelo);

        anio.setForeground(new Color(128, 128, 128));
        anio.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        anio.setColumns(10);
        anio.setBounds(106, 228, 175, 29);
        add(anio);

        color.setForeground(new Color(128, 128, 128));
        color.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        color.setColumns(10);
        color.setBounds(106, 269, 175, 29);
        add(color);

        placa.setForeground(new Color(128, 128, 128));
        placa.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        placa.setColumns(10);
        placa.setBounds(106, 310, 175, 29);
        add(placa);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(idvehiculo.getText().isEmpty() && !marca.getText().isEmpty()) {
                    buscar(marca.getText());
                } else if(!idvehiculo.getText().isEmpty() && marca.getText().isEmpty()) {
                    buscar(idvehiculo.getText());
                } else if(idvehiculo.getText().isEmpty() && marca.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Debe ingresar la marca o el ID");
                } else if(!idvehiculo.getText().isEmpty() && !marca.getText().isEmpty()) {
                    buscar(idvehiculo.getText());
                }
            }
        });
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Roboto Black", Font.BOLD, 24));
        btnBuscar.setBackground(new Color(51, 102, 255));
        btnBuscar.setBounds(116, 359, 149, 44);
        add(btnBuscar);

        // Crear la lista de vehículos
        listaModel = new DefaultListModel<>();
        listaVehiculos = new JList<>(listaModel);
        listaVehiculos.setFont(new Font("Roboto Medium", Font.PLAIN, 16));
        listaVehiculos.setForeground(new Color(128, 128, 128));

        JScrollPane scrollPane = new JScrollPane(listaVehiculos);
        scrollPane.setBounds(324, 65, 250, 300); // Ajusta la ubicación y tamaño
        add(scrollPane);

        // Ejemplo de añadir vehículos a la lista
        listaModel.addElement("Vehículo 1: Marca X, Modelo Y, Año 2020");
        listaModel.addElement("Vehículo 2: Marca A, Modelo B, Año 2019");
    }

    // Método de búsqueda de vehículos (simulado)
    private void buscar(String criterio) {
        // Aquí buscarías en la base de datos y actualizarías la lista de vehículos
        listaModel.clear(); // Limpiar la lista antes de mostrar los resultados
        // Simulación de búsqueda (agrega resultados simulados)
        listaModel.addElement("Vehículo encontrado: Marca " + criterio);
    }
}
