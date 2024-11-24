import java.sql.Date;

public class Inventario {
    private int inventarioId;
    private Sucursal sucursal;
    private Paquete paquete;
    private int cantidad;
    private Date fechaEntrada;
    
    public Inventario(int inventarioId, Sucursal sucursal, Paquete paquete, int cantidad, Date fechaEntrada) {
        this.inventarioId = inventarioId;
        this.sucursal = sucursal;
        this.paquete = paquete;
        this.cantidad = cantidad;
        this.fechaEntrada = fechaEntrada;
    }

    public int getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(int inventarioId) {
        this.inventarioId = inventarioId;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
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

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Override
    public String toString() {
        return "Inventario [inventarioId=" + inventarioId + ", sucursal=" + sucursal + ", paquete=" + paquete
                + ", cantidad=" + cantidad + ", fechaEntrada=" + fechaEntrada + "]";
    }
}
