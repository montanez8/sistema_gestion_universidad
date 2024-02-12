package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Periodo;


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
        System.out.println("Modificar Periodo");
        listarPeriodo();
        System.out.println("Ingrese el id del periodo");
        long id = leer.nextLong();
        Periodo periodo = servicePeriodo.buscarId(id);
        if (periodo != null) {
            leer.nextLine();
            periodo.setAnio(obtenerValor("año del periodo", periodo.getAnio()));
            periodo.setSemestre(obtenerValor("semestre del periodo", periodo.getSemestre()));
            servicePeriodo.editar(periodo);
            System.out.println("Periodo modificado");
        } else {
            System.out.println("Periodo no encontrado");
        }

    }

    private static int obtenerValor(String añoDelPeriodo, int anio) {
        System.out.println("Desea modificar el " + añoDelPeriodo + "? (s/n)");
        String confirm = leer.next();
        if (confirm.equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nuevo " + añoDelPeriodo);
            return leer.nextInt();
        }
        return anio;
    }

    private static void buscarPeriodo() {
        System.out.println("Buscar Periodo");
        System.out.println("Ingrese el id del periodo");
        long id = leer.nextLong();
        Periodo periodo = servicePeriodo.buscarId(id);
        if (periodo != null) {
            System.out.println(periodo);
        } else {
            System.out.println("Periodo no encontrado");
        }

    }

    private static void listarPeriodo() {
        System.out.println("Listar Periodo");
        for (Periodo periodo : servicePeriodo.listar()) {
            System.out.println(periodo);
        }
    }

    private static void crearPeriodo() {
        System.out.println("Crear Periodo");
        System.out.println("Ingrese el año");
        int anio = leer.nextInt();
        System.out.println("Ingrese el semestre");
        int semestre = leer.nextInt();
        Periodo periodo = new Periodo(anio, semestre);
        servicePeriodo.crear(periodo);

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
