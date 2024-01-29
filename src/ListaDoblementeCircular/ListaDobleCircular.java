package ListaDoblementeCircular;

import java.util.Random;
import javax.swing.JOptionPane;

public class ListaDobleCircular {

    private Nodo cabeza, ultimo;

    public ListaDobleCircular() {
        this.cabeza = null;
        this.ultimo = null;
    }

    public boolean esVacio() {
        return (cabeza == null);
    }

    private String agregarFecha() {

        // Obtener mes por parte del usuario
        String meses[]
                = {"Enero", "Febrero", "Marzo", "Abril",
                    "Mayo", "Junio", "Julio", "Agosto",
                    "Septiembre", "Octubre", "Noviembre", "Diciembre"};

        Object objetoMes = JOptionPane.showInputDialog(null, "Seleccione el mes en el que se realiza: ",
                "SELECCIONAR MES", JOptionPane.QUESTION_MESSAGE, null,
                meses, "Enero");

        String mes = objetoMes.toString();

        // Obtener dia del mes
        int diasMes = obtenerDiasEnElMes(mes);
        Object listaDiasMes[] = generarListaDiasEnMes(diasMes);

        Object objetoNumeroDia = JOptionPane.showInputDialog(null, "Seleccione el día en el que se realiza: ",
                "SELECCIONAR DIA", JOptionPane.QUESTION_MESSAGE, null,
                listaDiasMes, listaDiasMes[0]);
        String numeroDia = objetoNumeroDia.toString();

        String annio = "2023";

        String fecha = numeroDia + " de " + mes + " del " + annio;

        return fecha;
    }

    private int obtenerDiasEnElMes(String nombreMes) {
        switch (nombreMes) {
            case "Enero":
            case "Marzo":
            case "Mayo":
            case "Julio":
            case "Agosto":
            case "Octubre":
            case "Diciembre":
                return 31;
            case "Abril":
            case "Junio":
            case "Septiembre":
            case "Noviembre":
                return 30;
            case "Febrero":
                return 28;
            default:
                return 0;
        }
    }

    private Object[] generarListaDiasEnMes(int cantidadDias) {
        int dia = 1;
        Object listaDiasMes[] = new Object[cantidadDias];
        for (int i = 0; i < cantidadDias; i++) {
            listaDiasMes[i] = dia;
            dia++;
        }
        return listaDiasMes;
    }

    private int generarId() {

        Random random = new Random();
        int id = 0;

        if (esVacio()) {
            id = random.nextInt(101);
        } else {
            do {
                id = random.nextInt(101);
            } while (existeId(id));
        }
        return id;
    }

    private boolean existeId(int id) {
        boolean existe = false;
        Nodo auxiliar = cabeza;
        do {

            if (auxiliar.getProyecto().getId() != id) {
                auxiliar = auxiliar.getSiguiente();
            } else {
                existe = true;
            }

        } while (auxiliar != cabeza);

        return existe;
    }

    private void insertarDatosProyecto(Proyecto proyecto) {

        String nombre;
        do {
            nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del proyecto: ", "NOMBRE DE PROYECTO", JOptionPane.INFORMATION_MESSAGE);
        } while (nombre == null || nombre.equals(""));

        String descripcion;
        do {
            descripcion = JOptionPane.showInputDialog(null, "Ingrese una descripción breve del proyecto: ", "DESCRIPCION BREVE", JOptionPane.INFORMATION_MESSAGE);
        } while (descripcion == null || descripcion.equals(""));

        String fechaInicio = agregarFecha();
        
        String duracionEstimada;
        do {
            duracionEstimada = JOptionPane.showInputDialog(null, "Ingrese la duración estimada en días", "DURACIÓN ESTIMADA", JOptionPane.INFORMATION_MESSAGE);
        } while (duracionEstimada == null || duracionEstimada.equals(""));

        int id = generarId();

        proyecto.setNombre(nombre);
        proyecto.setId(id);
        proyecto.setDescripcion(descripcion);
        proyecto.setDuracionEstimada(duracionEstimada);
        proyecto.setFechaInicio(fechaInicio);

    }

