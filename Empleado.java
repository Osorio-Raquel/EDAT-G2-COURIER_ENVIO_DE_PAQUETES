import java.sql.Date;

public class Empleado {
    private int empleadoId;
    private String nomnbre;
    private String email;
    private String telefono;
    private String puesto;
    private Date fechaContratacion;
    private Administrador administrador;
    
    public Empleado(int empleadoId, String nomnbre, String email, String telefono, String puesto,
            Date fechaContratacion, Administrador administrador) {
        this.empleadoId = empleadoId;
        this.nomnbre = nomnbre;
        this.email = email;
        this.telefono = telefono;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
        this.administrador = administrador;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNomnbre() {
        return nomnbre;
    }

    public void setNomnbre(String nomnbre) {
        this.nomnbre = nomnbre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Date getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(Date fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    @Override
    public String toString() {
        return "Empleado [empleadoId=" + empleadoId + ", nomnbre=" + nomnbre + ", email=" + email + ", telefono="
                + telefono + ", puesto=" + puesto + ", fechaContratacion=" + fechaContratacion + ", administrador="
                + administrador + "]";
    }
}