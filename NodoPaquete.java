public class NodoPaquete {
    public Paquete paquete;
    public NodoPaquete izquierdo;
    public NodoPaquete derecho;

    public NodoPaquete(Paquete paquete) {
        this.paquete = paquete;
        this.izquierdo = null;
        this.derecho = null;
    }
}

