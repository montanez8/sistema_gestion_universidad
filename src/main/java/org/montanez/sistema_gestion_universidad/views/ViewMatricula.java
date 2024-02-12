package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.*;

public class ViewMatricula extends ViewMain {

    public static void startMenu() throws ExepcionesNullExeption {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1 -> crearMatricula();
                case 2 -> listarMatricula();
                case 3 -> buscarMatricula();
                case 4 -> modificarMatricula();
                case 5 -> eliminarMatricula();


                default -> System.out.println("Opcion no valida");
            }
        } while (op >= 1 && op <= 5);
    }

    private static void eliminarMatricula() {
        System.out.println("Eliminar Matricula");
        listarMatricula();
        System.out.println("Ingrese el id de la matricula");
        long id = leer.nextLong();
        Matricula matricula = serviceMatricula.matricula_id(id);
        if (matricula != null) {
            System.out.println("¿Está seguro de que desea eliminar esta matricula? (S/N)");
            String respuesta = leer.next();
            if (respuesta.equalsIgnoreCase("S")) {
                serviceMatricula.eliminar(id);
                System.out.println("Matricula eliminada exitosamente");
            }
        } else {
            System.out.println("Matricula no encontrada");
        }
    }

    private static void modificarMatricula() throws ExepcionesNullExeption {
        System.out.println("Modificar Matricula");
        listarMatricula();
        System.out.println("Ingrese el id de la matricula");
        long id = leer.nextLong();
        Matricula matricula = serviceMatricula.matricula_id(id);
        if (matricula != null) {
            System.out.println("¿Desea modificar el alumno? (S/N)");
            if (leer.next().equalsIgnoreCase("S")) {
                System.out.println("Seleccione el alumno:");
                serviceAlumno.listar().forEach(System.out::println);
                long idAlumno = leer.nextLong();
                Alumno alumno = serviceAlumno.alumno_id(idAlumno);
                matricula.setAlumno(alumno);
            }

            System.out.println("¿Desea modificar la asignatura? (S/N)");
            if (leer.next().equalsIgnoreCase("S")) {
                System.out.println("Seleccione la asignatura:");
                serviceAsignatura.listar().forEach(System.out::println);
                long idAsignatura = leer.nextLong();
                Asignatura asignatura = serviceAsignatura.asignatura_id(idAsignatura);
                matricula.setAsignatura(asignatura);
            }

            System.out.println("¿Desea modificar el periodo? (S/N)");
            if (leer.next().equalsIgnoreCase("S")) {
                System.out.println("Seleccione el periodo:");
                servicePeriodo.listar().forEach(System.out::println);
                long idPeriodo = leer.nextLong();
                Periodo periodo = servicePeriodo.buscarId(idPeriodo);
                matricula.setPeriodo(periodo);
            }

            System.out.println("¿Desea modificar el salon? (S/N)");
            if (leer.next().equalsIgnoreCase("S")) {
                System.out.println("Seleccione el salon:");
                serviceSalon.listar().forEach(System.out::println);
                long idSalon = leer.nextLong();
                Salon salon = serviceSalon.salon_id(idSalon);
                matricula.setSalon(salon);
            }

            System.out.println("¿Desea modificar el horario? (S/N)");
            if (leer.next().equalsIgnoreCase("S")) {
                System.out.println("Seleccione el horario:");
                serviceHorario.listar().forEach(System.out::println);
                long idHorario = leer.nextLong();
                Horario horario = serviceHorario.horario_id(idHorario);
                matricula.setHorario(horario);
            }

            serviceMatricula.editar(matricula);
            System.out.println("Matricula modificada exitosamente");
        } else {
            System.out.println("Matricula no encontrada");
        }
    }

    private static void buscarMatricula() {
        System.out.println("Buscar Matricula");
        System.out.println("Ingrese el id de la matricula");
        long id = leer.nextLong();
        Matricula matricula = serviceMatricula.matricula_id(id);
        if (matricula != null) {
            System.out.println(matricula);
        } else {
            System.out.println("Matricula no encontrada");
        }
    }

    private static void listarMatricula() {
        serviceMatricula.listar().forEach(System.out::println);
    }

    private static void crearMatricula() throws ExepcionesNullExeption {
        System.out.println("Crear Matricula");

        System.out.println("Seleccione el alumno:");
        serviceAlumno.listar().forEach(System.out::println);
        long idAlumno = leer.nextLong();
        Alumno alumno = serviceAlumno.alumno_id(idAlumno);

        System.out.println("Seleccione la asignatura:");
        serviceAsignatura.listar().forEach(System.out::println);
        long idAsignatura = leer.nextLong();
        Asignatura asignatura = serviceAsignatura.asignatura_id(idAsignatura);

        System.out.println("Seleccione el periodo:");
        servicePeriodo.listar().forEach(System.out::println);
        long idPeriodo = leer.nextLong();
        Periodo periodo = servicePeriodo.buscarId(idPeriodo);

        System.out.println("Seleccione el salon:");
        serviceSalon.listar().forEach(System.out::println);
        long idSalon = leer.nextLong();
        Salon salon = serviceSalon.salon_id(idSalon);

        System.out.println("Seleccione el horario:");
        serviceHorario.listar().forEach(System.out::println);
        long idHorario = leer.nextLong();
        Horario horario = serviceHorario.horario_id(idHorario);
        Matricula matricula = new Matricula(alumno, asignatura, periodo, salon, horario);
        serviceMatricula.crear(matricula);
        if (matricula != null) {
            System.out.println("Matricula creada exitosamente.");
        } else {
            System.out.println("Error al crear la matricula.");
        }
    }


    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Matricula----\033[0m");
        System.out.println("\033[1;32m1. Crear Matricula.\033[0m");
        System.out.println("\033[1;32m2. Listar Matricula.\033[0m");
        System.out.println("\033[1;32m3. Buscar Matricula.\033[0m");
        System.out.println("\033[1;32m4. Modificar Matricula.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Matricula.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }


}
