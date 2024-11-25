class Arista {
    private String destino;
    private double distancia;

    public Arista(String destino, double distancia) {
        this.destino = destino;
        this.distancia = distancia;
    }

    public String getDestino() {
        return destino;
    }

    public double getDistancia() {
        return distancia;
    }

    @Override
    public String toString() {
        return String.format("Destino: %s, Distancia: %.2f km", destino, distancia);
    }
}
