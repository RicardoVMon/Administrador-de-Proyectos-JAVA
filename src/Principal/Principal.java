package Principal;

import ListaDoblementeCircular.ListaDobleCircular;
import javax.swing.JOptionPane;

// Creado por Ricardo Vargas Montero

public class Principal {

    public static void main(String[] args) {

        ListaDobleCircular listaProyectos = new ListaDobleCircular();

        while (true) {

            try {
                String opcion = JOptionPane.showInputDialog(null,
                        "\n[1] - Crear Nuevo Proyecto"
                        + "\n[2] - Navegar Proyectos"
                        + "\n[3] - Salir del Menú"
                        + "\n\nIngrese la opción de su preferencia: ",
                        "MENU PRINCIPAL",
                        JOptionPane.PLAIN_MESSAGE);

                switch (opcion) {
                    case "1":
                        listaProyectos.insertar();
                        break;
                    case "2":
                        listaProyectos.navegarProyectos();
                        break;
                    case "3":
                        System.exit(0);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,
                                "Error: Ingrese una opción valida",
                                "ERROR",
                                JOptionPane.ERROR_MESSAGE);
                }
            } catch (NullPointerException e) {
            }

        }
    }

}
