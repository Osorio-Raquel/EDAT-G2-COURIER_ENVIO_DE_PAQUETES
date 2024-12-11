import java.util.PriorityQueue;
import java.util.Date;

public class GestionNotificaciones {
    private PriorityQueue<Notificacion> notificaciones; // Cola de prioridad para notificaciones

    // Constructor
    public GestionNotificaciones() {
        this.notificaciones = new PriorityQueue<>();
    }

    // Crear una nueva notificacion
    public void crearNotificacion(String tipo, String mensaje, int prioridad) {
        Notificacion nuevaNotificacion = new Notificacion(tipo, mensaje, new Date(), false, prioridad);
        notificaciones.add(nuevaNotificacion);
    }

    // Obtener y eliminar la notificacion más prioritaria
    public Notificacion obtenerSiguienteNotificacion() {
        return notificaciones.poll(); // Retorna y elimina la notificacion con mayor prioridad
    }

    // Marcar una notificacion como leída
    public void marcarNotificacionComoLeida(Notificacion notificacion) {
        notificacion.marcarComoLeida();
    }

    // Mostrar todas las notificaciones pendientes
    public void mostrarNotificacionsPendientes() {
        System.out.println("Notificacions pendientes:");
        for (Notificacion notificacion : notificaciones) {
            System.out.println(notificacion);
        }
    }

    // Obtener el número de notificaciones pendientes
    public int obtenerNumeroNotificacionsPendientes() {
        return notificaciones.size();
    }

    public void verificarEstadoEnvio(Envio envio) {
        String estado = envio.getEstadoEnvio().getNombreEstado();
        crearNotificacion("Estado de envío", "El estado del envío es: " + estado, 1);
    }
    // Devuelve la cola de prioridad de notificaciones
    public PriorityQueue<Notificacion> getNotificacions() {
        return notificaciones;
    }

    // Obtener notificaciones leídas
    public PriorityQueue<Notificacion> obtenerNotificacionsLeidas() {
        PriorityQueue<Notificacion> notificacionesLeidas = new PriorityQueue<>();
        for (Notificacion notificacion : notificaciones) {
            if (notificacion.isLeida()) {
                notificacionesLeidas.add(notificacion);
            }
        }
        return notificacionesLeidas;
    }

    //Obtener notificaciones no leídas
    public PriorityQueue<Notificacion> obtenerNotificacionsNoLeidas() {
        PriorityQueue<Notificacion> notificacionesNoLeidas = new PriorityQueue<>();
        for (Notificacion notificacion : notificaciones) {
            if (!notificacion.isLeida()) {
                notificacionesNoLeidas.add(notificacion);
            }
        }
        return notificacionesNoLeidas;
    }

    // Obtener notificaciones por prioridad
    public PriorityQueue<Notificacion> obtenerNotificacionesPorPrioridad(int prioridad) {
        PriorityQueue<Notificacion> notificacionesPorPrioridad = new PriorityQueue<>();
        for (Notificacion notificacion : notificaciones) {
            if (notificacion.getPrioridad() == prioridad) {
                notificacionesPorPrioridad.add(notificacion);
            }
        }
        return notificacionesPorPrioridad;
    }
}
