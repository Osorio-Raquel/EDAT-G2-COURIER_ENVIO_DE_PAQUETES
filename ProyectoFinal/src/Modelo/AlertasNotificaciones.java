package Modelo;
import java.util.PriorityQueue;

public class AlertasNotificaciones {
    private PriorityQueue<Alerta> alertas;

    public AlertasNotificaciones() {
        alertas = new PriorityQueue<>();
    }

    public void agregarAlerta(Alerta alerta) {
        alertas.add(alerta);
    }

    public Alerta obtenerProximaAlerta() {
        return alertas.poll();
    }
}

class Alerta implements Comparable<Alerta> {
    private String mensaje;
    private int prioridad; // menor número indica mayor prioridad

    public Alerta(String mensaje, int prioridad) {
        this.mensaje = mensaje;
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(Alerta o) {
        return Integer.compare(this.prioridad, o.prioridad);
    }
}
