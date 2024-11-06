public class ArbolPaquetes {
    private NodoPaquete raiz;

    public void agregarPaquete(Paquete paquete) {
        raiz = agregarRecursivo(raiz, paquete);
    }

    private NodoPaquete agregarRecursivo(NodoPaquete nodo, Paquete paquete) {
        if (nodo == null) {
            return new NodoPaquete(paquete);
        }
        if (paquete.getPeso() < nodo.paquete.getPeso()) {
            nodo.izquierdo = agregarRecursivo(nodo.izquierdo, paquete);
        } else {
            nodo.derecho = agregarRecursivo(nodo.derecho, paquete);
        }
        return nodo;
    }
}
