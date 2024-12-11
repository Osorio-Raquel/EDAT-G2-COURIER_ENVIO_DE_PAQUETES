import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeguimientoEnvioGUI extends JFrame {
    private JLabel alertaLabel; // Alerta dinámica en la parte superior
    private Timer timer; // Temporizador para simular cambios en el estado
    private int estadoIndex; // Índice del estado actual en la simulación
    private String[] estados = {"En almacén", "En tránsito", "Entregado"}; // Estados simulados
    private Point[] posiciones = { // Coordenadas de las ciudades
            new Point(100, 300), // En almacén (Guadalajara)
            new Point(300, 200), // En tránsito (Ciudad de México)
            new Point(500, 100)  // Entregado (Cancún)
    };

    private JPanel mapaPanel;
    private JLabel marcador; // Representa el marcador en el mapa

    public SeguimientoEnvioGUI(Envio envio) {
        // Configuración de la ventana
        setTitle("Seguimiento del Envío - ID " + envio.getEnvioId());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Panel principal
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Alerta dinámica
        alertaLabel = new JLabel("Estado actual: " + estados[0], SwingConstants.CENTER);
        alertaLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        alertaLabel.setOpaque(true);
        alertaLabel.setBackground(new Color(98, 121, 176));
        alertaLabel.setForeground(Color.WHITE);
        alertaLabel.setPreferredSize(new Dimension(800, 40));

        // Panel del mapa
        mapaPanel = crearMapaPanel();

        // Botón para detener el seguimiento
        JButton detenerButton = new JButton("Detener Seguimiento");
        detenerButton.addActionListener(e -> detenerSeguimiento());

        // Agregar componentes al panel principal
        mainPanel.add(alertaLabel, BorderLayout.NORTH); // Agregar alerta arriba
        mainPanel.add(mapaPanel, BorderLayout.CENTER); // Mapa en el centro
        mainPanel.add(detenerButton, BorderLayout.SOUTH); // Botón abajo

        add(mainPanel);

        // Iniciar simulación de seguimiento
        iniciarSimulacion(envio);

        setVisible(true);
    }

    /**
     * Crea el panel del mapa.
     */
    private JPanel crearMapaPanel() {
        JPanel panel = new JPanel(null);
        panel.setPreferredSize(new Dimension(800, 400));
        panel.setBackground(new Color(173, 216, 230));

        // Etiquetas para ciudades (puntos de referencia)
        JLabel guadalajaraLabel = new JLabel("Guadalajara");
        guadalajaraLabel.setBounds(100, 320, 100, 20);
        panel.add(guadalajaraLabel);

        JLabel cdmxLabel = new JLabel("Ciudad de México");
        cdmxLabel.setBounds(300, 220, 150, 20);
        panel.add(cdmxLabel);

        JLabel cancunLabel = new JLabel("Cancún");
        cancunLabel.setBounds(500, 120, 100, 20);
        panel.add(cancunLabel);

        // Marcador inicial
        marcador = new JLabel("📦");
        marcador.setBounds(posiciones[0].x, posiciones[0].y, 50, 50);
        marcador.setFont(new Font("SansSerif", Font.PLAIN, 24));
        panel.add(marcador);

        return panel;
    }

    /**
     * Inicia la simulación del seguimiento del envío.
     */
    private void iniciarSimulacion(Envio envio) {
        estadoIndex = 0; // Iniciar desde el primer estado

        // Temporizador para cambiar el estado cada 3 segundos
        timer = new Timer(3000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (estadoIndex < estados.length) {
                    // Actualizar el estado del envío
                    // envio.setEstado(estados[estadoIndex]);
                    envio.getEstadoEnvio().setNombreEstado(estados[estadoIndex]);
                    alertaLabel.setText("Estado actual: " + envio.getEstadoEnvio().getNombreEstado());
                    alertaLabel.revalidate(); // Forzar actualización
                    alertaLabel.repaint();

                    // Mover el marcador
                    moverMarcador(marcador, posiciones[estadoIndex]);

                    // Pasar al siguiente estado
                    estadoIndex++;

                    // Si llegamos al último estado, detener el temporizador
                    if (estadoIndex == estados.length) {
                        detenerSeguimiento();
                    }
                }
            }
        });
        timer.start();
    }

    /**
     * Mueve el marcador a una nueva posición con animación.
     *
     * @param marcador El JLabel del marcador
     * @param destino  La posición final del marcador
     */
    private void moverMarcador(JLabel marcador, Point destino) {
        Thread animacion = new Thread(() -> {
            Point inicio = marcador.getLocation();
            int pasos = 20; // Cantidad de pasos para animar
            int delay = 50; // Tiempo entre pasos (en milisegundos)

            for (int i = 1; i <= pasos; i++) {
                int x = inicio.x + (destino.x - inicio.x) * i / pasos;
                int y = inicio.y + (destino.y - inicio.y) * i / pasos;
                marcador.setLocation(x, y);

                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            marcador.setLocation(destino);
        });
        animacion.start();
    }

    /**
     * Detiene el seguimiento del envío.
     */
    private void detenerSeguimiento() {
        if (timer != null) {
            timer.stop();
        }
        alertaLabel.setText("Seguimiento finalizado.");
        alertaLabel.setBackground(new Color(76, 175, 80));
    }

    public static void main(String[] args) {
        // Ejemplo de uso
        Paquete paquete = new Paquete(1, "Electrodoméstico", 15.5, "50x40x30 cm", 2000);
        // Envio envio = new Envio(1, "Guadalajara", "Ciudad de México", paquete, 800, new java.util.Date());
        Envio envio = new Envio(1, null, null, null, null, null, new java.util.Date(), null, 0, paquete, null);
        new SeguimientoEnvioGUI(envio);
    }
}
