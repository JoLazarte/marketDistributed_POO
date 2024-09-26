package com.marketdistributed;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    private List<Producto> productos;

    public Catalogo() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Producto obtenerProducto(String codigo) {
        return productos.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    public List<Producto> listarProductos() {
        return new ArrayList<>(productos);
    }

    public void marcarProductosBajoStock() {
        productos.forEach(p -> {
            if (p.esBajoStock()) {
                System.out.println("Producto bajo stock: " + p.getDescripcion());
            }
        });
    }
}