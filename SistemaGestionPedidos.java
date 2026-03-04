import java.io.*;
import java.util.*;

public class SistemaGestionPedidos {
    
    private final List<Producto> productos = Collections.synchronizedList(new ArrayList<>());
    private final List<Cliente> clientes = Collections.synchronizedList(new ArrayList<>());
    private final List<Pedido> pedidos = Collections.synchronizedList(new ArrayList<>());

    

    public synchronized void guardarProductos() {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("productos.dat"))) {
            for (Producto p : productos) {
                dos.writeInt(p.getId());
                dos.writeUTF(p.getNombre());
                dos.writeDouble(p.getPrecio());
                dos.writeInt(p.getStock());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar productos binarios: " + e.getMessage());
        }
    }

    public synchronized void cargarProductos() {
        try (DataInputStream dis = new DataInputStream(new FileInputStream("productos.dat"))) {
            productos.clear();
            while (dis.available() > 0) {
                int id = dis.readInt();
                String nombre = dis.readUTF();
                double precio = dis.readDouble();
                int stock = dis.readInt();
                productos.add(new Producto(id, nombre, precio, stock));
            }
        } catch (FileNotFoundException e) {
            System.out.println("Archivo productos.dat no encontrado, se iniciará vacío.");
        } catch (IOException e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
        }
    }

    public synchronized void guardarPedidos() {
        try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pedidos.txt")))) {
            for (Pedido p : pedidos) {
                
                pw.println(p.toString());
            }
        } catch (IOException e) {
            System.err.println("Error al guardar pedidos en texto: " + e.getMessage());
        }
    }

    public synchronized void generarReporteConsolidado() {
    try (PrintWriter pw = new PrintWriter(new FileWriter("reporte_sistema.txt"))) {
        pw.println("    REPORTE DETALLADO DEL SISTEMA    ");
        pw.println("Fecha y hora: " + new Date());
        pw.println("-------------------------------------");
        
        
        int pendientes = 0;
        int confirmados = 0;
        int procesados = 0;
        double ingresosTotales = 0;

       
        for (Pedido p : pedidos) {
            String estado = p.getEstado().toUpperCase();
            switch (estado) {
                case "PENDIENTE" -> pendientes++;
                case "CONFIRMADO" -> {
                    confirmados++;
                    ingresosTotales += p.calcularTotal();
                }
                case "PROCESADO" -> {
                    procesados++;
                    ingresosTotales += p.calcularTotal();
                }
            }
        }

        pw.println("Pedidos PENDIENTES: " + pendientes);
        pw.println("Pedidos CONFIRMADOS: " + confirmados);
        pw.println("Pedidos PROCESADOS: " + procesados);
        pw.println("Ingresos totales: $" + ingresosTotales);

        pw.flush();
    } catch (IOException e) {
        System.err.println("Error al generar reporte consolidado: " + e.getMessage());
    }
}
    

    public synchronized void agregarProducto(Producto p) {
        productos.add(p);
        guardarProductos(); 
    }

    public synchronized void agregarCliente(Cliente c) {
        clientes.add(c);
    }

    public synchronized Pedido crearPedido(Cliente c) {
        Pedido nuevo = new Pedido(c);
        pedidos.add(nuevo);
        return nuevo;
    }

    public synchronized Producto buscarProductoPorId(int id) throws ProductoNoEncontradoException {
        for (Producto p : productos) {
            if (p.getId() == id) return p;
        }
        throw new ProductoNoEncontradoException("El producto con ID " + id + " no existe.");
    }

    public synchronized Cliente buscarClientePorId(int id) {
        for (Cliente c : clientes) {
            if (c.getId() == id) return c;
        }
        return null;
    }

    public synchronized void buscarProductoPorNombre(String nombre) {
        boolean encontrado = false;
        for (Producto p : productos) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(p);
                encontrado = true;
            }
        }
        if (!encontrado) System.out.println("No se encontro ningun producto con ese nombre.");
    }

    public synchronized void listarProductos() {
        if (productos.isEmpty()) System.out.println("No hay productos registrados.");
        else productos.forEach(System.out::println);
    }

    public synchronized void listarClientes() {
        if (clientes.isEmpty()) System.out.println("No hay clientes registrados.");
        else clientes.forEach(System.out::println);
    }

    public synchronized List<Pedido> getPedidos() {
       
        return new ArrayList<>(pedidos);
    }
}