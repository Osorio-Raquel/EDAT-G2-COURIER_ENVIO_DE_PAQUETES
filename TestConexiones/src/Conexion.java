import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://junction.proxy.rlwy.net:16902/railway";
        String username = "postgres";
        String password = "imioQblEwsVUXsictkeZCjvnuWWqyqNE";

        try {
            // Conecta a la base de datos
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("¡Conexión exitosa!");

            // Cierra la conexión
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos");
            e.printStackTrace();
        }
    }
}
