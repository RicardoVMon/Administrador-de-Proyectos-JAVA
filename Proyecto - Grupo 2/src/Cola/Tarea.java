package Cola;

public class Tarea {

    private String nombre;
    private String descripcion;

    public Tarea() {
        this.nombre = "";
        this.descripcion = "";
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "\nNombre: " + nombre + "\nDescripción: " + descripcion + "\n";
    }

}
