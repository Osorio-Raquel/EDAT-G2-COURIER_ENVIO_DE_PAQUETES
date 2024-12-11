import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {

        String jdbcURL = "jdbc:mysql://junction.proxy.rlwy.net:16902/railway";
        String username = "postgres";
        String password = "imioQblEwsVUXsictkeZCjvnuWWqyqNE";

        // Consulta SQL
        String query = "SELECT * FROM paises";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Conexión exitosa a la base de datos.");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(query);

            System.out.println("ID\tPais");
            System.out.println("---------------------------------");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");

                System.out.printf("%d\t%s\t\t%.2f%n", id, nombre);
            }

            resultSet.close();
            statement.close();
            connection.close();
            System.out.println("Conexión cerrada.");
        } catch (SQLException e) {
            System.out.println("Error al conectar o ejecutar la consulta.");
            e.printStackTrace();
        }
    }
}
