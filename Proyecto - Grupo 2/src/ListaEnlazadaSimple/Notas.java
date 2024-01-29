package ListaEnlazadaSimple;

public class Notas {

    private String titulo;
    private String descripcion;

    public Notas() {
        this.titulo = "";
        this.descripcion = "";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "\nTítulo: " + titulo 
                + "\nDescripción: " + descripcion + "\n" ;
    }
    
    

}
