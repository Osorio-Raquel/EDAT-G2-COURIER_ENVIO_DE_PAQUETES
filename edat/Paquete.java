public class Paquete {
    private int paqueteId;
    private String descripcion;
    private double pesoKg;
    private String dimensiones;
    private double valorDeclarado;
    private Cliente cliente;

    public Paquete(int paqueteId, String descripcion, double pesoKg, String dimensiones, double valorDeclarado) {
        this.paqueteId = paqueteId;
        this.descripcion = descripcion;
        this.pesoKg = pesoKg;
        this.dimensiones = dimensiones;
        this.valorDeclarado = valorDeclarado;
    }

    public int getPaqueteId() {
        return paqueteId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public double getValorDeclarado() {
        return valorDeclarado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return String.format("Paquete ID: %d, Descripción: %s, Peso: %.2f kg, Dimensiones: %s, Valor Declarado: $%.2f",
                paqueteId, descripcion, pesoKg, dimensiones, valorDeclarado);
    }
}
