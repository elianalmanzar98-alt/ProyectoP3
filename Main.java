package ProyectoP3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        SistemaGestionPedidos sistema = new SistemaGestionPedidos();

        try (Scanner scanner = new Scanner(System.in)) {
            
            sistema.agregarProducto(new Producto(1, "Laptop", 50000, 5));
            sistema.agregarProducto(new Producto(2, "Mouse", 1000, 20));
            
            sistema.agregarCliente(new ClienteRegular(1, "Juan"));
            sistema.agregarCliente(new ClienteVIP(2, "Maria"));
            
            boolean salir = false;
            
            while (!salir) {
                
                System.out.println("\n1. Listar productos");
                System.out.println("2. Buscar producto por nombre");
                System.out.println("3. Crear pedido");
                System.out.println("4. Listar pedidos");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opcion: ");
                
                
                int opcion;
                try {
                    opcion = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("Error: Ingrese un número válido.");
                    continue;
                }
                
                try {
                    
                    switch (opcion) {
                        
                        case 1 -> {
                            System.out.println("    Productos disponibles   ");
                            sistema.listarProductos();
                        }
                        
                        case 2 -> {
                            System.out.println("Ingrese nombre:");
                            String nombre = scanner.nextLine();
                            sistema.buscarProductoPorNombre(nombre);
                        }
                        
                        case 3 -> {
                           

                            Pedido pedido = sistema.crearPedido(new ClienteRegular(3, "ClientePrueba"));
                            
                            
                            Producto producto = sistema.buscarProductoPorId(1);
                            
                            
                            pedido.agregarProducto(producto, 1);
                            
                          
                            pedido.confirmarPedido();
                        }
                        
                        case 4 -> {
                            System.out.println("    Historial de Pedidos  ");
                            sistema.listarPedidos();
                        }
                        
                        case 5 -> salir = true;

                        default -> System.out.println("Opcion no valida ");
                    }
                    
                } catch (ProductoNoEncontradoException | StockInsuficienteException | PedidoInvalidoException e) {
                    
                    System.out.println("Error en la operación: " + e.getMessage());
                } catch (Exception e) {
                    
                    System.out.println("Error inesperado: " + e.getMessage());
                } finally {
                    
                    System.out.println(" finalizando... \n");
                }
            }
        }
    }
}