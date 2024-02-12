package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Edificio;
import org.montanez.sistema_gestion_universidad.repository.models.Salon;

import java.util.List;

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

        } while (op >= 1 && op <= 5);
    }

    private static void eliminarSalon() {
        System.out.println("Eliminar Salon");
        listarSalon();
        System.out.println("Ingrese el id del salon");
        long id = leer.nextLong();
        Salon salon = serviceSalon.salon_id(id);
        if (salon != null) {
            System.out.println("¿Está seguro de que desea eliminar el salon? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                serviceSalon.eliminar(salon);
                System.out.println("Salon eliminado");
            } else {
                System.out.println("Eliminación cancelada");
            }
        } else {
            System.out.println("Salon no encontrado");
        }
    }

    private static void modificarSalon() {
        System.out.println("Modificar Salon");
        listarSalon();
        System.out.println("Ingrese el id del salon");
        long id = leer.nextLong();
        Salon salon = serviceSalon.salon_id(id);
        if (salon != null) {
            System.out.println("¿Desea modificar la capacidad? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Capacidad");
                int capacidad = leer.nextInt();
                salon.setCapacidad(capacidad);
            }
            System.out.println("¿Desea modificar el piso? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Piso");
                int piso = leer.nextInt();
                salon.setPiso(piso);
            }
            System.out.println("¿Desea modificar el numero? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Numero");
                String numero = leer.next();
                salon.setNumero(numero);
            }
            System.out.println("¿Desea modificar el edificio? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Edificio");
                listarEdificios();
                long idEdificio = leer.nextLong();
                Edificio edificio = serviceEdificio.edificio_id(idEdificio);
                salon.setEdificio(edificio);
            }
            serviceSalon.editar(salon);
            if (salon.getId() != 0) {
                System.out.println("Salon modificado");
            } else {
                System.out.println("Error al modificar el salon");
            }
        } else {
            System.out.println("Salon no encontrado");
        }
    }

    private static void buscarSalon() {
        System.out.println("Buscar Salon");
        System.out.println("Ingrese el id del salon");
        long id = leer.nextLong();
        Salon salon = serviceSalon.salon_id(id);
        if (salon != null) {
            System.out.println(salon);
        } else {
            System.out.println("Salon no encontrado");
        }
    }

    private static void listarSalon() {
        for (Salon salon : serviceSalon.listar()) {
            System.out.println(salon);
        }
    }

    private static void crearSalon() {
        System.out.println("Crear Salon");
        System.out.println("Capacidad");
        int capacidad = leer.nextInt();
        System.out.println("Piso");
        int piso = leer.nextInt();
        System.out.println("Numero");
        String numero = leer.next();
        System.out.println("Edificio");
        listarEdificios();
        long idEdificio = leer.nextLong();
        Edificio edificio = serviceEdificio.edificio_id(idEdificio);
        Salon salon = new Salon(capacidad, piso, numero, edificio);
        serviceSalon.crear(salon);
        if (salon.getId() != 0) {
            System.out.println("Salon creado");
        } else {
            System.out.println("Error al crear el salon");
        }
    }


    private static void listarEdificios() {
        List<Edificio> edificios = serviceEdificio.listar();
        for (Edificio edificio : edificios) {
            System.out.println(edificio);
        }
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
