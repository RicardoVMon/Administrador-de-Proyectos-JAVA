package Recursividad;

import Cola.Cola;
import Cola.NodoCola;
import ListaEnlazadaSimple.ListaSimple;
import ListaEnlazadaSimple.NodoSimple;
import javax.swing.JOptionPane;

public class BusquedaRecursiva {

    public int cantidadTareas(Cola colaTareas, NodoCola auxiliar, int contador) {
        if (auxiliar == null) {
            return contador;
        } else {
            return cantidadTareas(colaTareas, auxiliar.getSiguiente(), contador + 1);
        }
    }

    public int cantidadNotas(ListaSimple listaNotas, NodoSimple auxiliar, int contador) {
        if (auxiliar == null) {
            return contador;
        } else {
            return cantidadNotas(listaNotas, auxiliar.getSiguiente(), contador + 1);
        }
    }

    public void mostrarDatosProyecto(String datosBase, ListaSimple listaNotas, Cola colaTareas) {

        String estadoNotas = (listaNotas.vacia()) ? "No hay notas en el proyecto" : "Hay " + cantidadNotas(listaNotas, listaNotas.inicio, 0) + " nota(s) en el proyecto";
        String estadoTareas = (colaTareas.esVacia()) ? "No hay tareas en el proyecto" : "Hay " + cantidadTareas(colaTareas, colaTareas.frente, 0) + " tarea(s) en el proyecto";

        JOptionPane.showMessageDialog(null,
                datosBase
                + "\n---------------------------------------------\n"
                + estadoTareas + "\n" + estadoNotas + "\n\n",
                "INFORMACIÓN DE PROYECTO",
                JOptionPane.PLAIN_MESSAGE);

    }

}
