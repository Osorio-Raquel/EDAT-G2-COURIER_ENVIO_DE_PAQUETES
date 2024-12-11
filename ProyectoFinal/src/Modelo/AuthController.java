import java.util.HashMap;

public class AuthController {
    private HashMap<String, String> adminCredentials = new HashMap<>();
    private HashMap<String, String> employeeCredentials = new HashMap<>();
    private HashMap<String, Cliente> clientCredentials = new HashMap<>();

    public AuthController() {
        // Simulated admin credentials
        adminCredentials.put("admin@example.com", "admin123");

        // Simulated employee credentials
        employeeCredentials.put("employee@example.com", "employee123");

        // Simulated client credentials
        Direccion direccion1 = new Direccion(1, "Calle 1", "123", "", "44100",
                new Ciudad(1, "Guadalajara", new Estado(1, "Jalisco", new Pais(1, "México"))));
        Cliente cliente1 = new Cliente(1, "Juan Pérez", "client@example.com", "555-1234", direccion1, new java.util.Date());

        clientCredentials.put(cliente1.getEmail(), cliente1); // Add the client object
    }

    /**
     * Authenticate a user by email and password.
     *
     * @param email    User email
     * @param password User password
     * @return The role of the authenticated user or null if authentication fails.
     */
    public String authenticate(String email, String password) {
        if (adminCredentials.containsKey(email) && adminCredentials.get(email).equals(password)) {
            return "Administrador";
        } else if (employeeCredentials.containsKey(email) && employeeCredentials.get(email).equals(password)) {
            return "Empleado";
        } else if (clientCredentials.containsKey(email) && password.equals("client123")) { // Simulated client password check
            return "Cliente";
        }
        return null; // Invalid credentials
    }

    /**
     * Retrieve a client object by email.
     *
     * @param email Client email
     * @return The Cliente object or null if not found.
     */
    public Cliente getClientByEmail(String email) {
        return clientCredentials.get(email);
    }
}
