package ProyectoP3;
public class SistemaGestionPedidos {
    private Producto[] productos;
    private Cliente[] clientes;
    private Pedido[] pedidos;

    private int contProd;
    private int contCli;
    private int contPed;

    public SistemaGestionPedidos(int maxProductos, int maxClientes, int maxPedidos) {
        productos = new Producto[maxProductos];
        clientes = new Cliente[maxClientes];
        pedidos = new Pedido[maxPedidos];
        contProd = 0;
        contCli = 0;
        contPed = 0;
    }

    
    public boolean registrarProducto(Producto p) {
        if (buscarProductoPorId(p.getId()) != null) return false;
        productos[contProd++] = p;
        return true;
    }

    public Producto buscarProductoPorId(int id) {
        for (int i = 0; i < contProd; i++) {
            if (productos[i].getId() == id) return productos[i];
        }
        return null;
    }

    public Producto buscarProductoPorNombre(String nombre) {
        for (int i = 0; i < contProd; i++) {
            if (productos[i].getNombre().equalsIgnoreCase(nombre)) return productos[i];
        }
        return null;
    }

    public void listarProductos() {
        for (int i = 0; i < contProd; i++) {
            System.out.println(productos[i].getId() + " - " + productos[i].getNombre() + " - Stock: " + productos[i].getStock());
        }
    }

    
    public boolean registrarCliente(Cliente c) {
        if (buscarCliente(c.getId()) != null) return false;
        clientes[contCli++] = c;
        return true;
    }

    public Cliente buscarCliente(int id) {
        for (int i = 0; i < contCli; i++) {
            if (clientes[i].getId() == id) return clientes[i];
        }
        return null;
    }

    
    public Pedido crearPedido(int idPedido, int idCliente) {
        if (buscarPedido(idPedido) != null) return null;
        Cliente c = buscarCliente(idCliente);
        if (c == null) return null;
        Pedido p = new Pedido(idPedido, c);
        pedidos[contPed++] = p;
        return p;
    }

    public Pedido buscarPedido(int id) {
        for (int i = 0; i < contPed; i++) {
            if (pedidos[i].getId() == id) return pedidos[i];
        }
        return null;
    }

    public boolean agregarProductoAPedido(int idPedido, int idProducto, int cantidad) {
        Pedido ped = buscarPedido(idPedido);
        Producto prod = buscarProductoPorId(idProducto);
        if (ped == null || prod == null) return false;
        if (prod.getStock() < cantidad) return false;
        ped.agregarProducto(prod, cantidad);
        return true;
    }

    public boolean confirmarPedido(int idPedido) {
        Pedido p = buscarPedido(idPedido);
        if (p == null) return false;
        p.confirmar();
        for (DetallePedido d : p.getDetalles()) {
            if (d != null) d.getProducto().reducirStock(d.getCantidad());
        }
        return true;
    }

    public boolean cancelarPedido(int idPedido) {
        Pedido p = buscarPedido(idPedido);
        if (p == null) return false;
        if (p.getEstado().equals(Pedido.CONFIRMADO)) {
            for (DetallePedido d : p.getDetalles()) {
                if (d != null) d.getProducto().aumentarStock(d.getCantidad());
            }
        }
        p.cancelar();
        return true;
    }

    public void listarPedidos() {
        for (int i = 0; i < contPed; i++) {
            System.out.println("Pedido " + pedidos[i].getId() + " - Estado: " + pedidos[i].getEstado());
        }
    }
}

