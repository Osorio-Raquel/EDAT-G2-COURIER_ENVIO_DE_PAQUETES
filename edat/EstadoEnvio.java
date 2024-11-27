
public class EstadoEnvio {
    private int estadoId;
    private String nombreEstado;

    public EstadoEnvio(int estadoId, String nombreEstado) {
        this.estadoId = estadoId;
        this.nombreEstado = nombreEstado;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public String getNombreEstado() {
        return nombreEstado;
    }

    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    //buscar por nombreEstado
    public boolean equals(Object obj) {
        if (obj instanceof EstadoEnvio) {
            EstadoEnvio estadoEnvio = (EstadoEnvio) obj;
            return this.nombreEstado.equals(estadoEnvio.getNombreEstado());
        }
        return false;
    }
}