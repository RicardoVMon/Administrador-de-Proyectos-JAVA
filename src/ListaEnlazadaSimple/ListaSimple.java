package ListaEnlazadaSimple;

import javax.swing.JOptionPane;

public class ListaSimple {

    public NodoSimple inicio;

    public ListaSimple() {
        this.inicio = null;
    }

    public boolean vacia() {
        return (inicio == null);
    }

    // Acá hago validaciones
    private void ingresarDatos(Notas dato) {
        String titulo;
        do {
            titulo = JOptionPane.showInputDialog(null, "Ingrese el título de la nota", "TITULO", JOptionPane.PLAIN_MESSAGE);
        } while (titulo == null || titulo.equals(""));

        String descripcion;
        do {
            descripcion = JOptionPane.showInputDialog(null, "Ingrese una descripción para la nota", "DESCRIPCIÓN", JOptionPane.PLAIN_MESSAGE);
        } while (descripcion == null || descripcion.equals(""));

        dato.setTitulo(titulo);
        dato.setDescripcion(descripcion);
    }

    private void confirmarIngreso() {
        JOptionPane.showMessageDialog(null,
                "Nota insertada correctamente",
                "EXITO",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void agregar() {

        Notas dato = new Notas();
        NodoSimple nuevo = new NodoSimple();
        ingresarDatos(dato);
        nuevo.setDato(dato);

        if (vacia()) {
            inicio = nuevo;
            confirmarIngreso();

        } else if (inicio.getSiguiente() == null) {
            inicio.setSiguiente(nuevo);
            confirmarIngreso();
        } else {
            NodoSimple aux = inicio;
            while ((aux.getSiguiente() != null)) {
                aux = aux.getSiguiente();
            }
            nuevo.setSiguiente(aux.getSiguiente());
            aux.setSiguiente(nuevo);
            confirmarIngreso();

        }

    }

    public void extraerPorTitulo(String titulo) {
        if (inicio.getDato().getTitulo().equals(titulo)) {
            inicio = inicio.getSiguiente();
            JOptionPane.showMessageDialog(null, "Nota eliminada correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);
        } else {

            NodoSimple anterior = inicio;
            NodoSimple actual = inicio.getSiguiente();
            while (!(actual.getDato().getTitulo().equals(titulo))) {
                anterior = actual;
                actual = actual.getSiguiente();
            }
            anterior.setSiguiente(actual.getSiguiente());
            JOptionPane.showMessageDialog(null, "Nota eliminada correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void extraer() {
        // Este es el método que usaremos para extraer la primera nota en la lista.
        if (vacia()) {
            JOptionPane.showMessageDialog(null, "No hay notas por extraer", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String listaTitulos[] = generarListaTitulos();
                Object objetoNota = JOptionPane.showInputDialog(null, "Seleccione el titulo de la nota por eliminar: ",
                        "SELECCIONAR TITULO", JOptionPane.QUESTION_MESSAGE, null,
                        listaTitulos, listaTitulos[0]);
                String titulo = objetoNota.toString();
                extraerPorTitulo(titulo);
            } catch (NullPointerException e) {
            }

        }
    }

    private int contadorElementos() {
        int contador = 0;
        NodoSimple aux = inicio;
        while (aux != null) {
            contador++;
            aux = aux.getSiguiente();
        }
        return contador;
    }

    private String[] generarListaTitulos() {
        int cantidadElementos = contadorElementos();
        String listaTitulos[] = new String[cantidadElementos];
        NodoSimple auxiliar = inicio;
        for (int i = 0; i < listaTitulos.length; i++) {
            listaTitulos[i] = auxiliar.getDato().getTitulo();
            auxiliar = auxiliar.getSiguiente();
        }
        return listaTitulos;
    }

    private void buscarPorTitulo(String titulo) {
        NodoSimple auxiliar = inicio;
        while (!(auxiliar.getDato().getTitulo().equals(titulo))) {
            auxiliar = auxiliar.getSiguiente();
        }
        JOptionPane.showMessageDialog(null,
                auxiliar.getDato().toString(),
                "INFORMACIÓN DE NOTA: " + auxiliar.getDato().getTitulo(),
                JOptionPane.PLAIN_MESSAGE);
    }

    public void mostrar() {
        // Este es el método para desplegar todas las notas
        if (vacia()) {
            JOptionPane.showMessageDialog(null, "Error: No se han agregado notas",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String listaTitulos[] = generarListaTitulos();
                Object objetoNota = JOptionPane.showInputDialog(null, "Seleccione el titulo de la nota por mostrar: ",
                        "SELECCIONAR TITULO", JOptionPane.QUESTION_MESSAGE, null,
                        listaTitulos, listaTitulos[0]);

                String titulo = objetoNota.toString();
                buscarPorTitulo(titulo);
            } catch (NullPointerException e) {
            }

        }
    }

    public void menuNotas() {

        // Menú que desplegará las opciones de administración de notas.
        while (true) {

            try {
                String opcion = JOptionPane.showInputDialog(null,
                        "\n[1] - Agregar nota\n"
                        + "[2] - Extraer nota\n"
                        + "[3] -  Mostrar notas\n"
                        + "[4] - Regresar\n"
                        + "\nDigite una opcion para continuar",
                        "MENU DE NOTAS",
                        JOptionPane.PLAIN_MESSAGE);
                switch (opcion) {
                    case "1":
                        agregar();
                        break;
                    case "2":
                        extraer();
                        break;
                    case "3":
                        mostrar();
                        break;
                    case "4":
                        return;
                    default:
                        JOptionPane.showMessageDialog(null, "Error: opción inválida",
                                "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException e) {
                return;
            }

        }
    }
}
