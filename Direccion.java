import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class Direccion {
    private int direccionId;
    private Ciudad ciudad;
    private String calle;
    private String numeroExt;
    private String numeroInt;
    private String codigoPostal;
    Map<String, Direccion> direccionesPorCiudad;
    
    public Direccion(int direccionId, Ciudad ciudad, String calle, String numeroExt, String numeroInt,
        String codigoPostal) {
        this.direccionId = direccionId;
        this.ciudad = ciudad;
        this.calle = calle;
        this.numeroExt = numeroExt;
        this.numeroInt = numeroInt;
        this.codigoPostal = codigoPostal;
    }

    public Direccion(){
        this.direccionId = 0;
        this.ciudad = null;
        this.calle = null;
        this.numeroExt = null;
        this.numeroInt = null;
        this.codigoPostal = null;
    }

    // Método para seleccionar todas las direcciones de la BD
    public ArrayList<Direccion> selectDirecciones(){
        ArrayList<Direccion> direcciones = new ArrayList<>();
        Ciudad c = new Ciudad();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM direcciones");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_direccion = rs.getInt("direccion_id");
                int id_ciudad= rs.getInt("ciudad_id");
                String calle = rs.getString("calle");
                String numero_ext = rs.getString("numero_ext");
                String numero_int = rs.getString("numero_ext");
                String codigo_posta = rs.getString("codigo_postal");

                direcciones.add(new Direccion(id_direccion, this.ciudad.selectCiudadById(id_ciudad), calle, numero_ext, numero_int, codigo_posta));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return direcciones;
    }

    // Método para seleccionar una dirección específica de la BD
    public Direccion selectDireccionById(int direccion_id){
        Direccion d = new Direccion();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM direcciones WHERE direccion_id = ?");
            stmt.setInt(1, direccion_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_direccion = rs.getInt("direccion_id");
                int id_ciudad= rs.getInt("ciudad_id");
                String calle = rs.getString("calle");
                String numero_ext = rs.getString("numero_ext");
                String numero_int = rs.getString("numero_ext");
                String codigo_posta = rs.getString("codigo_postal");

                d = new Direccion(id_direccion, this.ciudad.selectCiudadById(id_ciudad), calle, numero_ext, numero_int, codigo_posta);
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return d;
    }

    // Método para insertar una dirección a la BD
    public void insertDireccion(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO direcciones(ciudad_id, calle, numero_ext, numero_int, codigo_postal) VALUES(?, ?, ?, ?, ?)");
            stmt.setInt(1, this.ciudad.getCiudadId());
            stmt.setString(2, this.calle);
            stmt.setString(3, this.numeroExt);
            stmt.setString(4, this.numeroInt);
            stmt.setString(5, this.codigoPostal);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar una dirección en la BD
    public void updateDireccion(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE direcciones SET ciudad_id = ?, calle = ?, numero_ext = ?, numero_int = ?, codigo_postal = ? WHERE direccion_id = ?");
            stmt.setInt(1, this.ciudad.getCiudadId());
            stmt.setString(2, this.calle);
            stmt.setString(3, this.numeroExt);
            stmt.setString(4, this.numeroInt);
            stmt.setString(5, this.codigoPostal);
            stmt.setInt(6, this.direccionId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar una dirección en la BD
    public void deleteDireccion(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM direcciones WHERE direccion_id = ?");
            stmt.setInt(1, this.direccionId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Setters y getters
    public int getDireccionId() {
        return direccionId;
    }

    public void setDireccionId(int direccionId) {
        this.direccionId = direccionId;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumeroExt() {
        return numeroExt;
    }

    public void setNumeroExt(String numeroExt) {
        this.numeroExt = numeroExt;
    }

    public String getNumeroInt() {
        return numeroInt;
    }

    public void setNumeroInt(String numeroInt) {
        this.numeroInt = numeroInt;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

     // Método toString
    @Override
    public String toString() {
        return "Direccion [direccionId=" + direccionId + ", ciudad=" + ciudad + ", calle=" + calle + ", numeroExt="
                + numeroExt + ", numeroInt=" + numeroInt + ", codigoPostal=" + codigoPostal + "]";
    }
}
