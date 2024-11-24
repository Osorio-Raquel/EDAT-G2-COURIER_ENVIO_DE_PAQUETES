import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Estado {
    private int estadoId;
    private String nombreEstado;
    private Pais pais;
    
    public Estado(int estadoId, String nombreEstado, Pais pais) {
        this.estadoId = estadoId;
        this.nombreEstado = nombreEstado;
        this.pais = pais;
    }

    public Estado(){
        this.estadoId = 0;
        this.nombreEstado = null;
        this.pais = null;
    }

    // Método para seleccionar todos los estados de la BD
    public ArrayList<Estado> selectEstados(){
        ArrayList<Estado> estados = new ArrayList<>();
        Pais p = new Pais();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM estados");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_estado = rs.getInt("estado_id");
                String nombre_estado = rs.getString("nombre_estado");
                int id_pais = rs.getInt("pais_id");

                estados.add(new Estado(id_estado, nombre_estado, p.selectPaisById(id_pais)));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return estados;
    }

    // Método para seleccionar un estado específico de la BD
    public Estado selectEstadoById(int estado_id){
        Estado es = new Estado();
        Pais p = new Pais();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM paises WHERE estado_id = ?");
            stmt.setInt(1, estado_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_estado = rs.getInt("estado_id");
                String nombre_estado = rs.getString("nombre_estado");
                int id_pais = rs.getInt("pais_id");

                es = new Estado(id_estado, nombre_estado, p.selectPaisById(id_pais));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return es;
    }

    // Método para insertar un estado a la BD
    public void insertEstado(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO estados(nombre_estado, pais_id) VALUES(?, ?)");
            stmt.setString(1, this.nombreEstado);
            stmt.setInt(2, this.pais.getPaisId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar un estado en la BD
    public void updateEstado(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE estados SET nombre_estado = ? AND SET pais_id = ? WHERE estado_id = ?");
            stmt.setString(1, this.nombreEstado);
            stmt.setInt(2, this.pais.getPaisId());
            stmt.setInt(3, this.estadoId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un estado en la BD
    public void deleteEstado(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM estados WHERE estado_id = ?");
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

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    // Método toString
    @Override
    public String toString() {
        return "Estado [estadoId=" + estadoId + ", nombreEstado=" + nombreEstado + ", pais=" + pais + "]";
    }
}
