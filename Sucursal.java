import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Sucursal {
    private int sucursalId;
    private String nombre;
    private Direccion direccion;
    private String telefono;
    private Administrador administrador;
    
    // Constructor con parámetros
    public Sucursal(int sucursalId, String nombre, Direccion direccion, String telefono, Administrador administrador) {
        this.sucursalId = sucursalId;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.administrador = administrador;
    }

    // Constructor sin parámetros
    public Sucursal(){
        this.sucursalId = 0;
        this.nombre = null;
        this.direccion = null;
        this.telefono = null;
        this.administrador = null;
    }

    // Método para seleccionar todas las sucursales de la BD
    public ArrayList<Sucursal> selectSucursales(){
        ArrayList<Sucursal> sucursales = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM sucursales");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_sucursal = rs.getInt("sucursal_id");
                String nombre = rs.getString("nombre");
                int id_direccion = rs.getInt("direccion_id");
                String telefono = rs.getString("telefono");
                int id_administrador = rs.getInt("administrador_id");

                sucursales.add(new Sucursal(id_sucursal, nombre, this.direccion.selectDireccionById(id_direccion), telefono, this.administrador.selectAdministradorById(id_administrador)));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return sucursales;
    }

    // Método para seleccionar una sucursal específica de la BD
    public Sucursal selectSucursalById(int sucursal_id){
        Sucursal s = new Sucursal();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM sucursales WHERE sucursal_id = ?");
            stmt.setInt(1, sucursal_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_sucursal = rs.getInt("sucursal_id");
                String nombre = rs.getString("nombre");
                int id_direccion = rs.getInt("direccion_id");
                String telefono = rs.getString("telefono");
                int id_administrador = rs.getInt("administrador_id");

                s = new Sucursal(id_sucursal, nombre, this.direccion.selectDireccionById(id_direccion), telefono, this.administrador.selectAdministradorById(id_administrador));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return s;
    }

    // Método para insertar una sucursal a la BD
    public void insertSucursal(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO sucursales(nombre, direccion_id, telefono, adminsitrador_id) VALUES(?, ?, ?, ?)");
            stmt.setString(1, this.nombre);
            stmt.setInt(2, this.direccion.getDireccionId());
            stmt.setString(3, this.telefono);
            stmt.setInt(4, this.administrador.getAdministradorId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar un sucursal en la BD
    public void updateSucursal(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE sucursales SET nombre = ?, direccion_id = ?, telefono = ?, administrador_id = ? WHERE sucursal_id = ?");
            stmt.setString(1, this.nombre);
            stmt.setInt(2, this.direccion.getDireccionId());
            stmt.setString(3, this.telefono);
            stmt.setInt(4, this.administrador.getAdministradorId());
            stmt.setInt(5, this.sucursalId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un sucursal en la BD
    public void deleteSucursal(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM sucursales WHERE sucursal_id = ?");
            stmt.setInt(1, this.sucursalId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Setters y getters
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

    // Método toString
    @Override
    public String toString() {
        return "Sucursal [sucursalId=" + sucursalId + ", nombre=" + nombre + ", direccion=" + direccion + ", telefono="
                + telefono + ", administrador=" + administrador + "]";
    }
}
