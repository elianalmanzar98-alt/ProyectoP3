
import java.io.Serializable;


public abstract class Cliente implements Serializable {
    protected int id;
    protected String nombre;
    protected String direccion; 

    public Cliente(int id, String nombre, String direccion) { 
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacio.");
        }
        this.id = id;
        this.nombre = nombre.trim();
        this.direccion = direccion; 
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDireccion() { return direccion; } 
    public abstract double aplicarDescuento(double total);

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre + " | Direccion: " + direccion;
    }
}