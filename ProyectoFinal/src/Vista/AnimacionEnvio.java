package Vista;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class AnimacionEnvio extends JFrame {
    // Variables para la animación
    private int currentPointIndex = 0; // Índice del punto actual en la ruta
    private final List<Point> rutaEnvio = new ArrayList<>(); // Lista de puntos que representa la ruta
    private int step = 5; // Aumentamos la velocidad del movimiento
    private String notificacion = "Envío en progreso..."; // Notificación inicial
    private boolean enTrafic = false; // Estado para simular tráfico
    private long tiempoEspera = 0; // Temporizador de espera en el tráfico

    public AnimacionEnvio() {
        // Configuración de la ventana
        setTitle("Mapa de Envío");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centrar la ventana

        // Definir la ruta del envío (lista de puntos)
        definirRutaEnvio();

        // Panel donde se dibujará el mapa
        JPanel panelMapa = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Llamamos al método de dibujo del mapa
                dibujarMapa(g);
                moverEnvio(g);
                mostrarNotificaciones(g);
            }
        };

        // Agregamos el panel al contenedor
        getContentPane().add(panelMapa);

        // Iniciar animación con un Timer
        Timer timer = new Timer(30, e -> {
            // Simular tráfico en algún punto de la ruta
            if (currentPointIndex == 4 && !enTrafic) {
                enTrafic = true; // Entramos en tráfico
                notificacion = "Tráfico en ruta. Esperando...";
                tiempoEspera = System.currentTimeMillis(); // Iniciamos el tiempo de espera
            }

            // Si hemos estado esperando más de 4 segundos, podemos continuar
            if (enTrafic && System.currentTimeMillis() - tiempoEspera > 4000) {
                enTrafic = false;
                notificacion = "Envío en progreso..."; // Continuamos el envío
            }

            // Mover el envío
            if (currentPointIndex < rutaEnvio.size() - 1 && !enTrafic) {
                Point current = rutaEnvio.get(currentPointIndex);
                Point next = rutaEnvio.get(currentPointIndex + 1);

                // Calculamos la diferencia en X y Y
                int dx = next.x - current.x;
                int dy = next.y - current.y;

                // Movimiento más suave utilizando un paso grande
                if (Math.abs(dx) > Math.abs(dy)) {
                    current.x += Integer.signum(dx) * step; // Mover horizontalmente
                } else {
                    current.y += Integer.signum(dy) * step; // Mover verticalmente
                }

                // Si llegamos al siguiente punto, pasamos al siguiente
                if (current.x == next.x && current.y == next.y) {
                    currentPointIndex++;
                    if (currentPointIndex == 1) {
                        notificacion = "Curva suave a la derecha."; // Notificación de primer giro
                    }
                    if (currentPointIndex == 2) {
                        notificacion = "Viraje a la izquierda."; // Notificación del segundo giro
                    }
                    if (currentPointIndex == 3) {
                        notificacion = "Recorriendo zona con tráfico."; // Notificación de tráfico
                    }
                    if (currentPointIndex == 6) {
                        notificacion = "Casi llegamos a la Sucursal 2."; // Antes de llegar
                    }
                }
            }
            repaint(); // Redibujar el mapa y el envío
        });
        timer.start();
    }

    private void definirRutaEnvio() {
        // Definir puntos que simulan la ruta del envío
        rutaEnvio.add(new Point(100, 400)); // Sucursal 1 (punto de inicio)
        rutaEnvio.add(new Point(200, 350)); // Curva 1 a la derecha
        rutaEnvio.add(new Point(300, 200)); // Curva 2 a la izquierda
        rutaEnvio.add(new Point(400, 100)); // Curva 3 a la derecha
        rutaEnvio.add(new Point(500, 150)); // Zona con tráfico (tiempo de espera)
        rutaEnvio.add(new Point(600, 200)); // Curva final
        rutaEnvio.add(new Point(700, 250)); // Sucursal 2 (punto final)
    }

    private void dibujarMapa(Graphics g) {
        // Convertir el objeto Graphics a Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // Definir colores y grosores
        g2d.setColor(new Color(200, 200, 200)); // Color de fondo de calles
        g2d.fillRect(0, 0, getWidth(), getHeight()); // Fondo gris claro

        // Dibujo de las calles (rectángulos y líneas)
        g2d.setColor(new Color(100, 100, 100)); // Color de calles (gris oscuro)
        g2d.fillRect(50, 100, 700, 50); // Calle horizontal principal
        g2d.fillRect(100, 50, 50, 500); // Calle vertical principal

        // Dibujo de edificios como rectángulos
        g2d.setColor(new Color(200, 100, 100)); // Color de edificios (rojo claro)
        g2d.fillRect(150, 200, 100, 100); // Edificio 1
        g2d.fillRect(250, 300, 120, 100); // Edificio 2
        g2d.fillRect(400, 150, 150, 150); // Edificio 3

        // Dibujo de la primera sucursal (punto de inicio)
        g2d.setColor(new Color(0, 102, 204)); // Color de la sucursal
        g2d.fillRect(95, 390, 120, 120); // Sucursal 1 (punto de inicio)
        g2d.setColor(Color.WHITE);
        g2d.drawString("Sucursal 1", 105, 430);

        // Dibujo de la segunda sucursal (punto de final)
        g2d.setColor(new Color(0, 102, 204)); // Color de la sucursal
        g2d.fillRect(680, 180, 120, 120); // Sucursal 2 (punto final)
        g2d.setColor(Color.WHITE);
        g2d.drawString("Sucursal 2", 690, 220);

        // Dibujo de la ruta como una línea que conecta los puntos
        g2d.setColor(Color.RED);
        for (int i = 0; i < rutaEnvio.size() - 1; i++) {
            Point start = rutaEnvio.get(i);
            Point end = rutaEnvio.get(i + 1);
            g2d.drawLine(start.x + 60, start.y + 60, end.x + 60, end.y + 60); // Ajustamos para que se dibuje desde el centro de cada punto
        }
    }

    private void moverEnvio(Graphics g) {
        // Convertir el objeto Graphics a Graphics2D
        Graphics2D g2d = (Graphics2D) g;

        // Dibujo del vehículo de envío
        g2d.setColor(new Color(255, 0, 0)); // Color del vehículo
        Point currentPoint = rutaEnvio.get(currentPointIndex);
        g2d.fillOval(currentPoint.x + 60, currentPoint.y + 60, 20, 20); // Dibujo del vehículo
    }

    private void mostrarNotificaciones(Graphics g) {
        // Mostrar la notificación en pantalla
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        g.drawString(notificacion, 20, 20); // Ubicación de la notificación
    }

}
