import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstadoVehiculo {
    private int estadoId;
    private String nombreEstado;

    // Constructor con parámetros
    public EstadoVehiculo(int estadoId, String nombreEstado) {
        this.estadoId = estadoId;
        this.nombreEstado = nombreEstado;
    }

    // Constructor sin parámetros
    public EstadoVehiculo(){
        this.estadoId = 0;
        this.nombreEstado = null;
    }

    // Método para seleccionar todos los estados de los vehiculos de la BD
    public ArrayList<EstadoVehiculo> selectEstadosVehiculos(){
        ArrayList<EstadoVehiculo> estadosVehiculos = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM estados_vehiculos");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_estado = rs.getInt("estado_id");
                String nombre_estado = rs.getString("nombre_estado");

                estadosVehiculos.add(new EstadoVehiculo(id_estado, nombre_estado));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return estadosVehiculos;
    }

    // Método para seleccionar un estado del vehiculo específico de la BD
    public EstadoVehiculo selectEstadoVehiculoById(int estadovehiculo_id){
        EstadoVehiculo ev = new EstadoVehiculo();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM estados_vehiculos WHERE estado_id = ?");
            stmt.setInt(1, estadovehiculo_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_estado = rs.getInt("estado_id");
                String nombre_estado = rs.getString("nombre_estado");

                ev = new EstadoVehiculo(id_estado, nombre_estado);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return ev;
    }

    // Método para insertar un estado del vehiculo a la BD
    public void insertEstadoVehiculo(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO estados_vehiculos(nombre_estado) VALUES(?)");
            stmt.setString(1, this.nombreEstado);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar un estado del vehiculo en la BD
    public void updateEstadoVehiculo(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE estados_vehiculos SET nombre_estado = ? WHERE estado_id = ?");
            stmt.setString(1, this.nombreEstado);
            stmt.setInt(2, this.estadoId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un estado del vehiculo en la BD
    public void deleteEstadoVehiculo(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM estados_vehiculos WHERE estado_id = ?");
            stmt.setInt(1, this.estadoId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
    
    // Setters y getters
    public int getEstadoId() {
        return estadoId;
    }
    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }
    public String getNombreEstado() {
        return nombreEstado;
    }
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    // Método toString
    @Override
    public String toString() {
        return "EstadoVehiculo [estadoId=" + estadoId + ", nombreEstado=" + nombreEstado + "]";
    }
}
