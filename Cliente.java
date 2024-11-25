import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cliente {
    private int clienteId;
    private String nombre;
    private String email;
    private String telefono;
    private Direccion direccion;
    private Date fechaRegistro;
    
    // Constructor con parámetros
    public Cliente(int clienteId, String nombre, String email, String telefono, Direccion direccion,
            Date fechaRegistro) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaRegistro = fechaRegistro;
    }

    // Constructor sin parámetros
    public Cliente(){
        this.clienteId = 0;
        this.nombre = null;
        this.email = null;
        this.telefono = null;
        this.direccion = null;
        this.fechaRegistro = null;
    }

    // Método para seleccionar todos los clientes de la BD
    public ArrayList<Cliente> selectClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_cliente = rs.getInt("cliente_id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                int direccion_id = rs.getInt("direccion_id");
                Date fecha_registro = new Date(rs.getTimestamp("fecha_registro").getTime());

                clientes.add(new Cliente(id_cliente, nombre, email, telefono, this.direccion.selectDireccionById(direccion_id), fecha_registro));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return clientes;
    }

    // Método para seleccionar un cliente específico de la BD
    public Cliente selectClienteById(int cliente_id){
        Cliente c = new Cliente();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM clientes WHERE cliente_id = ?");
            stmt.setInt(1, cliente_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_cliente = rs.getInt("cliente_id");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                int direccion_id = rs.getInt("direccion_id");
                Date fecha_registro = new Date(rs.getTimestamp("fecha_registro").getTime());

                c = new Cliente(id_cliente, nombre, email, telefono, this.direccion.selectDireccionById(direccion_id), fecha_registro);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return c;
    }

    // Método para insertar un cliente a la BD
    public void insertCliente(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO clientes(nombre, email, telefono, direccion_id, fecha_registro) VALUES(?, ?, ?, ?, ?)");
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.email);
            stmt.setString(3, this.telefono);
            stmt.setInt(4, this.direccion.getDireccionId());
            stmt.setTimestamp(5, new java.sql.Timestamp(this.fechaRegistro.getTime()));
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar un cliente en la BD
    public void updateCliente(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE clientes SET nombre = ?, email = ?, telefono = ?, direccion_id = ?, fecha_registro = ? WHERE cliente_id = ?");
            stmt.setString(1, this.nombre);
            stmt.setString(2, this.email);
            stmt.setString(3, this.telefono);
            stmt.setInt(4, this.direccion.getDireccionId());
            stmt.setTimestamp(5, new java.sql.Timestamp(this.fechaRegistro.getTime()));
            stmt.setInt(6, this.clienteId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un cliente en la BD
    public void deleteCliente(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM clientes WHERE cliente_id = ?");
            stmt.setInt(1, this.clienteId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    // Setters y getters
    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
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

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
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
        return "Cliente [clienteId=" + clienteId + ", nombre=" + nombre + ", email=" + email + ", telefono=" + telefono
                + ", direccion=" + direccion + ", fechaRegistro=" + fechaRegistro + "]";
    }
}
