package ProyectoP3;

public class ClienteVIP extends Cliente {
    public ClienteVIP(int id, String nombre) { super(id, nombre); }
    @Override
    public double aplicarDescuento(double total) { return total * 0.90; }
}