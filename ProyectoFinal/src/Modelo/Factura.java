import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Factura {
    private int facturaId;
    private Cliente cliente;
    private Date fechaEmision;
    private double total;
    private String metodoPago;
    private String estado;
    private List<DetalleFactura> detalles;

    public Factura(int facturaId, Cliente cliente, Date fechaEmision, String metodoPago) {
        this.facturaId = facturaId;
        this.cliente = cliente;
        this.fechaEmision = fechaEmision;
        this.total = 0.0;
        this.metodoPago = metodoPago;
        this.estado = "Pendiente";
        this.detalles = new ArrayList<>();
    }

    public int getFacturaId() {
        return facturaId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public double getTotal() {
        return total;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void agregarDetalle(DetalleFactura detalle) {
        detalles.add(detalle);
        total += detalle.getSubtotal();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Factura ID: %d, Cliente: %s, Total: $%.2f, Estado: %s\n", facturaId, cliente.getNombre(), total, estado));
        sb.append("Detalles:\n");
        for (DetalleFactura detalle : detalles) {
            sb.append("  ").append(detalle).append("\n");
        }
        return sb.toString();
    }
}
