

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

public class Pedido implements Serializable {
    private final Cliente cliente;
    private final List<DetallePedido> detalles; 
    private final Date fechaCreacion; 
    private String estado; 

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.detalles = new ArrayList<>(); 
        this.fechaCreacion = new Date();
        this.estado = "PENDIENTE"; 
    }

    
    public synchronized String getEstado() { return estado; }
    public synchronized void setEstado(String estado) { this.estado = estado; }
    
    public Date getFechaCreacion() { return this.fechaCreacion; }
    public Cliente getCliente() { return cliente; }

    
    public synchronized void agregarProducto(Producto producto, int cantidad) throws StockInsuficienteException {
        producto.reducirStock(cantidad);
        detalles.add(new DetallePedido(producto, cantidad));
    }

    public synchronized void confirmarPedido() throws PedidoInvalidoException {
        if (detalles.isEmpty()) {
            throw new PedidoInvalidoException("Pedido vacio."); 
        }
        this.estado = "CONFIRMADO"; 
        System.out.println("Pedido confirmado.");
    }

    public synchronized double calcularTotal() {
        double total = 0;
        for (DetallePedido d : detalles) { total += d.calcularSubtotal(); }
        return cliente.aplicarDescuento(total);
    }

    @Override
    public String toString() {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
        return "Cliente: " + cliente.getNombre() + " | Fecha: " + fmt.format(fechaCreacion) + 
               " | Estado: " + estado + " | Total: " + calcularTotal();
    }
}