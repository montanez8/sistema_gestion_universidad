package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Departamento;

public class ViewDepartamento extends ViewMain {

    public static void startMenu() throws ExepcionesNullExeption {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1 -> crearDepartamento();
                case 2 -> listarDepartamento();
                case 3 -> buscarDepartamento();
                case 4 -> modificarDepartamento();
                case 5 -> eliminarDepartamento();
                default -> System.out.println("Opcion no valida");
            }

        } while (op >= 1 && op < 6);

    }

    private static void listarDepartamento() {
        System.out.println("Listar Departamentos: ");
        serviceDepartemento.listar().forEach(System.out::println);
    }

    private static void crearDepartamento() {
        leer.nextLine();
        System.out.println("Ingrese el nombre del departamento: ");
        String nombre = leer.nextLine();
        Departamento departamento = new Departamento(nombre);
        serviceDepartemento.crear(departamento);
        System.out.println("Departamento creado");
    }

    private static void modificarDepartamento() {
        listarDepartamento();
        System.out.println("Ingrese el id del departamento a modificar: ");
        long id = leer.nextLong();
        Departamento departamento = serviceDepartemento.buscarId(id);
        if (departamento != null) {
            leer.nextLine();
            System.out.println("Desea modificar el nombre de departamento? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo nombre del departamento: ");
                departamento.setNombre(leer.nextLine());
            }
            serviceDepartemento.editar(departamento);
            System.out.println("Departamento modificado");
        } else {
            System.out.println("Departamento no encontrado");
        }

    }

    private static void buscarDepartamento() {
        System.out.println("Ingrese el id del departamento a buscar: ");
        long id = leer.nextLong();
        Departamento departamento = serviceDepartemento.buscarId(id);
        if (departamento != null) {
            System.out.println(departamento);
        } else {
            System.out.println("Departamento no encontrado");
        }
    }

    private static void eliminarDepartamento() {
        listarDepartamento();
        System.out.println("Ingrese el id del departamento a eliminar: ");
        long id = leer.nextLong();
        Departamento departamento = serviceDepartemento.buscarId(id);
        if (departamento != null) {
            System.out.println("¿Está seguro de que desea eliminar el departamento? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                serviceDepartemento.eliminar(departamento);
                System.out.println("Departamento eliminado");
            } else {
                System.out.println("Eliminación cancelada");
            }
        } else {
            System.out.println("Departamento no encontrado");
        }
    }

    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Departamento----\033[0m");
        System.out.println("\033[1;32m1. Crear Departamento.\033[0m");
        System.out.println("\033[1;32m2. Listar Departamento.\033[0m");
        System.out.println("\033[1;32m3. Buscar Departamento.\033[0m");
        System.out.println("\033[1;32m4. Modificar Departamento.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Departamento.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }

}
