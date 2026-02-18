package ProyectoP3;

public abstract class Cliente {
    protected int id;
    protected String nombre;

    public Cliente(int id, String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del cliente no puede estar vacio.");
        }
        this.id = id;
        this.nombre = nombre.trim();
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public abstract double aplicarDescuento(double total);

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre;
    }
}