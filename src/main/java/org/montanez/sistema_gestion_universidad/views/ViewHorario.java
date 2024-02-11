package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;

public class ViewHorario extends ViewMain {
    public static void startMenu() throws ExepcionesNullExeption {
        int op = 0;
        do {

            op = mostrarMenu();
            switch (op) {
                case 1 -> crearHorario();
                case 2 -> listarHorario();
                case 3 -> buscarHorario();
                case 4 -> modificarHorario();
                case 5 -> eliminarHorario();
                default -> System.out.println("Opcion no valida");
            }

        } while (op >= 1 && op < 6);
    }

    private static void eliminarHorario() {
        //todo
    }

    private static void modificarHorario() {
        //todo
    }

    private static void buscarHorario() {
        //todo
    }

    private static void listarHorario() {
        //todo
    }

    private static void crearHorario() {
        //todo

    }


    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Horario----\033[0m");
        System.out.println("\033[1;32m1. Crear Horario.\033[0m");
        System.out.println("\033[1;32m2. Listar Horario.\033[0m");
        System.out.println("\033[1;32m3. Buscar Horario.\033[0m");
        System.out.println("\033[1;32m4. Modificar Horario.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Horario.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }
}
