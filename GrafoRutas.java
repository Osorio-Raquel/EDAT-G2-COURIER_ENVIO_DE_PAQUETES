import java.util.HashMap;
import java.util.Map;

public class GrafoRutas {
    private Map<String, Map<String, Integer>> rutas;

    public GrafoRutas() {
        rutas = new HashMap<>();
    }

    public void agregarRuta(String origen, String destino, int peso) {
        rutas.putIfAbsent(origen, new HashMap<>());
        rutas.get(origen).put(destino, peso);
    }

    public Map<String, Integer> obtenerRutasDesde(String origen) {
        return rutas.getOrDefault(origen, null);
    }
}
