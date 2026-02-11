package ProyectoP3;

public class Pedido {

    public static final String BORRADOR = "BORRADOR";
    public static final String CONFIRMADO = "CONFIRMADO";
    public static final String CANCELADO = "CANCELADO";

    private int id;
    private Cliente cliente;
    private String estado;
    private DetallePedido[] detalles;
    private int contador;

    public Pedido(int id, Cliente cliente) {
        this.id = id;
        this.cliente = cliente;
        this.estado = BORRADOR;
        this.detalles = new DetallePedido[10];
        this.contador = 0;
    }

    public int getId() { return id; }
    public String getEstado() { return estado; }

    public DetallePedido[] getDetalles() {
        return detalles;
    }

    public void agregarProducto(Producto producto, int cantidad) {

        if (!estado.equals(BORRADOR))
            throw new IllegalStateException("Solo se puede modificar en BORRADOR");

        detalles[contador++] = new DetallePedido(producto, cantidad);
    }

    public double calcularSubtotal() {
        double total = 0;

        for (int i = 0; i < contador; i++) {
            total += detalles[i].getSubtotal();
        }

        return total;
    }

    public double calcularDescuento() {
        return cliente.calcularDescuento(calcularSubtotal());
    }

    public double calcularTotalFinal() {
        return calcularSubtotal() - calcularDescuento();
    }

    public void confirmar() {

        if (contador == 0)
            throw new IllegalStateException("No se puede confirmar sin productos");

        estado = CONFIRMADO;
    }

    public void cancelar() {
        estado = CANCELADO;
    }
}

