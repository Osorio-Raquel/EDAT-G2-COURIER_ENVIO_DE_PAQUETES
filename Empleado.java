import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Empleado {
    private int empleadoId;
    private String nombre;
    private String email;
    private String telefono;
    private String puesto;
    private Date fechaContratacion;
    private Administrador administrador;
    
    // Constructor con parámetros
    public Empleado(int empleadoId, String nomnbre, String email, String telefono, String puesto,
            Date fechaContratacion, Administrador administrador) {
        this.empleadoId = empleadoId;
        this.nombre = nomnbre;
        this.email = email;
        this.telefono = telefono;
        this.puesto = puesto;
        this.fechaContratacion = fechaContratacion;
        this.administrador = administrador;
    }

    // Constructor sin parámetros
    public Empleado(){
        this.empleadoId = 0;
        this.nombre = null;
        this.email = null;
        this.telefono = null;
        this.puesto = null;
        this.fechaContratacion = null;
        this.administrador = null;
    }

    // Método para seleccionar todos los empleados de la BD
    public ArrayList<Empleado> selectEmpleados(){
        ArrayList<Empleado> empleados = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM empleados");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_empleado = rs.getInt("empleado_id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String puesto = rs.getString("puesto");
                Date fecha_contratacion = new Date(rs.getTimestamp("fecha_contratacion").getTime());
                int id_administrador = rs.getInt("administrador_id");

                empleados.add(new Empleado(id_empleado, nombre, email, telefono, puesto, fecha_contratacion, this.administrador.selectAdministradorById(id_administrador)));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return empleados;
    }

    // Método para seleccionar un empleado específico de la BD
    public Empleado selectEmpleadoById(int empleado_id){
        Empleado em = new Empleado();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM empleados WHERE empleado_id = ?");
            stmt.setInt(1, empleado_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_empleado = rs.getInt("empleado_id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                String puesto = rs.getString("puesto");
                Date fecha_contratacion = new Date(rs.getTimestamp("fecha_contratacion").getTime());
                int id_administrador = rs.getInt("administrador_id");

                em = new Empleado(id_empleado, nombre, email, telefono, puesto, fecha_contratacion, this.administrador.selectAdministradorById(id_administrador));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return em;
    }

    // Método para insertar un empleado a la BD
    public void insertEmpleado(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO empleados(nombre, email, telefono, puesto, fecha_contratacion, administrador_id) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.email);
            stmt.setString(3, this.telefono);
            stmt.setString(4, this.puesto);
            stmt.setTimestamp(5, new java.sql.Timestamp(this.fechaContratacion.getTime()));
            stmt.setInt(6, this.administrador.getAdministradorId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar un empleado en la BD
    public void updateEmpleado(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE empleados SET nombre = ?, email = ?, telefono = ?, puesto = ?, fecha_contratacion = ?, administrador_id = ? WHERE empleado_id = ?");
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.email);
            stmt.setString(3, this.telefono);
            stmt.setString(4, this.puesto);
            stmt.setTimestamp(5, new java.sql.Timestamp(this.fechaContratacion.getTime()));
            stmt.setInt(6, this.administrador.getAdministradorId());
            stmt.setInt(7, this.empleadoId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un empleado en la BD
    public void deleteEmpleado(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM empleados WHERE empleado_id = ?");
            stmt.setInt(1, this.empleadoId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    // Setters y getters
    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nomnbre) {
        this.nombre = nomnbre;
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

    // Método toString
    @Override
    public String toString() {
        return "Empleado [empleadoId=" + empleadoId + ", nomnbre=" + nombre + ", email=" + email + ", telefono="
                + telefono + ", puesto=" + puesto + ", fechaContratacion=" + fechaContratacion + ", administrador="
                + administrador + "]";
    }
}
