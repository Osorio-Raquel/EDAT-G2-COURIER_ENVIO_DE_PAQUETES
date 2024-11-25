public class Direccion {
    private int direccionId;
    private String calle;
    private String numeroExt;
    private String numeroInt;
    private String codigoPostal;
    private Ciudad ciudad;

    public Direccion(int direccionId, String calle, String numeroExt, String numeroInt, String codigoPostal, Ciudad ciudad) {
        this.direccionId = direccionId;
        this.calle = calle;
        this.numeroExt = numeroExt;
        this.numeroInt = numeroInt;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
    }

    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExt() {
        return numeroExt;
    }

    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    public String getNumeroInt() {
        return numeroInt;
    }

    public void setNumeroInt(String numeroInt) {
        this.numeroInt = numeroInt;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Direccion{" +
                "direccionId=" + direccionId +
                ", calle='" + calle + '\'' +
                ", numeroExt='" + numeroExt + '\'' +
                ", numeroInt='" + numeroInt + '\'' +
                ", codigoPostal='" + codigoPostal + '\'' +
                ", ciudad=" + ciudad +
                '}';
    }
}
