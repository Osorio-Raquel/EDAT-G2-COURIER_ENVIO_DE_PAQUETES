import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Administrador {
    private int administradorId;
    private String nombre;
    private String email;
    private String telefono;
    private Date fechaRegistro;
    
    // Constructor con parámetros
    public Administrador(int administradorId, String nombre, String email, String telefono, Date fechaRegistro) {
        this.administradorId = administradorId;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.fechaRegistro = fechaRegistro;
    }

    // Constructor sin parámetros
    public Administrador(){
        this.administradorId = 0;
        this.nombre = null;
        this.email = null;
        this.telefono = null;
        this.fechaRegistro = null;
    }

    // Método para seleccionar todos los administradores de la BD
    public ArrayList<Administrador> selectAdministradores(){
        ArrayList<Administrador> administradores = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM administradores");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_administrador = rs.getInt("administrador_id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                Date fecha_registro = new Date(rs.getTimestamp("fecha_registro").getTime());

                administradores.add(new Administrador(id_administrador, nombre, email, telefono, fecha_registro));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return administradores;
    }

    // Método para seleccionar un administrador específico de la BD
    public Administrador selectAdministradorById(int administrador_id){
        Administrador a = new Administrador();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM administradores WHERE administrador_id = ?");
            stmt.setInt(1, administrador_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_administrador = rs.getInt("administrador_id");
                String nombre = rs.getString("nombre_administrador");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                Date fecha_registro = new Date(rs.getTimestamp("fecha_registro").getTime());

                a = new Administrador(id_administrador, nombre, email, telefono, fecha_registro);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return a;
    }

    // Método para insertar un administrador a la BD
    public void insertAdministrador(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO administradores(nombre, email, telefono, fecha_registro) VALUES(?, ?, ?, ?)");
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.email);
            stmt.setString(3, this.telefono);
            stmt.setTimestamp(4, new java.sql.Timestamp(this.fechaRegistro.getTime()));
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar un administrador en la BD
    public void updateAdministrador(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE administradores SET nombre = ? AND email = ? AND telefono = ? AND fecha_registro = ? WHERE administrador_id = ?");
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.email);
            stmt.setString(3, this.telefono);
            stmt.setTimestamp(4, new java.sql.Timestamp(this.fechaRegistro.getTime()));
            stmt.setInt(5, this.administradorId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un administrador en la BD
    public void deleteAdministrador(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM administradores WHERE administrador_id = ?");
            stmt.setInt(1, this.administradorId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Setters y getters
    public int getAdministradorId() {
        return administradorId;
    }

    public void setAdministradorId(int administradorId) {
        this.administradorId = administradorId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    // Método toString
    @Override
    public String toString() {
        return "Administrador [administradorId=" + administradorId + ", nombre=" + nombre + ", email=" + email
                + ", telefono=" + telefono + ", fechaRegistro=" + fechaRegistro + "]";
    }
}
