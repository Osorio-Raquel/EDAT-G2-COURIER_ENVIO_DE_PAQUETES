package Vista;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

public class AdministrarEmpleados extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Create the frame.
     */
    public AdministrarEmpleados() {
        setType(Type.UTILITY);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 995, 548);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(0, 123, 255));  // Cambié el color de fondo a azul
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);  // Usamos un layout nulo para el control total de posiciones
        setLocationRelativeTo(null);

        // Panel derecho donde se carga contenido (tabla, formularios, etc.)
        JPanel content = new JPanel();
        content.setBackground(new Color(0, 123, 255));  // Cambié el color de fondo a azul
        content.setBounds(0, 40, 995, 508);  // Ajustado para que ocupe el espacio restante
        contentPane.add(content);
        content.setLayout(null);

        // Panel superior para los botones (pestañas)
        JPanel panelTop = new JPanel();
        panelTop.setBackground(new Color(13, 71, 170)); // Azul oscuro
        panelTop.setBounds(0, 0, 995, 40);  // Colocado en la parte superior
        contentPane.add(panelTop);
        panelTop.setLayout(new BoxLayout(panelTop, BoxLayout.X_AXIS));  // Usamos BoxLayout para una fila horizontal

        // Botón "Ver Personal" con animación de hover
        JButton btnVerPersonal = new JButton("Personal");
        btnVerPersonal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Panel1 veremp = new Panel1();
                veremp.setSize(995, 508); // Ajuste del tamaño
                veremp.setLocation(0, 0); // Ajuste de la ubicación
                content.removeAll();
                content.add(veremp);
                content.revalidate();
                content.repaint();
            }
        });
        btnVerPersonal.setForeground(Color.WHITE);
        btnVerPersonal.setFont(new Font("Roboto Medium", Font.BOLD, 24));
        btnVerPersonal.setFocusPainted(false);
        btnVerPersonal.setBorder(new LineBorder(new Color(7, 54, 127), 2));
        btnVerPersonal.setBackground(new Color(21, 101, 192)); // Azul claro
        
        // Efecto hover para el botón
        btnVerPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnVerPersonal.setBackground(new Color(0, 76, 153)); // Efecto hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnVerPersonal.setBackground(new Color(21, 101, 192)); // Vuelve al color original
            }
        });
        panelTop.add(btnVerPersonal);

        // Botón "Vehículos" con animación de hover
        JButton btnContratarPersonal = new JButton("Vehiculos");
        btnContratarPersonal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Panel2 mod = new Panel2();
                mod.setSize(995, 508); // Ajuste del tamaño
                mod.setLocation(0, 0); // Ajuste de la ubicación
                content.removeAll();
                content.add(mod);
                content.revalidate();
                content.repaint();
            }
        });
        btnContratarPersonal.setForeground(Color.WHITE);
        btnContratarPersonal.setFont(new Font("Roboto Medium", Font.BOLD, 24));
        btnContratarPersonal.setFocusPainted(false);
        btnContratarPersonal.setBorder(new LineBorder(new Color(7, 54, 127), 2));
        btnContratarPersonal.setBackground(new Color(21, 101, 192)); // Azul claro
        
        // Efecto hover para el botón
        btnContratarPersonal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnContratarPersonal.setBackground(new Color(0, 76, 153)); // Efecto hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnContratarPersonal.setBackground(new Color(21, 101, 192)); // Vuelve al color original
            }
        });
        panelTop.add(btnContratarPersonal);

        // Botón "Atrás" con animación de hover
        JButton btnAtras = new JButton("Atras");
        btnAtras.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MenuGerente meng = new MenuGerente();
                meng.setVisible(true);
                dispose();
            }
        });
        btnAtras.setForeground(Color.WHITE);
        btnAtras.setFont(new Font("Roboto Medium", Font.BOLD, 20));
        btnAtras.setFocusPainted(false);
        btnAtras.setBorder(new LineBorder(new Color(7, 54, 127), 2));
        btnAtras.setBackground(new Color(21, 101, 192)); // Azul claro
        btnAtras.setBounds(400, 460, 151, 55); // Ajuste de posición para asegurar visibilidad

        // Efecto hover para el botón
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtras.setBackground(new Color(0, 76, 153)); // Efecto hover
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtras.setBackground(new Color(21, 101, 192)); // Vuelve al color original
            }
        });

        // Aseguramos que el botón "Atrás" se mantenga visible en la parte inferior
        contentPane.add(btnAtras); // Añadir al contentPane directamente, para que no se remueva con la pestaña

        // Finalmente, ajustamos el tamaño del frame para asegurarnos de que todo esté visible
        setSize(995, 548);
    }
}
