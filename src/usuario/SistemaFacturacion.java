package usuario;

import java.util.Scanner;

import negocio.Catalogo;

public class SistemaFacturacion {
    private static Catalogo catalogo = new Catalogo();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarCatalogo();

        while (true) {
            mostrarMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de l�nea

            switch (opcion) {
                case 1:
                    listarProductos();
                    break;
                case 2:
                    realizarVenta();
                    break;
                case 3:
                    System.out.println("Gracias por usar el sistema de facturación. ¡Hasta luego!");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo.");
            }
        }
    }

    private static void inicializarCatalogo() {
        catalogo.agregarProducto(new Producto("001", "Leche", 2.5, 100, 20));
        catalogo.agregarProducto(new Producto("002", "Pan", 1.0, 50, 10));
        catalogo.agregarProducto(new Producto("003", "Huevos", 3.0, 30, 5));
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Sistema de Facturación ---");
        System.out.println("1. Listar productos");
        System.out.println("2. Realizar venta");
        System.out.println("3. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void listarProductos() {
        System.out.println("\nLista de productos:");
        for (Producto producto : catalogo.listarProductos()) {
            System.out.printf("%s - %s: $%.2f (Stock: %d)%n",
                    producto.getCodigo(), producto.getDescripcion(),
                    producto.getPrecioUnitario(), producto.getCantidadStock());
        }
    }

    private static void realizarVenta() {
        Venta venta = new Venta(seleccionarMedioPago());

        while (true) {
            System.out.print("\nIngrese el código del producto (o 'fin' para terminar): ");
            String codigo = scanner.nextLine();
            if (codigo.equalsIgnoreCase("fin")) break;

            Producto producto = catalogo.obtenerProducto(codigo);
            if (producto == null) {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("Ingrese la cantidad: ");
            int cantidad = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de l�nea

            try {
                venta.agregarProducto(producto, cantidad);
                System.out.println("Producto agregado a la venta.");
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        venta.finalizarVenta();
        catalogo.marcarProductosBajoStock();
    }

    private static MedioPago seleccionarMedioPago() {
        System.out.println("\nSeleccione el medio de pago:");
        System.out.println("1. Efectivo");
        System.out.println("2. Tarjeta de Débito");
        System.out.println("3. Tarjeta de Crédito");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de l�nea

        switch (opcion) {
            case 1:
                return new Efectivo();
            case 2:
                return new TarjetaDebito();
            case 3:
                System.out.print("Ingrese el número de cuotas (2, 3 o 6): ");
                int cuotas = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de l�nea
                return new TarjetaCredito(cuotas);
            default:
                System.out.println("Opciún no válida. Se usará Efectivo por defecto.");
                return new Efectivo();
        }
    }
}