    public void insertar() {

        Proyecto proyecto = new Proyecto();
        insertarDatosProyecto(proyecto);
        Nodo nuevo = new Nodo(proyecto);

        // Si es vacio lo ingresamos en el primero
        if (esVacio()) {

            cabeza = nuevo;
            ultimo = cabeza;
            cabeza.setSiguiente(ultimo);
            cabeza.setAnterior(ultimo);
            ultimo.setSiguiente(cabeza);
            ultimo.setAnterior(cabeza);

        } else if (cabeza.getProyecto().getId() > nuevo.getProyecto().getId()) {
            nuevo.setSiguiente(cabeza);
            cabeza.setAnterior(nuevo);
            cabeza = nuevo;
            cabeza.setAnterior(ultimo);
            ultimo.setSiguiente(cabeza);

        } else if (ultimo.getProyecto().getId() < nuevo.getProyecto().getId()) {
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = ultimo.getSiguiente();
            ultimo.setSiguiente(cabeza);
            cabeza.setAnterior(ultimo);
        } else {

            Nodo auxiliar = cabeza;
            while (auxiliar.getSiguiente().getProyecto().getId() < nuevo.getProyecto().getId()) {
                auxiliar = auxiliar.getSiguiente();
            }

            nuevo.setSiguiente(auxiliar.getSiguiente());
            auxiliar.getSiguiente().setAnterior(nuevo);

            nuevo.setAnterior(auxiliar);
            auxiliar.setSiguiente(nuevo);

        }

        JOptionPane.showMessageDialog(null, "Ingresado correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);

    }

    public void eliminar(int id) {

        if (esVacio()) {
            JOptionPane.showMessageDialog(null, "Error: No hay elementos por extraer", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {

            if (cabeza.getProyecto().getId() == id) {
                if (cabeza.getSiguiente() == cabeza) {
                    cabeza = null;
                    JOptionPane.showMessageDialog(null, "Extraido correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    cabeza = cabeza.getSiguiente();
                    cabeza.setAnterior(ultimo);
                    ultimo.setSiguiente(cabeza);
                    JOptionPane.showMessageDialog(null, "Extraido correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {

                Nodo actual = cabeza.getSiguiente();
                Boolean encontrado = false;
                while (actual != cabeza) {

                    if (!(actual.getProyecto().getId() == id)) {
                        actual = actual.getSiguiente();
                    } else {
                        encontrado = true;
                        break;
                    }

                }

                if (encontrado == false) {
                    JOptionPane.showMessageDialog(null, "Error: No se encontró el elemento", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    actual.getAnterior().setSiguiente(actual.getSiguiente());
                    actual.getSiguiente().setAnterior(actual.getAnterior());
                    JOptionPane.showMessageDialog(null, "Extraido correctamente", "EXITO", JOptionPane.INFORMATION_MESSAGE);
                }

            }

        }

    }

    private Nodo confirmarEliminacion(Nodo navegador) {

        String opciones[]
                = {"Sí", "No"};
        int confirmar = JOptionPane.showOptionDialog(null,
                "Se eliminará el proyecto '" + navegador.getProyecto().getNombre() + "'"
                + "\n¿Seguro que desea continuar?",
                "BORRAR PROYECTO",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]);

        switch (confirmar) {
            case 0:
                eliminar(navegador.getProyecto().getId());
                navegador = navegador.getSiguiente();
                break;
            case 1:
                return navegador;
            default:
                JOptionPane.showMessageDialog(null,
                        "Error: Opcion incorrecta",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
        }
        return navegador;
    }

    // Agregar el de modificar
    public void navegarProyectos() {

        if (esVacio()) {
            JOptionPane.showMessageDialog(null, "No hay proyectos por mostrar", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
        } else {

            Nodo navegador = cabeza;

            do {

                if (esVacio()) {
                    JOptionPane.showMessageDialog(null, "Se ha eliminado el último proyecto", "ATENCIÓN", JOptionPane.WARNING_MESSAGE);
                    return;
                } else {

                    Object[] opciones = {"Siguiente", "Anterior", "Ingresar", "Borrar", "Salir"};
                    int opcionSeleccionada = JOptionPane.showOptionDialog(null,
                            navegador.getProyecto().toString()
                            + "\n\nSeleccione una opción:",
                            "NAVEGADOR PROYECTOS",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            opciones,
                            opciones[0]);

                    switch (opcionSeleccionada) {
                        case 0:
                            navegador = navegador.getSiguiente();
                            break;
                        case 1:
                            navegador = navegador.getAnterior();
                            break;
                        case 2:
                            navegador.getProyecto().ingresar();
                            break;
                        case 3:
                            navegador = confirmarEliminacion(navegador);
                            break;
                        case 4:
                            return;
                        default:
                            return;
                    }

                }

            } while (true);
        }
    }

}
