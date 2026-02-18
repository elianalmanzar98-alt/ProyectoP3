package ProyectoP3;

import java.util.*;
import java.text.SimpleDateFormat;

public class SistemaGestionPedidos {
    private final List<Producto> productos = new ArrayList<>();
    private final List<Cliente> clientes = new ArrayList<>();
    private final List<Pedido> pedidos = new ArrayList<>();

    public void agregarProducto(Producto p) { productos.add(p); }
    public void agregarCliente(Cliente c) { clientes.add(c); }

    public void listarProductos() {
        for (Producto p : productos) System.out.println(p);
    }

    public void listarClientes() {
        if (clientes.isEmpty()) System.out.println("No hay clientes.");
        for (Cliente c : clientes) System.out.println(c);
    }

    public void listarPedidos() {
        if (pedidos.isEmpty()) System.out.println("No hay pedidos registrados.");
        for (Pedido p : pedidos) System.out.println(p);
    }

    
    public void listarPedidosPorFecha(String fechaBusqueda) {
        SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
        boolean encontrado = false;
        for (Pedido p : pedidos) {
            String fechaPedido = fmt.format(p.getFechaCreacion());
            if (fechaPedido.equals(fechaBusqueda)) {
                System.out.println(p);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No hay pedidos en la fecha: " + fechaBusqueda);
    }

    public Producto buscarProductoPorId(int id) throws ProductoNoEncontradoException {
        for (Producto p : productos) if (p.getId() == id) return p;
        throw new ProductoNoEncontradoException("Producto ID " + id + " no encontrado.");
    }

    public Cliente buscarClientePorId(int id) throws Exception {
        for (Cliente c : clientes) if (c.getId() == id) return c;
        throw new Exception("Cliente no encontrado.");
    }

    public void buscarProductoPorNombre(String nombre) {
        String texto = nombre.trim().toLowerCase();
        for (Producto p : productos) {
            if (p.getNombre().toLowerCase().contains(texto)) System.out.println(p);
        }
    }

    public void eliminarProducto(int id) throws ProductoNoEncontradoException {
        Iterator<Producto> it = productos.iterator();
        while (it.hasNext()) {
            if (it.next().getId() == id) {
                it.remove();
                return;
            }
        }
        throw new ProductoNoEncontradoException("Producto no encontrado.");
    }

    public Pedido crearPedido(Cliente cliente) {
        Pedido pedido = new Pedido(cliente);
        pedidos.add(pedido);
        return pedido;
    }
}