package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Programa;

public class ViewPrograma extends ViewMain {
    public static void startMenu() throws ExepcionesNullExeption {
        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1 -> crearPrograma();
                case 2 -> listarPrograma();
                case 3 -> buscarPrograma();
                case 4 -> modificarPrograma();
                case 5 -> eliminarPrograma();
                default -> System.out.println("Opcion no valida");
            }

        } while (op >= 1 && op < 6);
    }

    private static void eliminarPrograma() {
        System.out.println("Eliminar Programa");
        listarPrograma();
        System.out.println("Ingrese el id del programa");
        long id = leer.nextLong();
        if (servicePrograma.programa_id(id) != null) {
            System.out.println("¿Está seguro de que desea eliminar el programa? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                servicePrograma.eliminar(servicePrograma.programa_id(id));
                System.out.println("Programa eliminado");
            } else {
                System.out.println("Eliminación cancelada");
            }
        } else {
            System.out.println("Programa no encontrado");
        }
    }

    private static void modificarPrograma() {
        System.out.println("Modificar Programa");
        listarPrograma();
        System.out.println("Ingrese el id del programa");
        long id = leer.nextLong();
        Programa programa = servicePrograma.programa_id(id);
        if (programa != null) {
            leer.nextLine();
            System.out.println("Desea modificar el nombre del programa? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo nombre del programa");
                String nombre = leer.nextLine();
                programa.setNombre(nombre);
            }
            System.out.println("Desea modificar el nivel del programa? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo nivel del programa");
                String nivel = leer.nextLine();
                programa.setNivel(nivel);
            }
            servicePrograma.editar(programa);
            System.out.println("Programa modificado");
        } else {
            System.out.println("Programa no encontrado");
        }
    }

    private static void buscarPrograma() {
        System.out.println("Ingrese el id del programa");
        long id = leer.nextLong();
        Programa programa = servicePrograma.programa_id(id);
        if (programa != null) {
            System.out.println(programa);
        } else {
            System.out.println("Programa no encontrado");
        }
    }

    private static void listarPrograma() {
        System.out.println("Listar Programa");
        servicePrograma.listar().forEach(System.out::println);

    }

    private static void crearPrograma() {
        System.out.println("Crear Programa");
        System.out.println("Ingrese el nombre del programa");
        String nombre = leer.nextLine();
        System.out.println("Ingrese el nivel del programa");
        String nivel = leer.nextLine();
        Programa programa = new Programa(nombre, nivel);
        servicePrograma.crear(programa);
        System.out.println("Programa creado");

    }


    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Programa----\033[0m");
        System.out.println("\033[1;32m1. Crear Programa.\033[0m");
        System.out.println("\033[1;32m2. Listar Programa.\033[0m");
        System.out.println("\033[1;32m3. Buscar Programa.\033[0m");
        System.out.println("\033[1;32m4. Modificar Programa.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Programa.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }
}
