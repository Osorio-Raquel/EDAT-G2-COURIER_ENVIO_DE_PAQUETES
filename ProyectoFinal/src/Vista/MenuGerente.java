package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuGerente extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    // Constantes para los colores
    private static final Color COLOR_INICIAL = new Color(0, 102, 204); // Azul claro
    private static final Color COLOR_HOVER = new Color(21, 151, 192); // Azul más oscuro
    private static final Color COLOR_FONDO = new Color(245, 245, 245);  // Fondo gris claro

    public MenuGerente() {
        setResizable(false);
        setType(Type.UTILITY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 990, 695);
        contentPane = new JPanel();
        contentPane.setBackground(COLOR_FONDO);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        // Título principal
        JLabel lblNewLabel_2 = new JLabel("ADMINISTRADOR");
        lblNewLabel_2.setForeground(new Color(0, 51, 102)); // Azul oscuro
        lblNewLabel_2.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
        lblNewLabel_2.setBounds(74, 100, 468, 192);
        contentPane.add(lblNewLabel_2);

        // Botón "Controlar Envíos"
        JButton botonRealizarPedido = createHoverButton("Controlar Envíos", 74, 280, 422, 64);
        botonRealizarPedido.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Pedidos pedido = new Pedidos();
                pedido.setVisible(true);
                dispose();
            }
        });
        contentPane.add(botonRealizarPedido);

        // Botón "Administrar Personal"
        JButton botonAdmEmpleados = createHoverButton("Personal", 74, 450, 422, 64);
        botonAdmEmpleados.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AdministrarEmpleados adm = new AdministrarEmpleados();
                adm.setVisible(true);
                dispose();
            }
        });
        contentPane.add(botonAdmEmpleados);

        // Botón "Reportes"
        JButton botonReportes = createHoverButton("Reportes", 74, 365, 422, 64);
        botonReportes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Reportes reportes = new Reportes();
                reportes.setVisible(true);
                dispose();
            }
        });
        contentPane.add(botonReportes);

        // Botón "Cerrar Sesión"
        JButton btnCerrarSesion = createHoverButton("Salir", 74, 540, 143, 39);
        btnCerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login log = new login();
                log.setVisible(true);
                dispose();
            }
        });
        contentPane.add(btnCerrarSesion);
    }

    // Método para crear botones con hover
    private JButton createHoverButton(String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Helvetica Neue", Font.BOLD, 20));
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(new Color(7, 54, 127), 2));
        button.setBackground(COLOR_INICIAL);
        button.setBounds(x, y, width, height);
        button.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        // Animación al pasar el mouse
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(COLOR_HOVER);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(COLOR_INICIAL);
            }
        });

        return button;
    }
}
