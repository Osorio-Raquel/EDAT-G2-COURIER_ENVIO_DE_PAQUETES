import java.util.LinkedList;

public class EstadoEnvio {
    private LinkedList<String> estados;

    public EstadoEnvio() {
        this.estados = new LinkedList<>();
        // Inicialmente, el estado se configura como "Pendiente"
        this.estados.add("Pendiente");
    }

    // Método para agregar un nuevo estado al envío
    public void agregarEstado(String nuevoEstado) {
        estados.add(nuevoEstado);
    }

    // Método para obtener el estado actual (último en la lista)
    public String getEstadoActual() {
        return estados.getLast();
    }

    // Método para mostrar todos los estados del envío
    public void mostrarHistorialEstados() {
        System.out.println("Historial de Estados del Envío:");
        for (String estado : estados) {
            System.out.println("- " + estado);
        }
    }
}
