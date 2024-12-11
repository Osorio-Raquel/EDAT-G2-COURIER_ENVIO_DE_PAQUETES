package Vista;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class login extends JFrame {

    private JTextField correo;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPasswordField password;

    public login() {
        setResizable(false);
        setType(Type.UTILITY);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 995, 700);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 245));  // Fondo gris claro
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        // Título principal
        JLabel lblNewLabel_2 = new JLabel("INICIAR SESION");
        lblNewLabel_2.setForeground(new Color(0, 51, 102)); // Azul oscuro
        lblNewLabel_2.setFont(new Font("Helvetica Neue", Font.BOLD, 40));
        lblNewLabel_2.setBounds(170, 100, 288, 70);
        contentPane.add(lblNewLabel_2);

        // Campo de correo
        JLabel lblNewLabel_3 = new JLabel("Correo:");
        lblNewLabel_3.setForeground(new Color(0, 51, 102)); // Azul oscuro
        lblNewLabel_3.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        lblNewLabel_3.setBounds(85, 200, 157, 30);
        contentPane.add(lblNewLabel_3);

        correo = new JTextField();
        correo.setForeground(new Color(0, 0, 0)); // Texto negro
        correo.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        correo.setBackground(new Color(255, 255, 255));  // Fondo blanco
        correo.setBounds(153, 240, 404, 40);
        correo.setText("cliente@cliente.com");  // Valor predeterminado
        contentPane.add(correo);
        correo.setColumns(10);

        // Campo de contraseña
        password = new JPasswordField();
        password.setForeground(new Color(0, 0, 0)); // Texto negro
        password.setFont(new Font("Helvetica Neue", Font.PLAIN, 16));
        password.setBackground(new Color(255, 255, 255)); // Fondo blanco
        password.setBounds(153, 320, 404, 40);
        password.setText("cliente123");  // Valor predeterminado
        contentPane.add(password);

        // Botón de ingreso
        JButton BotonIngresar = new JButton("Iniciar Sesion");
        BotonIngresar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Captura de datos ingresados
                String correoUsuario = correo.getText().trim();
                String contrasena = new String(password.getPassword()).trim();

                // Verificar si los campos están vacíos
                if (correoUsuario.isEmpty() || contrasena.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Validación de credenciales
                if (correoUsuario.equals("admin@admin.com") && contrasena.equals("admin123")) {
                    JOptionPane.showMessageDialog(null, "Ingreso permitido como Administrador", "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
                    // Redirigir al menú del administrador
                    MenuGerente ger = new MenuGerente();
                    ger.setVisible(true);
                    dispose();
                } else if (correoUsuario.equals("cliente@cliente.com") && contrasena.equals("cliente123")) {
                    JOptionPane.showMessageDialog(null, "Ingreso permitido como Cliente", "Acceso concedido", JOptionPane.INFORMATION_MESSAGE);
                    // Redirigir al menú del cliente
                    VentanaEnvio venta = new VentanaEnvio();
                    venta.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Ingreso denegado. Verifique sus credenciales.", "Error", JOptionPane.ERROR_MESSAGE);
                }

                // Limpiar los campos después de intentar ingresar
                correo.setText("");
                password.setText("");
            }
        });
        BotonIngresar.setFont(new Font("Helvetica Neue", Font.BOLD, 18));
        BotonIngresar.setBackground(new Color(0, 102, 204));  // Azul claro
        BotonIngresar.setForeground(new Color(255, 255, 255)); // Texto blanco
        BotonIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BotonIngresar.setBounds(153, 400, 180, 49);  // Hacemos el botón más pequeño (ancho reducido)
        contentPane.add(BotonIngresar);

        // Animación al presionar el botón
        BotonIngresar.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Reducir el tamaño del botón al presionar
                BotonIngresar.setBounds(153, 400, 150, 49); // Cambia el tamaño
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Restaurar el tamaño original al soltar
                BotonIngresar.setBounds(153, 400, 180, 49); // Restaurar el tamaño
            }
        });

        // Campo de contraseña visible
        JButton vercontrasena = new JButton("Ver contraseña");
        vercontrasena.setFont(new Font("Helvetica Neue", Font.PLAIN, 14));
        vercontrasena.setForeground(new Color(0, 51, 102)); // Azul oscuro
        vercontrasena.setBackground(new Color(245, 245, 245));  // Fondo gris claro
        vercontrasena.setBounds(153, 370, 128, 21);
        vercontrasena.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(vercontrasena);

        // ActionListener para mostrar/ocultar la contraseña al presionar el botón
        vercontrasena.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                password.setEchoChar((char) 0);  // Mostrar la contraseña
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                password.setEchoChar('\u2022');  // Ocultar la contraseña
            }
        });

        // Instrucciones adicionales
        JLabel lblNewLabel_3_2 = new JLabel("Contraseña:");
        lblNewLabel_3_2.setForeground(new Color(0, 51, 102)); // Azul oscuro
        lblNewLabel_3_2.setFont(new Font("Helvetica Neue", Font.PLAIN, 18));
        lblNewLabel_3_2.setBounds(85, 280, 157, 30);
        contentPane.add(lblNewLabel_3_2);
    }
}
