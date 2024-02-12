package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Periodo;
import org.montanez.sistema_gestion_universidad.repository.models.Programa;
import org.montanez.sistema_gestion_universidad.repository.models.Tarifa;

public class ViewTarifa extends ViewMain {
    public static void startMenu() throws ExepcionesNullExeption {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1 -> crearTarifa();
                case 2 -> listarTarifa();
                case 3 -> buscarTarifa();
                case 4 -> modificarTarifa();
                case 5 -> eliminarTarifa();

                default -> System.out.println("Opcion no valida");
            }
        } while (op >= 1 && op <= 5);
    }

    private static void eliminarTarifa() {
        System.out.println("Eliminar Tarifa");
        listarTarifa();
        System.out.println("Ingrese el id de la tarifa");
        long id = leer.nextLong();
        Tarifa tarifa = serviceTarifa.buscarId(id);
        if (tarifa != null) {
            System.out.println("¿Está seguro de que desea eliminar esta tarifa? (S/N)");
            String respuesta = leer.next();
            if (respuesta.equalsIgnoreCase("S")) {
                serviceTarifa.eliminar(tarifa);
                System.out.println("Tarifa eliminada exitosamente");
            }
        } else {
            System.out.println("Tarifa no encontrada");
        }
    }

    private static void modificarTarifa() {
        System.out.println("Modificar Tarifa");
        listarTarifa();
        System.out.println("Ingrese el id de la tarifa");
        long id = leer.nextLong();
        Tarifa tarifa = serviceTarifa.buscarId(id);
        if (tarifa != null) {
            System.out.println("¿Desea modificar el valor del crédito? (S/N)");
            String respuesta = leer.next();
            if (respuesta.equalsIgnoreCase("S")) {
                System.out.println("Ingrese el nuevo valor del crédito");
                double valorCredito = leer.nextDouble();
                tarifa.setCostoCredito(valorCredito);
            }
            System.out.println("¿Desea modificar el programa? (S/N)");
            respuesta = leer.next();
            if (respuesta.equalsIgnoreCase("S")) {
                servicePrograma.listar().forEach(System.out::println);
                System.out.println("Ingrese el id del nuevo programa");
                long programaId = leer.nextLong();
                Programa programa = servicePrograma.programa_id(programaId);
                tarifa.setPrograma(programa);
            }
            System.out.println("¿Desea modificar el periodo? (S/N)");
            respuesta = leer.next();
            if (respuesta.equalsIgnoreCase("S")) {
                servicePeriodo.listar().forEach(System.out::println);
                System.out.println("Ingrese el id del nuevo periodo");
                long periodoId = leer.nextLong();
                Periodo periodo = servicePeriodo.buscarId(periodoId);
                tarifa.setPeriodo(periodo);
            }
            serviceTarifa.editar(tarifa);
            if (serviceTarifa.buscarId(tarifa.getId()) != null) {
                System.out.println("Tarifa modificada exitosamente");
            } else {
                System.out.println("Tarifa no modificada. Error");
            }
        } else {
            System.out.println("Tarifa no encontrada");
        }
    }

    private static void buscarTarifa() {
        System.out.println("Buscar Tarifa");
        System.out.println("Ingrese el id de la tarifa");
        long id = leer.nextLong();
        Tarifa tarifa = serviceTarifa.buscarId(id);
        if (tarifa != null) {
            System.out.println(tarifa);
        } else {
            System.out.println("Tarifa no encontrada");
        }
    }

    private static void listarTarifa() {
        System.out.println("Listar Tarifa");
        serviceTarifa.listar().forEach(System.out::println);
    }

    private static void crearTarifa() {
        System.out.println("Crear Tarifa");
        System.out.println("Ingrese el valor del crédito");
        double valorCredito = leer.nextDouble();
        servicePrograma.listar().forEach(System.out::println);
        System.out.println("Ingrese el id del programa");
        long programaId = leer.nextLong();
        Programa programa = servicePrograma.programa_id(programaId);
        servicePeriodo.listar().forEach(System.out::println);
        System.out.println("Ingrese el id del periodo");
        long periodoId = leer.nextLong();
        Periodo periodo = servicePeriodo.buscarId(periodoId);
        Tarifa tarifa = new Tarifa(valorCredito);
        tarifa.setPrograma(programa);
        tarifa.setPeriodo(periodo);
        serviceTarifa.crear(tarifa);

        System.out.println("Tarifa creada exitosamente");

    }


    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Tarifa----\033[0m");
        System.out.println("\033[1;32m1. Crear Tarifa.\033[0m");
        System.out.println("\033[1;32m2. Listar Tarifa.\033[0m");
        System.out.println("\033[1;32m3. Buscar Tarifa.\033[0m");
        System.out.println("\033[1;32m4. Modificar Tarifa.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Tarifa.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }
}
