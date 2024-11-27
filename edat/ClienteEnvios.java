import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClienteEnvios {
    public Cliente cliente;
    public List<Envio> envios; // Lista de envíos asociados al cliente
    public List<Factura> facturas; // Lista de facturas del cliente

    public ClienteEnvios(Cliente cliente) {
        this.cliente = cliente;
        this.envios = new ArrayList<>();
        this.facturas = new ArrayList<>();
    }

    /**
     * Muestra los envíos actuales del cliente.
     */
    public void mostrarEnvios() {
        if (envios.isEmpty()) {
            System.out.println("No tienes envíos registrados.");
        } else {
            System.out.println("Tus envíos:");
            for (Envio envio : envios) {
                System.out.println(envio);
            }
        }
    }

    /**
     * Agrega un nuevo envío al cliente.
     *
     * @param descripcionPaquete Descripción del paquete
     * @param peso               Peso del paquete en kg
     * @param dimensiones        Dimensiones del paquete
     * @param valorDeclarado     Valor declarado del paquete
     * @param origen             Ciudad de origen
     * @param destino            Ciudad de destino
     * @param ruta               Grafo de rutas
     */
    public void agregarEnvio(String descripcionPaquete, double peso, String dimensiones, double valorDeclarado, String origen, String destino, Ruta ruta) {
        // Validar la conexión entre las ciudades
        if (!ruta.existeConexion(origen, destino)) {
            throw new IllegalArgumentException("No existe una conexión entre " + origen + " y " + destino);
        }

        // Crear el paquete
        Paquete paquete = new Paquete(envios.size() + 1, descripcionPaquete, peso, dimensiones, valorDeclarado);

        // Calcular costo basado en la distancia y peso
        double distancia = ruta.rutaMasCorta(origen, destino).size() * 100; // Ejemplo de distancia fija por nodo
        double costo = distancia * 0.5 + peso * 2.0; // Ejemplo: 0.5 por km + 2.0 por kg

        //TODO: refactoriza esto, ya que se tiene que recibir objetos del tipo Empleado, ruta, estadoEnvio, vehiculo
        Envio envio = new Envio(envios.size() + 1, cliente, null, null, null, null, new Date(), null, costo, null, null);

        // Agregar el envío a la lista del cliente
        envios.add(envio);

        System.out.println("Envio registrado con éxito: " + envio);
    }

    /**
     * Genera una factura que agrupa todos los envíos del cliente desde la última factura.
     *
     * @return La factura generada
     */
    public Factura generarFactura() {
        if (envios.isEmpty()) {
            throw new IllegalStateException("No hay envíos registrados para facturar.");
        }

        Factura factura = new Factura(facturas.size() + 1, cliente, new Date(), "Tarjeta de Crédito");

        // Agregar todos los envíos a la factura
        for (Envio envio : envios) {
            // DetalleFactura detalle = new DetalleFactura(factura.getFacturaId(), envio, 1);
            DetalleFactura detalle = new DetalleFactura(factura.getFacturaId(), factura, envio.getPaquete(), 1, envio.getCosto());
            factura.agregarDetalle(detalle);
        }

        // Registrar la factura
        facturas.add(factura);

        System.out.println("Factura generada:");
        System.out.println(factura);

        return factura;
    }

    /**
     * Muestra todas las facturas del cliente.
     */
    public void mostrarFacturas() {
        if (facturas.isEmpty()) {
            System.out.println("No tienes facturas registradas.");
        } else {
            System.out.println("Tus facturas:");
            for (Factura factura : facturas) {
                System.out.println(factura);
            }
        }
    }
}
