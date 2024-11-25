public class Ciudad {
    private int ciudadId;
    private String nombreCiudad;
    private Estado estado; // Relación con la clase Estado

    public Ciudad(int ciudadId, String nombreCiudad, Estado estado) {
        this.ciudadId = ciudadId;
        this.nombreCiudad = nombreCiudad;
        this.estado = estado;
    }

    public int getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(int ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Ciudad{" +
                "ciudadId=" + ciudadId +
                ", nombreCiudad='" + nombreCiudad + '\'' +
                ", estado=" + estado.getNombreEstado() +
                '}';
    }
}
