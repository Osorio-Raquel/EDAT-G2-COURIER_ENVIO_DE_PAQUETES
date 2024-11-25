import java.util.*;

public class Ruta {
    private Map<String, List<Arista>> adyacencias;

    public Ruta() {
        adyacencias = new HashMap<>();
    }

    /**
     * Agrega una ciudad al grafo.
     *
     * @param ciudad Nombre de la ciudad
     */
    public void agregarCiudad(String ciudad) {
        adyacencias.putIfAbsent(ciudad, new ArrayList<>());
    }

    /**
     * Agrega una ruta entre dos ciudades con su distancia.
     *
     * @param origen    Ciudad de origen
     * @param destino   Ciudad de destino
     * @param distancia Distancia entre las ciudades
     */
    public void agregarRuta(String origen, String destino, double distancia) {
        agregarCiudad(origen);
        agregarCiudad(destino);
        adyacencias.get(origen).add(new Arista(destino, distancia));
        adyacencias.get(destino).add(new Arista(origen, distancia)); // Grafo no dirigido
    }

    /**
     * Devuelve una lista de todas las ciudades conectadas al grafo.
     *
     * @return Lista de nombres de las ciudades
     */
    public List<String> obtenerCiudades() {
        return new ArrayList<>(adyacencias.keySet());
    }

    /**
     * Verifica si existe una conexión directa entre dos ciudades.
     *
     * @param origen  Ciudad de origen
     * @param destino Ciudad de destino
     * @return true si existe conexión, false en caso contrario
     */
    public boolean existeConexion(String origen, String destino) {
        if (!adyacencias.containsKey(origen)) return false;
        for (Arista arista : adyacencias.get(origen)) {
            if (arista.getDestino().equals(destino)) {
                return true;
            }
        }
        return false;
    }

    public String getOrigenDestino(String origen, String destino) {
        if (adyacencias.containsKey(origen) && existeConexion(origen, destino)) {
            return origen + " -> " + destino;
        }
        throw new IllegalArgumentException("La conexión entre " + origen + " y " + destino + " no existe.");
    }

    /**
     * Calcula la ruta más corta entre dos ciudades utilizando el algoritmo de Dijkstra.
     *
     * @param origen  Ciudad de origen
     * @param destino Ciudad de destino
     * @return Lista con los nombres de las ciudades en la ruta más corta
     */
    public List<String> rutaMasCorta(String origen, String destino) {
        Map<String, Double> distancias = new HashMap<>();
        Map<String, String> predecesores = new HashMap<>();
        PriorityQueue<Nodo> colaPrioridad = new PriorityQueue<>(Comparator.comparingDouble(Nodo::getDistancia));

        // Inicializar las distancias y la cola de prioridad
        for (String ciudad : adyacencias.keySet()) {
            distancias.put(ciudad, Double.MAX_VALUE);
        }
        distancias.put(origen, 0.0);
        colaPrioridad.add(new Nodo(origen, 0.0));

        while (!colaPrioridad.isEmpty()) {
            Nodo nodoActual = colaPrioridad.poll();
            String ciudadActual = nodoActual.getCiudad();

            for (Arista arista : adyacencias.get(ciudadActual)) {
                double nuevaDistancia = distancias.get(ciudadActual) + arista.getDistancia();
                if (nuevaDistancia < distancias.get(arista.getDestino())) {
                    distancias.put(arista.getDestino(), nuevaDistancia);
                    predecesores.put(arista.getDestino(), ciudadActual);
                    colaPrioridad.add(new Nodo(arista.getDestino(), nuevaDistancia));
                }
            }
        }

        // Reconstruir la ruta más corta
        List<String> ruta = new ArrayList<>();
        String paso = destino;
        while (paso != null) {
            ruta.add(0, paso);
            paso = predecesores.get(paso);
        }

        if (ruta.size() == 1 && !origen.equals(destino)) {
            throw new IllegalArgumentException("No existe una ruta entre las ciudades especificadas.");
        }

        return ruta;
    }

    /**
     * Imprime todas las rutas del grafo.
     */
    public void imprimirRutas() {
        for (String ciudad : adyacencias.keySet()) {
            System.out.println("Ciudad: " + ciudad);
            for (Arista arista : adyacencias.get(ciudad)) {
                System.out.println("  -> " + arista);
            }
        }
    }
}
