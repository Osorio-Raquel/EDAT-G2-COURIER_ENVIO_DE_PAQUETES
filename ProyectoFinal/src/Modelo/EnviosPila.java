package Modelo;

import java.util.Stack;

public class EnviosPila {

    // Definir una pila para manejar los envíos
    private Stack<Envio> pilaEnvios;

    public EnviosPila() {
        pilaEnvios = new Stack<>(); // Usamos Stack para la implementación de la pila
    }

    // Método para agregar un envío a la pila
    public void agregarEnvio(Envio envio) {
        pilaEnvios.push(envio);
        System.out.println("Envío agregado a la pila: " + envio);
    }

    // Método para procesar el último envío (LIFO)
    public Envio procesarEnvio() {
        if (!pilaEnvios.isEmpty()) {
            Envio envio = pilaEnvios.pop(); // Extrae el último envío de la pila (LIFO)
            System.out.println("Procesando envío: " + envio);
            return envio;
        } else {
            System.out.println("No hay envíos pendientes para procesar.");
            return null;
        }
    }

    // Verificar si la pila está vacía
    public boolean estaVacia() {
        return pilaEnvios.isEmpty();
    }

    // Ver el último envío en la pila sin procesarlo
    public Envio verUltimoEnvio() {
        return pilaEnvios.peek();
    }

}
