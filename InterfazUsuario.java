import java.util.Scanner;

public class InterfazUsuario {
    private RegistroEnvios registroEnvios;
    private ArbolPaquetes arbolPaquetes;
    private EstadoEnvio estadoEnvio;
    private GrafoRutas grafoRutas;
    private AlertasNotificaciones alertasNotificaciones;

    public InterfazUsuario() {
        registroEnvios = new RegistroEnvios();
        arbolPaquetes = new ArbolPaquetes();
        estadoEnvio = new EstadoEnvio();
        grafoRutas = new GrafoRutas();
        alertasNotificaciones = new AlertasNotificaciones();
    }

    public void iniciar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al sistema de Courier de Envío de Paquetes.");
        // Lógica de menú interactivo para gestionar envíos, rastreo, etc.
        scanner.close();
    }
}
