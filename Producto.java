package ProyectoP3;
import java.io.Serializable;

public class Producto implements Serializable {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio.");
        }
        this.id = id;
        this.nombre = nombre.trim();
        this.precio = precio;
        this.stock = stock;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    
    public void reducirStock(int cantidad) throws StockInsuficienteException {
        if (cantidad > this.stock) throw new StockInsuficienteException("No hay suficiente stock.");
        this.stock -= cantidad;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Precio: " + precio + " | Stock: " + stock;
    }
}