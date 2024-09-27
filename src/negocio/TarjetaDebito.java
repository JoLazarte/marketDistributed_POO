package negocio;

public class TarjetaDebito extends MedioPago {
    @Override
    public double calcularMonto(double total) {
        return total; // Sin recargo
    }
}