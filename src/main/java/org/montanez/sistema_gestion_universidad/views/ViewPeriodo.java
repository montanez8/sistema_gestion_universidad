package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;

public class ViewPeriodo extends ViewMain {
    public static void startMenu() throws ExepcionesNullExeption {
        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1 -> crearPeriodo();
                case 2 -> listarPeriodo();
                case 3 -> buscarPeriodo();
                case 4 -> modificarPeriodo();
                case 5 -> eliminarPeriodo();
                default -> System.out.println("Opcion no valida");
            }

        } while (op >= 1 && op < 6);
    }

    private static void eliminarPeriodo() {
        //todo
    }

    private static void modificarPeriodo() {
        //todo
    }

    private static void buscarPeriodo() {
        //todo
    }

    private static void listarPeriodo() {
        //todo
    }

    private static void crearPeriodo() {
        //todo

    }


    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Periodo----\033[0m");
        System.out.println("\033[1;32m1. Crear Periodo.\033[0m");
        System.out.println("\033[1;32m2. Listar Periodo.\033[0m");
        System.out.println("\033[1;32m3. Buscar Periodo.\033[0m");
        System.out.println("\033[1;32m4. Modificar Periodo.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Periodo.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }
}
