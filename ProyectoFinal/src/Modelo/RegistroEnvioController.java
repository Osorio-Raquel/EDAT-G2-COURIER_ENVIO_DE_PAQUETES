import java.util.HashMap;

public class RegistroEnvioController {
    // Usamos un HashMap para almacenar los envíos
    private static HashMap<String, Envio> envios = new HashMap<>();

    // Método para obtener todos los envíos
    public static HashMap<String, Envio> getEnvios() {
        return envios;
    }

    // Método para registrar un envío
    public void registrarEnvio(String numeroSeguimiento, String origen, double peso, String destino) {
        // Creamos un nuevo objeto Paquete con los datos correspondientes
        Paquete paquete = new Paquete(origen, peso, destino);
        
        // Ahora creamos el objeto Envio, pasando todos los parámetros necesarios
        Envio envio = new Envio(numeroSeguimiento, origen, peso, destino, paquete);
        
        // Lo añadimos al HashMap usando el número de seguimiento como clave
        envios.put(numeroSeguimiento, envio);
        
        // Mostrar un mensaje de confirmación (opcional)
        System.out.println("Envío registrado correctamente: " + numeroSeguimiento);
    }
}
