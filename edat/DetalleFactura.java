public class DetalleFactura {
    private int detalleId;
    private Envio envio;
    private int cantidad;
    private double subtotal;

    public DetalleFactura(int detalleId, Envio envio, int cantidad) {
        this.detalleId = detalleId;
        this.envio = envio;
        this.cantidad = cantidad;
        this.subtotal = envio.getCosto() * cantidad;
    }

    public int getDetalleId() {
        return detalleId;
    }

    public Envio getEnvio() {
        return envio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return String.format("Detalle ID: %d, Envío: [%s], Cantidad: %d, Subtotal: $%.2f",
                detalleId, envio.getPaquete().getDescripcion(), cantidad, subtotal);
    }
}
