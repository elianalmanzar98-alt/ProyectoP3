public class ClienteVIP extends Cliente {
    public ClienteVIP(int id, String nombre, String direccion) { 
        super(id, nombre, direccion); 
    }
    @Override
    public double aplicarDescuento(double total) { return total * 0.90; }
}