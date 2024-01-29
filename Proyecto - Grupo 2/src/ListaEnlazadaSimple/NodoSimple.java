package ListaEnlazadaSimple;

public class NodoSimple {

    private Notas dato;
    private NodoSimple siguiente;

    public NodoSimple() {
        this.siguiente = null;
    }

    public Notas getDato() {
        return dato;
    }

    public void setDato(Notas dato) {
        this.dato = dato;
    }

    public NodoSimple getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSimple siguiente) {
        this.siguiente = siguiente;
    }
}
