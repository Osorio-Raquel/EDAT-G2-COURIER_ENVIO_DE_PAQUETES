public class Envio {
    private String numeroSeguimiento;
    private String origen;
    private double peso;
    private String destino;
    private Paquete paquete;

    // Modificar el constructor para que acepte Paquete
    public Envio(String numeroSeguimiento, String origen, double peso, String destino, Paquete paquete) {
        this.numeroSeguimiento = numeroSeguimiento;
        this.origen = origen;
        this.peso = peso;
        this.destino = destino;
        this.paquete = paquete;
    }

    // Métodos getters
    public String getNumeroSeguimiento() {
        return numeroSeguimiento;
    }

    public String getEstadoEnvio() {
        return "En tránsito";  // Ejemplo de estado
    }

    public Paquete getPaquete() {
        return paquete;
    }
}
