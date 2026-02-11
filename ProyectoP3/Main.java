package ProyectoP3;


import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {
            SistemaGestionPedidos sistema =
                    new SistemaGestionPedidos(20, 20, 20);
            
            int opcion;
            
            do {
                System.out.println("\n           SISTEMA DE GESTIoN DE PEDIDOS       ");
                System.out.println("1. Registrar producto");
                System.out.println("2. Registrar cliente");
                System.out.println("3. Crear pedido");
                System.out.println("4. Agregar producto a pedido");
                System.out.println("5. Ver detalle de pedido");
                System.out.println("6. Listar productos");
                System.out.println("7. Listar pedidos");
                System.out.println("8. Cambiar estado de pedido");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opcion: ");
                
                opcion = sc.nextInt();
                
                switch (opcion) {
                    
                    case 1 -> {
                        System.out.print("ID Producto: ");
                        int idProd = sc.nextInt();
                        System.out.print("Nombre: ");
                        String nombreProd = sc.next();
                        System.out.print("Precio: ");
                        double precio = sc.nextDouble();
                        System.out.print("Stock: ");
                        int stock = sc.nextInt();
                        
                        boolean prodOk = sistema.registrarProducto(
                                new Producto(idProd, nombreProd, precio, stock)
                        );
                        
                        System.out.println(prodOk
                                ? "Producto registrado correctamente"
                                : "ID de producto duplicado");
                    }
                        
                    case 2 -> {
                        System.out.print("ID Cliente: ");
                        int idCli = sc.nextInt();
                        System.out.print("Nombre: ");
                        String nombreCli = sc.next();
                        System.out.print("Tipo (1-Regular o 2-VIP): ");
                        int tipo = sc.nextInt();
                        
                        boolean cliOk;
                        if (tipo == 1) {
                            cliOk = sistema.registrarCliente(
                                    new ClienteRegular(idCli, nombreCli)
                            );
                        } else {
                            System.out.print("descuento aplicable : ");
                            double desc = sc.nextDouble();
                            cliOk = sistema.registrarCliente(
                                    new ClienteVIP(idCli, nombreCli, desc)
                            );
                        }
                        
                        System.out.println(cliOk
                                ? "Cliente registrado correctamente"
                                : " ID de cliente duplicado");
                    }
                        
                    case 3 -> {
                        System.out.print("ID Pedido: ");
                        int idPed = sc.nextInt();
                        System.out.print("ID Cliente: ");
                        int cliPedido = sc.nextInt();
                        
                        Pedido p = (Pedido) sistema.crearPedido(idPed, cliPedido);
                        System.out.println(p != null
                                ? "Pedido creado en estado Borrador"
                                : "Error al crear pedido");
                    }
                        
                    case 4 -> {
                        System.out.print("ID Pedido: ");
                        int pedAdd = sc.nextInt();
                        System.out.print("ID Producto: ");
                        int prodAdd = sc.nextInt();
                        System.out.print("Cantidad: ");
                        int cant = sc.nextInt();
                        
                        System.out.println(
                                sistema.agregarProductoAPedido(pedAdd, prodAdd, cant)
                                        ? "Producto agregado"
                                        : "Error al agregar producto"
                        );
                    }
                        
                    case 6 -> sistema.listarProductos();
                        
                    case 7 -> sistema.listarPedidos();
                        
                    case 8 -> {
                        System.out.print("ID Pedido: ");
                        int pedEstado = sc.nextInt();
                        System.out.print("1. Confirmar  2. Cancelar: ");
                        int est = sc.nextInt();
                        
                        boolean estadoOk = (est == 1)
                                ? sistema.confirmarPedido(pedEstado)
                                : sistema.cancelarPedido(pedEstado);
                        
                        System.out.println(estadoOk
                                ? "Estado actualizado"
                                : "Error al cambiar estado");
                    }
                        
                }
                
            } while (opcion != 0);
            
            System.out.println("Saliendo");
        }
    }
}

