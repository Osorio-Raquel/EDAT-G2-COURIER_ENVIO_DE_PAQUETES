
 import java.util.Date;

public class SeguimientoEnvio {
    private int seguimientoId;
    private Envio envio;
    private EstadoSeguimiento estadoSeguimiento;
    private String ubicacionActual;
    private Date fechaActualizacion;

    public SeguimientoEnvio(int seguimientoId, Envio envio, EstadoSeguimiento estadoSeguimiento, String ubicacionActual, Date fechaActualizacion) {
        this.seguimientoId = seguimientoId;
        this.envio = envio;
        this.estadoSeguimiento = estadoSeguimiento;
        this.ubicacionActual = ubicacionActual;
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getSeguimientoId() {
        return seguimientoId;
    }

    public void setSeguimientoId(int seguimientoId) {
        this.seguimientoId = seguimientoId;
    }

    public Envio getEnvio() {
        return envio;
    }

    public void setEnvio(Envio envio) {
        this.envio = envio;
    }

    public EstadoSeguimiento getEstadoSeguimiento() {
        return estadoSeguimiento;
    }

    public void setEstadoSeguimiento(EstadoSeguimiento estadoSeguimiento) {
        this.estadoSeguimiento = estadoSeguimiento;
    }

    public String getUbicacionActual() {
        return ubicacionActual;
    }

    public void setUbicacionActual(String ubicacionActual) {
        this.ubicacionActual = ubicacionActual;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

}