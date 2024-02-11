package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;

public class ViewSalon extends ViewMain {
    public static void startMenu() throws ExepcionesNullExeption {
        int op = 0;
        do {

            op = mostrarMenu();
            switch (op) {
                case 1 -> crearSalon();
                case 2 -> listarSalon();
                case 3 -> buscarSalon();
                case 4 -> modificarSalon();
                case 5 -> eliminarSalon();
                default -> System.out.println("Opcion no valida");
            }

        } while (op >= 1 && op < 6);
    }

    private static void eliminarSalon() {
        //todo
    }

    private static void modificarSalon() {
        //todo
    }

    private static void buscarSalon() {
        //todo
    }

    private static void listarSalon() {
        //todo
    }

    private static void crearSalon() {
        //todo

    }


    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Salon----\033[0m");
        System.out.println("\033[1;32m1. Crear Salon.\033[0m");
        System.out.println("\033[1;32m2. Listar Salon.\033[0m");
        System.out.println("\033[1;32m3. Buscar Salon.\033[0m");
        System.out.println("\033[1;32m4. Modificar Salon.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Salon.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }
}
