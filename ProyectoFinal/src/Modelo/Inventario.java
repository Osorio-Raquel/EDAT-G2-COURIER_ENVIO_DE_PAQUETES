import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Inventario {
    private int inventarioId;
    private Sucursal sucursal;
    private Paquete paquete;
    private int cantidad;
    private Date fechaEntrada;
    

    // Constructor con parámetros
    public Inventario(int inventarioId, Sucursal sucursal, Paquete paquete, int cantidad, Date fechaEntrada) {
        this.inventarioId = inventarioId;
        this.sucursal = sucursal;
        this.paquete = paquete;
        this.cantidad = cantidad;
        this.fechaEntrada = fechaEntrada;
    }

    // Constructor sin parámetros
    public Inventario(){
        this.inventarioId = 0;
        this.sucursal = null;
        this.paquete = null;
        this.cantidad = 0;
        this.fechaEntrada = null;
    }

    // Método para seleccionar el inventario de la BD
    public ArrayList<Inventario> selectInventario(){
        ArrayList<Inventario> inventario = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM inventario");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_inventario = rs.getInt("inventario_id");
                int id_sucursal = rs.getInt("sucursal_id");
                int id_paquete = rs.getInt("paquete_id");
                int cantidad = rs.getInt("cantidad");
                Date fecha_entrada = new Date(rs.getTimestamp("fecha_entrada").getTime());

                inventario.add(new Inventario(id_inventario, this.sucursal.selectSucursalById(id_sucursal), this.paquete, cantidad, fecha_entrada));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return inventario;
    }

    // Método para seleccionar un inventario específico de la BD
    public Inventario selectInventarioById(int inventario_id){
        Inventario i = new Inventario();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM inventario WHERE inventario_id = ?");
            stmt.setInt(1, inventario_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_inventario = rs.getInt("inventario_id");
                int id_sucursal = rs.getInt("sucursal_id");
                int id_paquete = rs.getInt("paquete_id");
                int cantidad = rs.getInt("cantidad");
                Date fecha_entrada = new Date(rs.getTimestamp("fecha_entrada").getTime());

                i = new Inventario(id_inventario, this.sucursal.selectSucursalById(id_sucursal), this.paquete, cantidad, fecha_entrada);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return i;
    }

    // Método para insertar un inventario a la BD
    public void insertInventario(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO inventario(sucursal_id, paquete_id, cantidad, fecha_entrada) VALUES(?, ?, ?, ?)");
            stmt.setInt(1, this.sucursal.getSucursalId());
            stmt.setInt(2, 1);
            stmt.setInt(3, cantidad);
            stmt.setTimestamp(4, new java.sql.Timestamp(this.fechaEntrada.getTime()));
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar un inventario en la BD
    public void updateInventario(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE inventario SET sucursal_id = ?, paquete_id = ?, cantidad = ?, fecha_entrada = ? WHERE inventario_id = ?");
            stmt.setInt(1, this.sucursal.getSucursalId());
            stmt.setInt(2, 1);
            stmt.setInt(3, cantidad);
            stmt.setTimestamp(4, new java.sql.Timestamp(this.fechaEntrada.getTime()));
            stmt.setInt(2, this.inventarioId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un inventario en la BD
    public void deleteInventario(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM inventario WHERE inventario_id = ?");
            stmt.setInt(1, this.inventarioId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public int getInventarioId() {
        return inventarioId;
    }

    public void setInventarioId(int inventarioId) {
        this.inventarioId = inventarioId;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    @Override
    public String toString() {
        return "Inventario [inventarioId=" + inventarioId + ", sucursal=" + sucursal + ", paquete=" + paquete
                + ", cantidad=" + cantidad + ", fechaEntrada=" + fechaEntrada + "]";
    }
}
