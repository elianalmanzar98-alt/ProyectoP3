public class GeneradorReportes extends Thread {
    private final SistemaGestionPedidos sistema;

    public GeneradorReportes(SistemaGestionPedidos sistema) {
        this.sistema = sistema;
        this.setDaemon(true); 
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10000); 
                sistema.generarReporteConsolidado();
                System.out.println("\n[SISTEMA] Reporte automatico actualizado.");
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}