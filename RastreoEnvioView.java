import javax.swing.*;

public class RastreoEnvioView extends JFrame {
    private JTextField seguimientoField;
    private JButton rastrearButton;
    private JLabel estadoLabel;
    private RastreoEnvioController controller;

    public RastreoEnvioView(RastreoEnvioController controller) {
        this.controller = controller;

        setTitle("Rastreo de Envío");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(new JLabel("Número de Seguimiento:"));
        seguimientoField = new JTextField();
        panel.add(seguimientoField);

        rastrearButton = new JButton("Rastrear");
        panel.add(rastrearButton);

        estadoLabel = new JLabel("Estado: ");
        panel.add(estadoLabel);

        rastrearButton.addActionListener(e -> controller.rastrearEnvio(seguimientoField.getText()));

        add(panel);
    }

    // Método para mostrar el estado del envío
    public void mostrarEstadoEnvio(String estado) {
        estadoLabel.setText("Estado: " + estado);
    }
}
