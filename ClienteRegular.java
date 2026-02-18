package ProyectoP3;

public class ClienteRegular extends Cliente {
    public ClienteRegular(int id, String nombre) { super(id, nombre); }
    @Override
    public double aplicarDescuento(double total) { return total; }
}