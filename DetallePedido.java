package ProyectoP3;

public class DetallePedido {
    private final Producto producto;
    private int cantidad;

    public DetallePedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = Math.max(cantidad, 1);
    }

    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return producto.getNombre() + " | Cantidad: " + cantidad + " | Subtotal: " + calcularSubtotal();
    }
}