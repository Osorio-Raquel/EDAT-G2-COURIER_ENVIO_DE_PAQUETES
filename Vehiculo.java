import java.sql.Date;

public class Vehiculo {
    private int vehiculoId;
    private String tipo;
    private String modelo;
    private String matricula;
    private double capacidadKg;
    private Date fechaAdquisicion;
    private EstadoVehiculo estadoVehiculo;

    public Vehiculo(int vehiculoId, String tipo, String modelo, String matricula, double capacidadKg,
            Date fechaAdquisicion, EstadoVehiculo estadoVehiculo) {
        this.vehiculoId = vehiculoId;
        this.tipo = tipo;
        this.modelo = modelo;
        this.matricula = matricula;
        this.capacidadKg = capacidadKg;
        this.fechaAdquisicion = fechaAdquisicion;
        this.estadoVehiculo = estadoVehiculo;
    }
    
    public int getVehiculoId() {
        return vehiculoId;
    }
    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public double getCapacidadKg() {
        return capacidadKg;
    }
    public void setCapacidadKg(double capacidadKg) {
        this.capacidadKg = capacidadKg;
    }
    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
    public EstadoVehiculo getEstadoVehiculo() {
        return estadoVehiculo;
    }
    public void setEstadoVehiculo(EstadoVehiculo estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }
    @Override
    public String toString() {
        return "Vehiculo [vehiculoId=" + vehiculoId + ", tipo=" + tipo + ", modelo=" + modelo + ", matricula="
                + matricula + ", capacidadKg=" + capacidadKg + ", fechaAdquisicion=" + fechaAdquisicion
                + ", estadoVehiculo=" + estadoVehiculo + "]";
    }
}
