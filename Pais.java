import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Pais {
    private int paisId;
    private String nombrePais;

    // Constructor con parámetros
    public Pais(int paisId, String nombrePais){
        this.paisId = paisId;
        this.nombrePais = nombrePais;
    }

    // Constructor sin parámetros
    public Pais(){
        this.paisId = 0;
        this.nombrePais = null;
    }

    // Método para seleccionar todos los países de la BD
    public ArrayList<Pais> selectPaises(){
        ArrayList<Pais> paises = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM paises");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_pais = rs.getInt("pais_id");
                String nombre_pais = rs.getString("nombre_pais");

                paises.add(new Pais(id_pais, nombre_pais));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return paises;
    }

    // Método para seleccionar un país específico de la BD
    public Pais selectPaisById(int pais_id){
        Pais p = new Pais();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM paises WHERE pais_id = ?");
            stmt.setInt(1, pais_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_pais = rs.getInt("pais_id");
                String nombre_pais = rs.getString("nombre_pais");

                p = new Pais(id_pais, nombre_pais);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return p;
    }

    // Método para insertar un país a la BD
    public void insertPais(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO paises(nombre_pais) VALUES(?)");
            stmt.setString(1, this.nombrePais);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar un país en la BD
    public void updatePais(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE paises SET nombre_pais = ? WHERE pais_id = ?");
            stmt.setString(1, this.nombrePais);
            stmt.setInt(2, this.paisId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un país en la BD
    public void deletePais(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM paises WHERE pais_id = ?");
            stmt.setInt(1, this.paisId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    // Setters y getters
    public int getPaisId() {
        return paisId;
    }

    public void setPaisId(int paisId) {
        this.paisId = paisId;
    }

    public String getNombrePais() {
        return nombrePais;
    }

    public void setNombrePais(String nombrePais) {
        this.nombrePais = nombrePais;
    }


    // Método toString
    @Override
    public String toString() {
        return "Pais [paisId=" + paisId + ", nombrePais=" + nombrePais + "]";
    }
}
