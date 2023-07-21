package Datos;

public class Jugador {
    private int id;
    private String nombre;
    private int dorsal;
    private double altura;

    public Jugador(int id, String nombre, int dorsal, double altura) {
        this.id = id;
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.altura = altura;
    }

    // Getter and Setter methods for the private fields (id, nombre, dorsal, altura)

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

//    // You can also override the toString() method for better representation of the object.
//    @Override
//    public String toString() {
//        return "Jugador{" +
//                "id=" + id +
//                ", nombre='" + nombre + '\'' +
//                ", dorsal=" + dorsal +
//                ", altura=" + altura +
//                '}';
//    }
}
