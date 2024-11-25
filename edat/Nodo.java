class Nodo {
    private String ciudad;
    private double distancia;

    public Nodo(String ciudad, double distancia) {
        this.ciudad = ciudad;
        this.distancia = distancia;
    }

    public String getCiudad() {
        return ciudad;
    }

    public double getDistancia() {
        return distancia;
    }
}
