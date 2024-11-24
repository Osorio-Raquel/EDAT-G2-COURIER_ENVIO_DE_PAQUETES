public class Sucursal {
    private int sucursalId;
    private String nombre;
    private Direccion direccion;
    private String telefono;
    private Administrador administrador;
    
    public Sucursal(int sucursalId, String nombre, Direccion direccion, String telefono, Administrador administrador) {
        this.sucursalId = sucursalId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.administrador = administrador;
    }

    public int getSucursalId() {
        return sucursalId;
    }

    public void setSucursalId(int sucursalId) {
        this.sucursalId = sucursalId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "Sucursal [sucursalId=" + sucursalId + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono="
                + telefono + ", administrador=" + administrador + "]";
    }
}
