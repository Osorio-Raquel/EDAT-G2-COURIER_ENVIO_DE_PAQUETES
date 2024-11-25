public class Estado {
    private int estadoId;
    private String nombreEstado;
    private Pais pais; // Relación con la clase País

    public Estado(int estadoId, String nombreEstado, Pais pais) {
        this.estadoId = estadoId;
        this.nombreEstado = nombreEstado;
        this.pais = pais;
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Estado{" +
                "estadoId=" + estadoId +
                ", nombreEstado='" + nombreEstado + '\'' +
                ", pais=" + pais.getNombrePais() +
                '}';
    }
}
