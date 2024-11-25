import java.util.Date;

public class Envio {
    private int envioId;
    private String origen;
    private String destino;
    private Paquete paquete;
    private double costo;
    private Date fechaEnvio;
    private String estado; // Estado del envío

    public Envio(int envioId, String origen, String destino, Paquete paquete, double costo, Date fechaEnvio) {
        this.envioId = envioId;
        this.origen = origen;
        this.destino = destino;
        this.paquete = paquete;
        this.costo = costo;
        this.fechaEnvio = fechaEnvio;
        this.estado = "En Proceso"; // Estado inicial
    }

    public int getEnvioId() {
        return envioId;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public Paquete getPaquete() {
        return paquete;
    }

    public double getCosto() {
        return costo;
    }

    public Date getFechaEnvio() {
        return fechaEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return String.format("Envio ID: %d, Ruta: %s -> %s, Paquete: [%s], Costo: $%.2f, Estado: %s",
                envioId, origen, destino, paquete.getDescripcion(), costo, estado);
    }
}
