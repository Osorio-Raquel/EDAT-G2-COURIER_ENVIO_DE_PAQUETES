import javax.swing.*;

public class AlertasNotificacionesView extends JFrame {
    private JTextArea alertasArea;
    private JButton refrescarButton;
    private AlertasNotificacionesController controller;

    public AlertasNotificacionesView(AlertasNotificacionesController controller) {
        this.controller = controller;
        
        setTitle("Alertas y Notificaciones");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        refrescarButton = new JButton("Refrescar Alertas");
        panel.add(refrescarButton);

        alertasArea = new JTextArea();
        alertasArea.setEditable(false);
        panel.add(new JScrollPane(alertasArea));

        refrescarButton.addActionListener(e -> controller.mostrarAlertas());

        add(panel);
    }

    public void mostrarAlertas(String alertas) {
        alertasArea.setText(alertas);
    }
}
