package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;


import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Reportes extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelIntervalos; // Declarar panelIntervalos como variable de clase

    /**
     * Create the frame.
     */
    public Reportes() {
        setType(Type.UTILITY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 996, 700);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(33, 47, 60)); // Azul oscuro
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);

        // Logo en la parte superior izquierda
        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon("C:\\Documentos\\imag\\logo330x200.png"));
        lblLogo.setBounds(118, 10, 330, 200);
        contentPane.add(lblLogo);

        // Título
        JLabel lblTitulo = new JLabel("Reportes Administrativos");
        lblTitulo.setForeground(new Color(255, 255, 255)); // Blanco
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 42)); // Fuente moderna
        lblTitulo.setBounds(138, 156, 600, 60);
        contentPane.add(lblTitulo);



        // Botón "Atrás"
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuGerente menuGerente = new MenuGerente();
                menuGerente.setVisible(true);
                dispose();
            }
        });
        customizeButton(btnAtras);
        btnAtras.setBounds(29, 603, 143, 39);
        contentPane.add(btnAtras);

        // Panel para contener botones adicionales (espaciado)
        panelIntervalos = new JPanel(); // Inicializar panelIntervalos
        panelIntervalos.setBackground(new Color(33, 47, 60));
        panelIntervalos.setBounds(71, 515, 422, 75);
        contentPane.add(panelIntervalos);

    



    }

    // Método para limpiar el panel de intervalos (si se necesita)
    private void limpiarPanelIntervalos() {
        panelIntervalos.removeAll();
        panelIntervalos.revalidate();
        panelIntervalos.repaint();
    }

    // Método para personalizar los botones: agregar bordes, colores y animaciones
    private void customizeButton(JButton button) {
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Segoe UI", Font.BOLD, 20)); // Cambié la fuente
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(new Color(85, 126, 163), 3)); // Borde grueso y azul más suave
        button.setBackground(new Color(46, 91, 141)); // Azul más suave

        // Agregar animaciones al pasar el mouse
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(33, 67, 100)); // Cambia a un azul más oscuro cuando el mouse entra
                button.setBorder(new LineBorder(new Color(30, 55, 85), 3)); // Cambiar color del borde
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(46, 91, 141)); // Vuelve al color original
                button.setBorder(new LineBorder(new Color(85, 126, 163), 3)); // Borde normal
            }

            @Override
            public void mousePressed(MouseEvent e) {
                button.setBackground(new Color(25, 50, 75)); // Cambio de color al hacer clic
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                button.setBackground(new Color(33, 67, 100)); // Vuelve al color después de soltar
            }
        });
    }
}
