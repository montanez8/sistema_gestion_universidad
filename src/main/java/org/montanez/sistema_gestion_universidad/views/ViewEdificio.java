package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Edificio;

public class ViewEdificio extends ViewMain {
    public static void startMenu() throws ExepcionesNullExeption {
        int op = 0;
        do {

            op = mostrarMenu();
            switch (op) {
                case 1 -> crearEdificio();
                case 2 -> listarEdificio();
                case 3 -> buscarEdificio();
                case 4 -> modificarEdificio();
                case 5 -> eliminarEdificio();
                default -> System.out.println("Opcion no valida");
            }

        } while (op >= 1 && op < 6);
    }

    private static void eliminarEdificio() {
        System.out.println("Eliminar Edificio");
        listarEdificio();
        System.out.println("Ingrese el id del edificio");
        long id = leer.nextLong();
        Edificio edificio = serviceEdificio.edificio_id(id);
        if (edificio != null) {
            System.out.println("¿Está seguro de que desea eliminar el edificio? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                serviceEdificio.eliminar(edificio);
                System.out.println("Edificio eliminado");
            } else {
                System.out.println("Eliminación cancelada");
            }
        } else {
            System.out.println("Edificio no encontrado");
        }

    }

    private static void modificarEdificio() {
        System.out.println("Modificar Edificio");
        listarEdificio();
        System.out.println("Ingrese el id del edificio");
        long id = leer.nextLong();
        Edificio edificio = serviceEdificio.edificio_id(id);
        if (edificio != null) {
            System.out.println("¿Desea modificar el nombre del edificio? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo nombre del edificio");
                String nombre = leer.next();
                edificio.setNombre(nombre);
                serviceEdificio.editar(edificio);
                if (edificio != null) {
                    System.out.println("Edificio modificado");
                } else {
                    System.out.println("Edificio no modificado Error.");
                }
            } else {
                System.out.println("Modificación cancelada");
            }
        } else {
            System.out.println("Edificio no encontrado");
        }
    }

    private static void buscarEdificio() {

    }

    private static void listarEdificio() {
        System.out.println("Listar Edificio");
        serviceEdificio.listar().forEach(System.out::println);

    }

    private static void crearEdificio() {
        System.out.println("Crear Edificio");
        System.out.println("Ingrese el nombre del edificio");
        String nombre = leer.next();
        Edificio edificio = new Edificio(nombre);
        serviceEdificio.crear(edificio);
        if (edificio != null) {
            System.out.println("Edificio creado");
        } else {
            System.out.println("Edificio no creado");
        }
    }


    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Salon----\033[0m");
        System.out.println("\033[1;32m1. Crear Edificio.\033[0m");
        System.out.println("\033[1;32m2. Listar Edificio.\033[0m");
        System.out.println("\033[1;32m3. Buscar Edificio.\033[0m");
        System.out.println("\033[1;32m4. Modificar Edificio.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Edificio.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }
}
