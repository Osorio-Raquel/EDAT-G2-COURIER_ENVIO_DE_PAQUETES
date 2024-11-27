
import java.util.Date;

public class Envio {
    private int envioId;
    private Cliente cliente;
    private Empleado empleado;
    private Vehiculo vehiculo;
    private Ruta ruta;
    private EstadoEnvio estadoEnvio;
    private Date fechaEnvio;
    private Date fechaEntrega;
    private double costo;
    private Paquete paquete;
    private Direccion direccion;

    public Envio(int envioId, Cliente cliente, Empleado empleado, Vehiculo vehiculo, Ruta ruta, EstadoEnvio estadoEnvio, Date fechaEnvio, Date fechaEntrega, double costo, Paquete paquete, Direccion direccion) {
        this.envioId = envioId;
        this.cliente = cliente;
        this.empleado = empleado;
        this.vehiculo = vehiculo;
        this.ruta = ruta;
        this.estadoEnvio = estadoEnvio;
        this.fechaEnvio = fechaEnvio;
        this.fechaEntrega = fechaEntrega;
        this.costo = costo;
        this.paquete = paquete;
        this.direccion = direccion;
    }

    public int getEnvioId() {
        return envioId;
    }

    public void setEnvioId(int envioId) {
        this.envioId = envioId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    public EstadoEnvio getEstadoEnvio() {
        return estadoEnvio;
    }

    public void setEstadoEnvio(EstadoEnvio estadoEnvio) {
        this.estadoEnvio = estadoEnvio;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Date fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public boolean buscarPaquete(Inventario inventario, Paquete paquete) {
        if (inventario.getPaquete().equals(paquete)) {
            return true;
        }
        return false;
    }


}
