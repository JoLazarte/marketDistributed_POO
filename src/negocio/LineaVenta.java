package negocio;


public class LineaVenta {
    private Producto producto;
    private int cantidad;

    public LineaVenta(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        return producto.getPrecioUnitario() * cantidad;
    }
}