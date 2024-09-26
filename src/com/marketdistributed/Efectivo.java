package com.marketdistributed;

public class Efectivo extends MedioPago {
    @Override
    public double calcularMonto(double total) {
        return total * 0.9; // 10% de descuento
    }
}