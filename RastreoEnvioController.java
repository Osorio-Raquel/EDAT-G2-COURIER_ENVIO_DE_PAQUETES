import java.util.HashMap;
import javax.swing.*;

public class RastreoEnvioController {
    private RastreoEnvioView view;
    private static HashMap<String, Envio> envios = RegistroEnvioController.getEnvios();  // Llamamos al método getEnvios()

    // Constructor modificado para no requerir vista como parámetro
    public RastreoEnvioController() {
        // No hacemos nada con la vista por ahora
    }

    // Método para asignar la vista al controlador
    public void setView(RastreoEnvioView view) {
        this.view = view;
    }

    public void rastrearEnvio(String numeroSeguimiento) {
        Envio envio = envios.get(numeroSeguimiento);

        if (envio != null) {
            view.mostrarEstadoEnvio(envio.getEstadoEnvio());
        } else {
            JOptionPane.showMessageDialog(view, "Número de seguimiento no encontrado.");
        }
    }
}
