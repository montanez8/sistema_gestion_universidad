package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.*;

public class ViewAsignatura extends ViewMain {

    public static void startMenu() throws ExepcionesNullExeption {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1 -> crearAsignatura();
                case 2 -> listarAsignatura();
                case 3 -> buscarAsignatura();
                case 4 -> modificarAsignatura();
                case 5 -> eliminarAsignatura();
                default -> System.out.println("Opcion no valida");
            }
        } while (op >= 1 && op <= 5);
    }

    private static void modificarAsignatura() {
        System.out.println("Modificar Asignatura");
        listarAsignatura();
        System.out.println("Ingrese el id de la asignatura");
        long id = leer.nextLong();
        Asignatura asignatura = serviceAsignatura.asignatura_id(id);
        if (asignatura != null) {
            leer.nextLine();
            System.out.println("Desea modificar el nombre de la asignatura? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Nombre de la asignatura");
                String nombre = leer.nextLine();
                asignatura.setNombre(nombre);
            }
            System.out.println("Desea modificar los creditos de la asignatura? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Creditos de la asignatura");
                int creditos = leer.nextInt();
                asignatura.setCreditos(creditos);
            }
            System.out.println("Desea modificar el cupo de la asignatura? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Cupo de la asignatura");
                int cupo = leer.nextInt();
                asignatura.setCupo(cupo);
            }
            System.out.println("Desea modificar el profesor de la asignatura? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Seleccione un profesor:");
                serviceProfesor.listar().forEach(System.out::println);
                System.out.println("Ingrese el id del profesor");
                long profesor_id = leer.nextLong();
                Profesor profesor = serviceProfesor.profesor_id(profesor_id);
                asignatura.setProfesor(profesor);
            }
            System.out.println("Desea modificar el programa de la asignatura? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Seleccione un programa:");
                servicePrograma.listar().forEach(System.out::println);
                System.out.println("Ingrese el id del programa");
                long programa_id = leer.nextLong();
                Programa programa = servicePrograma.programa_id(programa_id);
                asignatura.setPrograma(programa);
            }
            System.out.println("Desea modificar el curso de la asignatura? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Seleccione un curso:");
                serviceCurso.listar().forEach(System.out::println);
                System.out.println("Ingrese el id del curso");
                long curso_id = leer.nextLong();
                Curso curso = serviceCurso.curso_id(curso_id);
                asignatura.setCurso(curso);
            }
            System.out.println("Desea modificar el periodo de la asignatura? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Seleccione un periodo:");
                servicePeriodo.listar().forEach(System.out::println);
                System.out.println("Ingrese el id del periodo");
                long periodo_id = leer.nextInt();
                Periodo periodo = servicePeriodo.buscarId(periodo_id);
                asignatura.setPeriodo(periodo);
            }
            serviceAsignatura.editar(asignatura);
            if (serviceAsignatura.asignatura_id(id) != null) {
                System.out.println("Asignatura modificada exitosamente.");
            } else {
                System.out.println("Asignatura no modificada. Error");
            }

        } else {
            System.out.println("Asignatura no encontrada");
        }
    }

    private static void crearAsignatura() {
        System.out.println("Crear Asignatura");
        leer.nextLine();
        System.out.println("Nombre de la asignatura");
        String nombre = leer.nextLine();
        System.out.println("Creditos de la asignatura");
        int creditos = leer.nextInt();
        System.out.println("Cupo de la asignatura");
        int cupo = leer.nextInt();

        System.out.println("Seleccione un profesor:");
        serviceProfesor.listar().forEach(System.out::println);
        System.out.println("Ingrese el id del profesor");
        long profesor_id = leer.nextLong();
        Profesor profesor = serviceProfesor.profesor_id(profesor_id);

        System.out.println("Seleccione un programa:");
        servicePrograma.listar().forEach(System.out::println);
        System.out.println("Ingrese el id del programa");
        long programa_id = leer.nextLong();
        Programa programa = servicePrograma.programa_id(programa_id);

        System.out.println("Seleccione un curso:");
        serviceCurso.listar().forEach(System.out::println);
        System.out.println("Ingrese el id del curso");
        long curso_id = leer.nextLong();
        Curso curso = serviceCurso.curso_id(curso_id);


        System.out.println("Seleccione un periodo:");
        servicePeriodo.listar().forEach(System.out::println);
        System.out.println("Ingrese el id del periodo");
        long periodo_id = leer.nextInt();
        Periodo periodo = servicePeriodo.buscarId(periodo_id);

        Asignatura asignatura = new Asignatura(nombre, creditos, cupo, profesor, programa, curso, periodo);
        serviceAsignatura.crear(asignatura);
        System.out.println("Asignatura creada");
    }

    private static void eliminarAsignatura() throws ExepcionesNullExeption {
        System.out.println("Eliminar Asignatura");
        listarAsignatura();
        System.out.println("Ingrese el id de la asignatura");
        long id = leer.nextLong();
        if (serviceAsignatura.asignatura_id(id) != null) {
            System.out.println("¿Está seguro de que desea eliminar la asignatura? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                serviceAsignatura.eliminar(id);
                System.out.println("Asignatura eliminada");
            } else {
                System.out.println("Eliminación cancelada");
            }
        } else {
            System.out.println("Asignatura no encontrada");
        }
    }

    private static void listarAsignatura() {
        System.out.println("Listar Asignaturas");
        serviceAsignatura.listar().forEach(System.out::println);
    }

    private static void buscarAsignatura() {
        System.out.println("Buscar Asignatura");
        System.out.println("Ingrese el id de la asignatura");
        long id = leer.nextLong();
        System.out.println(serviceAsignatura.asignatura_id(id));
    }


    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Asignatura----\033[0m");
        System.out.println("\033[1;32m1. Crear Asignatura.\033[0m");
        System.out.println("\033[1;32m2. Listar Asignatura.\033[0m");
        System.out.println("\033[1;32m3. Buscar Asignatura.\033[0m");
        System.out.println("\033[1;32m4. Modificar Asignatura.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Asignatura.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }
}
