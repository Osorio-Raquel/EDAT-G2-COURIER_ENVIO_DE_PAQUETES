import java.util.Map;

public class Direccion {
    private int direccionId;
    private Ciudad ciudad;
    private String calle;
    private String numeroExt;
    private String numeroInt;
    private String codigoPostal;
    Map<String, Direccion> direccionesPorCiudad;
    
    public Direccion(int direccionId, Ciudad ciudad, String calle, String numeroExt, String numeroInt,
            String codigoPostal) {
        this.direccionId = direccionId;
        this.ciudad = ciudad;
        this.calle = calle;
        this.numeroExt = numeroExt;
        this.numeroInt = numeroInt;
        this.codigoPostal = codigoPostal;
    }

    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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

    @Override
    public String toString() {
        return "Direccion [direccionId=" + direccionId + ", ciudad=" + ciudad + ", calle=" + calle + ", numeroExt="
                + numeroExt + ", numeroInt=" + numeroInt + ", codigoPostal=" + codigoPostal + "]";
    }
}
