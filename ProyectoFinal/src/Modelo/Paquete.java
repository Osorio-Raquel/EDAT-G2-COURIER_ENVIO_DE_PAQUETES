public class Paquete {
    private String origen;
    private double peso;
    private String destino;

    // Constructor modificado para aceptar String, double, String
    public Paquete(String origen, double peso, String destino) {
        this.origen = origen;
        this.peso = peso;
        this.destino = destino;
    }

    // Métodos de acceso
    public String getOrigen() {
        return origen;
    }

    public double getPeso() {
        return peso;
    }

    public String getDestino() {
        return destino;
    }
}
