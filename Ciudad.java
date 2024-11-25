import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Ciudad {
    private int ciudadId;
    private String nombreCiudad;
    private Estado estado;
    
    // Constructor con parámetros
    public Ciudad(int ciudadId, String nombreCiudad, Estado estado) {
        this.ciudadId = ciudadId;
        this.nombreCiudad = nombreCiudad;
        this.estado = estado;
    }

    // Constructor sin parámetros
    public Ciudad(){
        this.ciudadId = 0;
        this.nombreCiudad = null;
        this.estado = null;
    }

    // Método para seleccionar todas las ciudades de la BD
    public ArrayList<Ciudad> selectCiudades(){
        ArrayList<Ciudad> ciudades = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ciudades");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_ciudad = rs.getInt("ciudad_id");
                String nombre_ciudad = rs.getString("nombre_ciudad");
                int id_estado = rs.getInt("estado_id");

                ciudades.add(new Ciudad(id_ciudad, nombre_ciudad, this.estado.selectEstadoById(id_estado)));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return ciudades;
    }

    // Método para seleccionar una ciudad específica de la BD
    public Ciudad selectCiudadById(int ciudad_id){
        Ciudad c = new Ciudad();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM ciudades WHERE ciudad_id = ?");
            stmt.setInt(1, ciudad_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_ciudad = rs.getInt("ciudad_id");
                String nombre_ciudad = rs.getString("nombre_ciudad");
                int id_estado = rs.getInt("estado_id");

                c = new Ciudad(id_ciudad, nombre_ciudad, this.estado.selectEstadoById(id_estado));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return c;
    }

    // Método para insertar una ciudad a la BD
    public void insertCiudad(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO ciudades(nombre_ciudad, estado_id) VALUES(?, ?)");
            stmt.setString(1, this.nombreCiudad);
            stmt.setInt(2, this.estado.getEstadoId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar una ciudad en la BD
    public void updateCiudad(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE ciudades SET nombre_ciudad = ?, estado_id = ? WHERE ciudad_id = ?");
            stmt.setString(1, this.nombreCiudad);
            stmt.setInt(2, this.estado.getEstadoId());
            stmt.setInt(3, this.ciudadId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar una ciudad en la BD
    public void deleteCiudad(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM ciudades WHERE ciudad_id = ?");
            stmt.setInt(1, this.ciudadId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Setters y getters
    public int getCiudadId() {
        return ciudadId;
    }

    public void setCiudadId(int ciudadId) {
        this.ciudadId = ciudadId;
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    // Método toString
    @Override
    public String toString() {
        return "Ciudad [ciudadId=" + ciudadId + ", nombreCiudad=" + nombreCiudad + ", estado=" + estado + "]";
    }
}
