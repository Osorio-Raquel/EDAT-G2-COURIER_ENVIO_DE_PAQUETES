package Modelo;
public class AlertasNotificacionesController {
    private AlertasNotificacionesView view;

    public AlertasNotificacionesController(AlertasNotificacionesView view) {
        this.view = view;
    }

    public void mostrarAlertas() {
        // Lógica para obtener alertas (simulación)
        String alertas = "Alerta 1: Retraso en el envío 12345\n" +
                         "Alerta 2: Paquete 67890 entregado exitosamente\n";
        
        view.mostrarAlertas(alertas);
    }
}
