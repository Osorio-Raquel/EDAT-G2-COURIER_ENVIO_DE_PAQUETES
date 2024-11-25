public class Pais {
    private int paisId;
    private String nombrePais;

    public Pais(int paisId, String nombrePais) {
        this.paisId = paisId;
        this.nombrePais = nombrePais;
    }

    public int getPaisId() {
        return paisId;
    }

    public void setPaisId(int paisId) {
        this.paisId = paisId;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }

    @Override
    public String toString() {
        return "Pais{" +
                "paisId=" + paisId +
                ", nombrePais='" + nombrePais + '\'' +
                '}';
    }
}
