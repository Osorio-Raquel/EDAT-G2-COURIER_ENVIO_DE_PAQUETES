import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Crear el controlador para registrar envíos
        RegistroEnvioController registroController = new RegistroEnvioController();
        // Crear la vista para registrar envíos pasando el controlador
        RegistroEnvioView registroView = new RegistroEnvioView(registroController);

        // Crear el controlador para rastreo de envíos
        RastreoEnvioController rastreoController = new RastreoEnvioController();
        // Crear la vista para rastrear envíos pasando el controlador
        RastreoEnvioView rastreoView = new RastreoEnvioView(rastreoController);

        // Asociar la vista con el controlador
        rastreoController.setView(rastreoView);

        // Menú principal
        String[] opciones = {"Registrar Envío", "Rastrear Envío", "Salir"};
        while (true) {
            int opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú Principal",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            if (opcion == 0) {
                // Abrir vista para registrar envío
                registroView.setVisible(true);
            } else if (opcion == 1) {
                // Abrir vista para rastrear envío
                rastreoView.setVisible(true);
            } else {
                // Salir
                break;
            }
        }
    }
}
