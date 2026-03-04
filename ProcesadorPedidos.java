import java.util.List;

public class ProcesadorPedidos extends Thread { 
    private final SistemaGestionPedidos sistema;

    public ProcesadorPedidos(SistemaGestionPedidos sistema) {
        this.sistema = sistema;
        this.setPriority(Thread.MAX_PRIORITY); 
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                
                List<Pedido> listaPedidos = sistema.getPedidos();
                for (Pedido p : listaPedidos) {
                    if (p.getEstado().equals("CONFIRMADO")) {
                        Thread.sleep(3000); 
                        p.setEstado("PROCESADO");
                        System.out.println("\n[Procesador] Pedido de " + p.getCliente().getNombre() + " ha sido PROCESADO.");
                        
                        sistema.guardarPedidos(); 
                    }
                }
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}