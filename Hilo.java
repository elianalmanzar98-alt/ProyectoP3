import java.util.List;

public class Hilo extends Thread {
    private final SistemaGestionPedidos sistema;

    public Hilo(SistemaGestionPedidos sistema) {
        this.sistema = sistema;
        this.setPriority(Thread.MAX_PRIORITY); 
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                List<Pedido> pedidos = sistema.getPedidos();
                for (Pedido p : pedidos) {
                    if (p.getEstado().equals("CONFIRMADO")) {
                        Thread.sleep(3000); 
                        p.setEstado("PROCESADO");
                        System.out.println("\n[HILO] Pedido de " + p.getCliente().getNombre() + " PROCESADO.");
                    }
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}