package Cola;

public class NodoCola {

    private Tarea tarea;
    private NodoCola siguiente;

    public NodoCola(Tarea tarea) {
        this.tarea = tarea;
        this.siguiente = null;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public NodoCola getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCola siguiente) {
        this.siguiente = siguiente;
    }
}
