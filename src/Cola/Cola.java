package Cola;

import javax.swing.JOptionPane;

public class Cola {

    public NodoCola frente;
    private NodoCola fin;

    public Cola() {
        this.frente = null;
        this.fin = null;
    }

    public boolean esVacia() {
        return (frente == null);
    }

    private Tarea crearTarea() {
        String nombre;
        do {
            nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de la tarea:", "NOMBRE DE TAREA", JOptionPane.QUESTION_MESSAGE);
        } while (nombre == null || nombre.equals(""));

        String descripcion;
        do {
            descripcion = JOptionPane.showInputDialog(null, "Ingrese una breve descripción de la tarea:", "DESCRIPCIÓN DE TAREA", JOptionPane.QUESTION_MESSAGE);
        } while (descripcion == null || descripcion.equals(""));

        Tarea tarea = new Tarea();
        tarea.setNombre(nombre);
        tarea.setDescripcion(descripcion);

        return tarea;
    }

    private void confirmarIngreso() {
        JOptionPane.showMessageDialog(null,
                "Tarea insertada correctamente",
                "EXITO",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public void encolar() {

        NodoCola nuevoNodo = new NodoCola(crearTarea());
        if (esVacia()) {
            frente = nuevoNodo;
            fin = nuevoNodo;
            confirmarIngreso();
        } else {
            fin.setSiguiente(nuevoNodo);
            fin = nuevoNodo;
            confirmarIngreso();
        }
    }

    public void desencolar() {

        String opciones[]
                = {"Sí", "No"};

        int opcionSeleccionada = JOptionPane.showOptionDialog(null,
                "Se dará la tarea '" + frente.getTarea().getNombre() + "' como completada"
                + "\n¿Seguro que desea continuar?",
                "CONFIRMAR COMPLETADO",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        switch (opcionSeleccionada) {
            case 0:
                JOptionPane.showMessageDialog(null,
                        "Tarea: " + frente.getTarea().getNombre() + " marcada como completada",
                        "EXITO",
                        JOptionPane.INFORMATION_MESSAGE);
                frente = frente.getSiguiente();
                if (frente == null) {
                    fin = null;
                }
                break;
            case 1:
                return;
        }

    }

    private int contadorElementos() {
        int contador = 0;
        NodoCola aux = frente;
        while (aux != null) {
            contador++;
            aux = aux.getSiguiente();
        }
        return contador;
    }

    private String[] generarListaTitulos() {
        int cantidadElementos = contadorElementos();
        String listaTitulos[] = new String[cantidadElementos];
        NodoCola auxiliar = frente;
        for (int i = 0; i < listaTitulos.length; i++) {
            listaTitulos[i] = auxiliar.getTarea().getNombre();
            auxiliar = auxiliar.getSiguiente();
        }
        return listaTitulos;
    }

    public void extraerPorTitulo(String titulo) {
        if (frente.getTarea().getNombre().equals(titulo)) {
            frente = frente.getSiguiente();
            if (frente == null) {
                fin = null;
            }
            JOptionPane.showMessageDialog(null, "Tarea eliminada correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);
        } else {

            NodoCola anterior = frente;
            NodoCola actual = frente.getSiguiente();
            while (!(actual.getTarea().getNombre().equals(titulo))) {
                anterior = actual;
                actual = actual.getSiguiente();
            }
            anterior.setSiguiente(actual.getSiguiente());
            JOptionPane.showMessageDialog(null, "Nota eliminada correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void borrarEspecifica() {
        if (esVacia()) {
            JOptionPane.showMessageDialog(null, "Error: La cola de tareas está vacía.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            if (esVacia()) {
                JOptionPane.showMessageDialog(null, "No hay notas por extraer", "Error", JOptionPane.ERROR_MESSAGE);
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

    }

    public void tareaEnDesarrollo() {
        if (esVacia()) {
            JOptionPane.showMessageDialog(null, "Error: La cola de tareas está vacía.", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                String opciones[]
                        = {"Marcar como completada", "Volver"};
                int opcionSeleccionada = JOptionPane.showOptionDialog(null,
                        frente.getTarea().toString()
                        + "\nSeleccione una opción:",
                        "TAREA EN DESARROLLO",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        opciones,
                        opciones[0]);

                switch (opcionSeleccionada) {
                    case 0:
                        desencolar();
                        return;
                    case 1:
                        return;
                }
            } catch (NullPointerException e) {
            }

        }

    }

    public void mostrarTareasPendientes() {
        if (esVacia()) {
            JOptionPane.showMessageDialog(null, "La cola de tareas está vacía.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            NodoCola actual = frente;
            boolean primeraTarea = true;

            while (actual != null) {
                if (primeraTarea) {
                    int option = JOptionPane.showOptionDialog(null,
                            "Tarea en Desarrollo:\n"
                            + "Nombre: " + actual.getTarea().getNombre() + "\n"
                            + "Descripción: " + actual.getTarea().getDescripcion(),
                            "Tarea en Desarrollo",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new Object[]{"Siguiente"},
                            "Siguiente");

                    if (option == 0) {
                        primeraTarea = false;
                    }
                } else {
                    JOptionPane.showOptionDialog(null,
                            "Tarea Pendiente:\n"
                            + "Nombre: " + actual.getTarea().getNombre() + "\n"
                            + "Descripción: " + actual.getTarea().getDescripcion(),
                            "Tarea Pendiente",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE,
                            null,
                            new Object[]{"Siguiente"},
                            "Siguiente");
                }
                actual = actual.getSiguiente();
            }

            // Después de salir del bucle, para indicar el fin de la cola 
            JOptionPane.showMessageDialog(null,
                    "No hay más tareas en la cola.\nHaz clic en ok para volver al menú de tareas.",
                    "Fin de las tareas",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void menuTareas() {

        while (true) {

            try {
                String opcion = JOptionPane.showInputDialog(null,
                        "\n[1] - Ver Tarea en desarrollo\n"
                        + "[2] - Agregar tarea\n"
                        + "[3] - Borrar tarea específica\n"
                        + "[4] - Mostrar tareas pendientes\n"
                        + "[5] - Regresar\n"
                        + "\nDigite una opcion para continuar",
                        "MENU DE TAREAS",
                        JOptionPane.PLAIN_MESSAGE);

                switch (opcion) {
                    case "1":
                        tareaEnDesarrollo();
                        break;
                    case "2":
                        encolar();
                        break;
                    case "3":
                        borrarEspecifica();
                        break;
                    case "4":
                        mostrarTareasPendientes();
                        break;
                    case "5":
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
