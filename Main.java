import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemaGestionPedidos sistema = new SistemaGestionPedidos();
        sistema.cargarProductos();

       
        ProcesadorPedidos procesador = new ProcesadorPedidos(sistema);
        GeneradorReportes reportador = new GeneradorReportes(sistema);
        procesador.start();
        reportador.start();

        try (Scanner scanner = new Scanner(System.in)) {
            boolean salir = false;

            while (!salir) {
                System.out.println("\n    SISTEMA DE GESTION    ");
                System.out.println("1. Registrar Producto");
                System.out.println("2. Registrar Cliente");
                System.out.println("3. Listar Productos");
                System.out.println("4. Crear Pedido (Múltiples productos)");
                System.out.println("5. Ver Pedidos");
                System.out.println("0. Salir");
                System.out.print("Seleccione: ");

                int opcion = Integer.parseInt(scanner.nextLine());

                try {
                    switch (opcion) {
                        case 1 -> { 
                            System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
                            System.out.print("Nombre: "); String nom = scanner.nextLine();
                            System.out.print("Precio: "); double pre = Double.parseDouble(scanner.nextLine());
                            System.out.print("Stock inicial: "); int st = Integer.parseInt(scanner.nextLine());
                            sistema.agregarProducto(new Producto(id, nom, pre, st));
                            System.out.println("Producto guardado.");
                        }
                        case 2 -> { 
                            System.out.print("ID: "); int idC = Integer.parseInt(scanner.nextLine());
                            System.out.print("Nombre: "); String nomC = scanner.nextLine();
                            System.out.print("Dirección: "); String dir = scanner.nextLine();
                            sistema.agregarCliente(new ClienteRegular(idC, nomC, dir));
                            System.out.println("Cliente registrado.");
                        }
                        case 3 -> sistema.listarProductos();
                        case 4 -> { 
                            sistema.listarClientes();
                            System.out.print("ID del Cliente para el pedido: ");
                            Cliente c = sistema.buscarClientePorId(Integer.parseInt(scanner.nextLine()));
                            
                            if (c != null) {
                                Pedido nuevoPedido = sistema.crearPedido(c);
                                boolean agregando = true;
                                while (agregando) {
                                    sistema.listarProductos();
                                    System.out.print("ID Producto a agregar (0 para finalizar): ");
                                    int idP = Integer.parseInt(scanner.nextLine());
                                    if (idP == 0) break;
                                    
                                    System.out.print("Cantidad: ");
                                    int cant = Integer.parseInt(scanner.nextLine());
                                    
                                    Producto p = sistema.buscarProductoPorId(idP);
                                    nuevoPedido.agregarProducto(p, cant);
                                    System.out.println("¡Agregado!");
                                }
                                nuevoPedido.confirmarPedido(); 
                                sistema.guardarPedidos();
                            }
                        }
                        case 5 -> sistema.getPedidos().forEach(System.out::println);
                        case 0 -> {
                            salir = true;
                            procesador.interrupt();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }
}