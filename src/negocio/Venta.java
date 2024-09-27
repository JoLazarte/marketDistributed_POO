package negocio;

import java.util.ArrayList;
import java.util.List;

public class Venta {
    private List<LineaVenta> lineasVenta;
    private MedioPago medioPago;

    public Venta(MedioPago medioPago) {
        this.lineasVenta = new ArrayList<>();
        this.medioPago = medioPago;
    }

    public void agregarProducto(Producto producto, int cantidad) {
        if (producto.getCantidadStock() < cantidad) {
            throw new IllegalArgumentException("No hay suficiente stock para el producto: " + producto.getDescripcion());
        }
        lineasVenta.add(new LineaVenta(producto, cantidad));
        producto.actualizarStock(-cantidad);
    }

    public double calcularTotal() {
        double total = lineasVenta.stream()
                .mapToDouble(LineaVenta::calcularSubtotal)
                .sum();
        return medioPago.calcularMonto(total);
    }

    public void finalizarVenta() {
        System.out.println("Venta finalizada. Total a pagar: $" + calcularTotal());
    }
}

