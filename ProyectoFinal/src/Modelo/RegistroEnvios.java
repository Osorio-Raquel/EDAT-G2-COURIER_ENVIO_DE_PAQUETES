import java.util.HashMap;

public class RegistroEnvios {
    private HashMap<String, Envio> envios;

    public RegistroEnvios() {
        envios = new HashMap<>();
    }

    public void registrarEnvio(Envio envio) {
        envios.put(envio.getNumeroSeguimiento(), envio);
    }

    public Envio buscarEnvio(String numeroSeguimiento) {
        return envios.get(numeroSeguimiento);
    }

    public void eliminarEnvio(String numeroSeguimiento) {
        envios.remove(numeroSeguimiento);
    }
}
