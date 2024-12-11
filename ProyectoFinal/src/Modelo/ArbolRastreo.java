package Modelo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ArbolRastreo {
    private NodoRastreo raiz;

    public ArbolRastreo() {
        this.raiz = null;
    }

    // Clase interna para representar un nodo en el árbol de rastreo
    private class NodoRastreo {
        private String ubicacion;
        private LocalDateTime fechaHora;
        private List<NodoRastreo> hijos;

        public NodoRastreo(String ubicacion, LocalDateTime fechaHora) {
            this.ubicacion = ubicacion;
            this.fechaHora = fechaHora;
            this.hijos = new ArrayList<>();
        }
    }

    // Método para agregar una nueva ubicación al árbol de rastreo
    public void agregarUbicacion(String ubicacion) {
        LocalDateTime fechaHora = LocalDateTime.now();
        NodoRastreo nuevoNodo = new NodoRastreo(ubicacion, fechaHora);

        if (raiz == null) {
            raiz = nuevoNodo;
        } else {
            raiz.hijos.add(nuevoNodo);
        }
    }

    // Método para imprimir el historial completo de ubicaciones
    public void mostrarHistorial() {
        mostrarHistorial(raiz, 0);
    }

    private void mostrarHistorial(NodoRastreo nodo, int nivel) {
        if (nodo != null) {
            // Imprime la ubicación y la fecha/hora del nodo
            System.out.println(" ".repeat(nivel * 2) + "Ubicación: " + nodo.ubicacion + " | Fecha y Hora: " + nodo.fechaHora);
            for (NodoRastreo hijo : nodo.hijos) {
                mostrarHistorial(hijo, nivel + 1);
            }
        }
    }
}
