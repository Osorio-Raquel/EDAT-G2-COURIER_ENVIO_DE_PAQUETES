import java.util.*;

class Grafo {
    private Map<String, List<Arista>> adyacencias;

    public Grafo() {
        adyacencias = new HashMap<>();
    }

    public void agregarCiudad(String ciudad) {
        adyacencias.putIfAbsent(ciudad, new ArrayList<>());
    }

    public void agregarRuta(String origen, String destino, double distancia) {
        agregarCiudad(origen);
        agregarCiudad(destino);
        adyacencias.get(origen).add(new Arista(destino, distancia));
        adyacencias.get(destino).add(new Arista(origen, distancia)); // Grafo no dirigido
    }

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
}
