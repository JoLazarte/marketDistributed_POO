package negocio;

public class TarjetaCredito extends MedioPago {
    private int cuotas;

    public TarjetaCredito(int cuotas) {
        if (cuotas != 2 && cuotas != 3 && cuotas != 6) {
            throw new IllegalArgumentException("Las cuotas solo pueden ser 2, 3 o 6");
        }
        this.cuotas = cuotas;
    }

    @Override
    public double calcularMonto(double total) {
        switch (cuotas) {
            case 2:
                return total * 1.06; // 6% de recargo
            case 3:
                return total * 1.12; // 12% de recargo
            case 6:
                return total * 1.20; // 20% de recargo
            default:
                throw new IllegalStateException("N�mero de cuotas no v�lido");
        }
    }
}