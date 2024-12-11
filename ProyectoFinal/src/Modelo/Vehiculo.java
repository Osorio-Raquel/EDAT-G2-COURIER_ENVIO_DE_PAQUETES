import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Vehiculo {
    private int vehiculoId;
    private String tipo;
    private String modelo;
    private String matricula;
    private double capacidadKg;
    private Date fechaAdquisicion;
    private EstadoVehiculo estadoVehiculo;

    // Constructor con parámetros
    public Vehiculo(int vehiculoId, String tipo, String modelo, String matricula, double capacidadKg,
            Date fechaAdquisicion, EstadoVehiculo estadoVehiculo) {
        this.vehiculoId = vehiculoId;
        this.tipo = tipo;
        this.modelo = modelo;
        this.matricula = matricula;
        this.capacidadKg = capacidadKg;
        this.fechaAdquisicion = fechaAdquisicion;
        this.estadoVehiculo = estadoVehiculo;
    }

    // Constructor sin parámetros
    public Vehiculo(){
        this.vehiculoId = 0;
        this.tipo = null;
        this.modelo = null;
        this.matricula = null;
        this.capacidadKg = 0;
        this.fechaAdquisicion = null;
        this.estadoVehiculo = null;
    }

    // Método para seleccionar todos los vehiculos de la BD
    public ArrayList<Vehiculo> selectVehiculos(){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM vehiculos");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_vehiculo = rs.getInt("vehiculo_id");
                String tipo = rs.getString("tipo");
                String modelo = rs.getString("modelo");
                String matricula = rs.getString("matricula");
                double capacidad_kg = rs.getDouble("capacidad_kg");
                Date fecha_adquisicion = new Date(rs.getTimestamp("fecha_adquisicion").getTime());
                int id_estado = rs.getInt("estado_id");

                vehiculos.add(new Vehiculo(id_vehiculo, tipo, modelo, matricula, capacidad_kg, fecha_adquisicion, this.estadoVehiculo.selectEstadoVehiculoById(id_estado)));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return vehiculos;
    }

    // Método para seleccionar un vehiculo específico de la BD
    public Vehiculo selectVehiculoById(int vehiculo_id){
        Vehiculo v = new Vehiculo();
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM vehiculos WHERE vehiculo_id = ?");
            stmt.setInt(1, vehiculo_id);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                int id_vehiculo = rs.getInt("vehiculo_id");
                String tipo = rs.getString("tipo");
                String modelo = rs.getString("modelo");
                String matricula = rs.getString("matricula");
                double capacidad_kg = rs.getDouble("capacidad_kg");
                Date fecha_adquisicion = new Date(rs.getTimestamp("fecha_adquisicion").getTime());
                int id_estado = rs.getInt("estado_id");

                v = new Vehiculo(id_vehiculo, tipo, modelo, matricula, capacidad_kg, fecha_adquisicion, this.estadoVehiculo.selectEstadoVehiculoById(id_estado));
            }
            rs.close();
            stmt.close();
            con.close();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return v;
    }

    // Método para insertar un vehiculo a la BD
    public void insertVehiculo(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("INSERT INTO vehiculos(tipo, modelo, matricula, capacidad_kg, fecha_adquisicion, estado_id) VALUES(?, ?, ?, ?, ?, ?)");
            stmt.setString(1, this.tipo);
            stmt.setString(2, this.modelo);
            stmt.setString(3, this.matricula);
            stmt.setDouble(4, this.capacidadKg);
            stmt.setTimestamp(5, new java.sql.Timestamp(this.fechaAdquisicion.getTime()));
            stmt.setInt(6, this.estadoVehiculo.getEstadoId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para actualizar un vehiculo en la BD
    public void updateVehiculo(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("UPDATE vehiculos SET tipo = ?, modelo = ?, matricula = ?, capacidad_kg = ?, fecha_adquisicion = ?, estado_id = ? WHERE vehiculo_id = ?");
            stmt.setString(1, this.tipo);
            stmt.setString(2, this.modelo);
            stmt.setString(3, this.matricula);
            stmt.setDouble(4, this.capacidadKg);
            stmt.setTimestamp(5, new java.sql.Timestamp(this.fechaAdquisicion.getTime()));
            stmt.setInt(6, this.estadoVehiculo.getEstadoId());
            stmt.setInt(7, this.vehiculoId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }

    // Método para eliminar un vehiculo en la BD
    public void deleteVehiculo(){
        ConnectionDB conn = new ConnectionDB();
        Connection con = conn.connect();
        try{
            PreparedStatement stmt = con.prepareStatement("DELETE FROM vehiculos WHERE vehiculo_id = ?");
            stmt.setInt(1, this.vehiculoId);
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    // Setters y getters
    public int getVehiculoId() {
        return vehiculoId;
    }
    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getModelo() {
        return modelo;
    }
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    public String getMatricula() {
        return matricula;
    }
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    public double getCapacidadKg() {
        return capacidadKg;
    }
    public void setCapacidadKg(double capacidadKg) {
        this.capacidadKg = capacidadKg;
    }
    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }
    public EstadoVehiculo getEstadoVehiculo() {
        return estadoVehiculo;
    }
    public void setEstadoVehiculo(EstadoVehiculo estadoVehiculo) {
        this.estadoVehiculo = estadoVehiculo;
    }

    // Método toString
    @Override
    public String toString() {
        return "Vehiculo [vehiculoId=" + vehiculoId + ", tipo=" + tipo + ", modelo=" + modelo + ", matricula="
                + matricula + ", capacidadKg=" + capacidadKg + ", fechaAdquisicion=" + fechaAdquisicion
                + ", estadoVehiculo=" + estadoVehiculo + "]";
    }
}
