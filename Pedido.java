package ProyectoP3;


import java.util.*;

import java.text.SimpleDateFormat;

public class Pedido {
    private Cliente cliente;
    private List<DetallePedido> detalles; 
    private final Date fechaCreacion; 

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.detalles = new ArrayList<>(); 
        this.fechaCreacion = new Date();
    }

    public void agregarProducto(Producto producto, int cantidad) throws StockInsuficienteException {
        if (producto.getStock() < cantidad) {
            throw new StockInsuficienteException("Stock insuficiente."); 
        }
        producto.reducirStock(cantidad);
        detalles.add(new DetallePedido(producto, cantidad));
    }

    public void confirmarPedido() throws PedidoInvalidoException {
        if (detalles.isEmpty()) {
            throw new PedidoInvalidoException("Pedido vacio."); 
        }
        System.out.println("Pedido confirmado.");
    }

    public double calcularTotal() {
        double total = 0;
        for (DetallePedido d : detalles) { total += d.calcularSubtotal(); }
        return cliente.aplicarDescuento(total);
    }

    @Override
    public String toString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        return "Cliente: " + cliente.getNombre() + " | Fecha: " + fmt.format(fechaCreacion) + " | Total: " + calcularTotal();
    }
}