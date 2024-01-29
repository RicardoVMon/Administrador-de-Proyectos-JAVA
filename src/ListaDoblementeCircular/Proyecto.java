package ListaDoblementeCircular;

import Recursividad.BusquedaRecursiva;
import Cola.Cola;
import ListaEnlazadaSimple.ListaSimple;
import javax.swing.JOptionPane;

public class Proyecto {

    private Cola colaTareas;
    private BusquedaRecursiva busquedaDatos;
    private ListaSimple listaComentarios;

    private String nombre, descripcion, fechaInicio, duracionEstimada;
    private int id;

    public Proyecto() {
        colaTareas = new Cola();
        busquedaDatos = new BusquedaRecursiva();
        listaComentarios = new ListaSimple();
        this.nombre = "";
        this.descripcion = "";
        this.fechaInicio = "";
        this.duracionEstimada = "";
        this.id = 0;
    }

    public void ingresar() {

        while (true) {
            try {
                String opcion = JOptionPane.showInputDialog(null,
                        "\n[1] - Administrar tareas"
                        + "\n[2] - Administrar notas"
                        + "\n[3] - Ver Información de Proyecto"
                        + "\n[4] - Volver a navegador"
                        + "\n\nIngrese la opción de su preferencia: ",
                        "PROYECTO:  " + nombre,
                        JOptionPane.PLAIN_MESSAGE);

                switch (opcion) {
                    case "1":
                        colaTareas.menuTareas();
                        break;
                    case "2":
                        listaComentarios.menuNotas();
                        break;
                    case "3":
                        busquedaDatos.mostrarDatosProyecto(toString(), listaComentarios, colaTareas);
                        break;
                    case "4":
                        return;
                    default:
                        JOptionPane.showMessageDialog(null,
                                "Error: Ingrese una opción válida",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException e) {
                return;
            }

        }

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getDuracionEstimada() {
        return duracionEstimada;
    }

    public void setDuracionEstimada(String duracionEstimada) {
        this.duracionEstimada = duracionEstimada;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\nNombre del proyecto: " + nombre
                + "\nID: " + id
                + "\nDescripción: " + descripcion
                + "\nFecha de inicio: " + fechaInicio
                + "\nDuración estimada: " + duracionEstimada + " días";
    }

}
