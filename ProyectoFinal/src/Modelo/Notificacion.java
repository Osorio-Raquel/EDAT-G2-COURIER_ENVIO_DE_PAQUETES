import java.util.Date;

public class Notificacion implements Comparable<Notificacion> {
    private String tipo; // Tipo de alerta (ejemplo: "Retraso", "Entrega exitosa")
    private String mensaje; // Mensaje descriptivo de la alerta
    private Date fechaGeneracion; // Fecha y hora de la alerta
    private boolean leida; // Indica si la alerta ya fue procesada o leída
    private int prioridad; // Nivel de prioridad (menor valor = mayor prioridad)

    public Notificacion(String tipo, String mensaje, Date fechaGeneracion, boolean leida, int prioridad) {
        this.tipo = tipo;
        this.mensaje = mensaje;
        this.fechaGeneracion = fechaGeneracion;
        this.leida = leida;
        this.prioridad = prioridad;
    }

    // Getters y Setters
    public String getTipo() {
        return tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public boolean isLeida() {
        return leida;
    }

    public void marcarComoLeida() {
        this.leida = true;
    }

    public int getPrioridad() {
        return prioridad;
    }

    // Implementación de compareTo para la cola de prioridad
    @Override
    public int compareTo(Notificacion otra) {
        return Integer.compare(this.prioridad, otra.prioridad); // Orden por prioridad
    }

    @Override
    public String toString() {
        return "[" + tipo + "] " + mensaje + " (Prioridad: " + prioridad + ", Fecha: " + fechaGeneracion + ")";
    }
}
