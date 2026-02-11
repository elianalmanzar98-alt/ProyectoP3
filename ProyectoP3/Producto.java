package ProyectoP3;


public class Producto{


    private int id;
    private String nombre;
    private double precio;
    private int stock;


    public Producto(int id, String nombre, double precio, int stock){

        if (precio <=0 ) throw new IllegalArgumentException("El precio no es valido");

        if (stock < 0) throw new IllegalArgumentException("El stock no es valido");

        this.id =id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock =stock;
    }


    public int getId(){return id;}

    public String getNombre(){
        return nombre;
    }

    public double getPrecio(){
        return precio;
    }

    public int getStock(){
        return stock;
    }

    public void reducirStock(int cantidad){
        this.stock-=cantidad;
    }

    public void aumentarStock(int cantidad){
        this.stock += cantidad;
    }

}