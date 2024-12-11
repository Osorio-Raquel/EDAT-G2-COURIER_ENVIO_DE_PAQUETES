
public class DetalleFactura {
    private int detalleId;
    private Factura factura;
    private Paquete paquete;
    private int cantidad;
    private double subtotal;

    public DetalleFactura(int detalleId, Factura factura, Paquete paquete, int cantidad, double subtotal) {
        this.detalleId = detalleId;
        this.factura = factura;
        this.paquete = paquete;
        this.cantidad = cantidad;
        this.subtotal = subtotal;
    }

    public int getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(int detalleId) {
        this.detalleId = detalleId;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

}
