package Modelo;

import java.sql.Timestamp;

public class Envio {
    private int envioId;
    private int clienteId;
    private int empleadoId;
    private int vehiculoId;
    private int rutaId;
    private int estadoId;
    private Timestamp fechaEnvio;
    private Timestamp fechaEntrega;
    private double costo;

    // Constructor
    public Envio(int clienteId, int empleadoId, int vehiculoId, int rutaId, 
                 int estadoId, String fechaEnvio, String fechaEntrega, double costo) {
        this.clienteId = clienteId;
        this.empleadoId = empleadoId;
        this.vehiculoId = vehiculoId;
        this.rutaId = rutaId;
        this.estadoId = estadoId;
        this.fechaEnvio = Timestamp.valueOf(fechaEnvio); // Convertir String a Timestamp
        if (fechaEntrega != null) {
            this.fechaEntrega = Timestamp.valueOf(fechaEntrega); // Convertir String a Timestamp
        } else {
            this.fechaEntrega = null; // Si no se proporciona, dejarlo como null
        }
        this.costo = costo;
    }

    // Getters y setters
    public int getEnvioId() {
        return envioId;
    }

    public void setEnvioId(int envioId) {
        this.envioId = envioId;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public int getRutaId() {
        return rutaId;
    }

    public void setRutaId(int rutaId) {
        this.rutaId = rutaId;
    }

    public int getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(int estadoId) {
        this.estadoId = estadoId;
    }

    public Timestamp getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(Timestamp fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public Timestamp getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Timestamp fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    @Override
    public String toString() {
        return "Envio{" +
                "envioId=" + envioId +
                ", clienteId=" + clienteId +
                ", empleadoId=" + empleadoId +
                ", vehiculoId=" + vehiculoId +
                ", rutaId=" + rutaId +
                ", estadoId=" + estadoId +
                ", fechaEnvio=" + fechaEnvio +
                ", fechaEntrega=" + fechaEntrega +
                ", costo=" + costo +
                '}';
    }
}
