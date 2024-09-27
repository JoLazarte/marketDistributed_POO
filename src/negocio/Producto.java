package negocio;

public class Producto {
    private String codigo;
    private String descripcion;
    private double precioUnitario;
    private int cantidadStock;
    private int stockMinimo;

    public Producto(String codigo, String descripcion, double precioUnitario, int cantidadStock, int stockMinimo) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.cantidadStock = cantidadStock;
        this.stockMinimo = stockMinimo;
    }

    public void actualizarStock(int cantidad) {
        this.cantidadStock += cantidad;
    }

    public boolean esBajoStock() {
        return this.cantidadStock <= this.stockMinimo;
    }

    // Getters y setters
    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }
}