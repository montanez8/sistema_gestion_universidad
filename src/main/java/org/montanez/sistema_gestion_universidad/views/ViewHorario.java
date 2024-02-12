package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Horario;

import java.time.DayOfWeek;
import java.time.LocalTime;

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

        } while (op >= 1 && op <= 5);
    }

    private static void eliminarHorario() {
        System.out.println("Eliminar Horario");
        listarHorario();
        System.out.println("Ingrese el id del horario");
        long id = leer.nextLong();
        Horario horario = serviceHorario.horario_id(id);
        if (horario != null) {
            System.out.println("¿Está seguro de que desea eliminar el horario? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                serviceHorario.eliminar(id);
                System.out.println("Horario eliminado");
            } else {
                System.out.println("Eliminación cancelada");
            }
        } else {
            System.out.println("Horario no encontrado");
        }
    }

    private static void modificarHorario() {
        System.out.println("Modificar Horario");
        listarHorario();
        System.out.println("Ingrese el id del horario");
        long id = leer.nextLong();
        Horario horario = serviceHorario.horario_id(id);
        if (horario != null) {
            System.out.println("¿Desea modificar el dia de la semana? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese el dia de la semana (1=Lunes, 2=Martes, ..., 7=Domingo)");
                int dia = leer.nextInt();
                horario.setDia(DayOfWeek.of(dia));
            }
            System.out.println("¿Desea modificar la hora de inicio? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la hora de inicio en formato de 24 horas");
                System.out.println("Hora:");
                int horaInicioHora = leer.nextInt();
                System.out.println("Minutos:");
                int horaInicioMinuto = leer.nextInt();
                LocalTime horaInicio = LocalTime.of(horaInicioHora, horaInicioMinuto);
                horario.setHoraInicio(horaInicio);
            }
            System.out.println("¿Desea modificar la hora de fin? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la hora de fin en formato de 24 horas");
                System.out.println("Hora:");
                int horaFinHora = leer.nextInt();
                System.out.println("Minutos:");
                int horaFinMinuto = leer.nextInt();
                LocalTime horaFin = LocalTime.of(horaFinHora, horaFinMinuto);
                horario.setHoraFin(horaFin);
            }
            serviceHorario.editar(horario);
            System.out.println("Horario modificado");
        } else {
            System.out.println("Horario no encontrado");
        }
    }

    private static void buscarHorario() {
        System.out.println("Buscar Horario");
        System.out.println("Ingrese el id del horario");
        long id = leer.nextLong();
        Horario horario = serviceHorario.horario_id(id);
        if (horario != null) {
            System.out.println(horario);
        } else {
            System.out.println("Horario no encontrado");
        }
    }

    private static void listarHorario() {
        System.out.println("Listar Horario");
        serviceHorario.listar().forEach(System.out::println);
    }

    private static void crearHorario() {
        System.out.println("Crear Horario");
        System.out.println("Ingrese el dia de la semana (1=Lunes, 2=Martes, ..., 7=Domingo)");
        int dia = leer.nextInt();

        System.out.println("Ingrese la hora de inicio en formato de 24 horas");
        System.out.println("Hora:");
        int horaInicioHora = leer.nextInt();
        System.out.println("Minutos:");
        int horaInicioMinuto = leer.nextInt();
        LocalTime horaInicio = LocalTime.of(horaInicioHora, horaInicioMinuto);

        System.out.println("Ingrese la hora de fin en formato de 24 horas");
        System.out.println("Hora:");
        int horaFinHora = leer.nextInt();
        System.out.println("Minutos:");
        int horaFinMinuto = leer.nextInt();
        LocalTime horaFin = LocalTime.of(horaFinHora, horaFinMinuto);

        Horario horario = new Horario(DayOfWeek.of(dia), horaInicio, horaFin);
        serviceHorario.crear(horario);
        if (serviceHorario.horario_id(horario.getId()) != null) {
            System.out.println("Horario creado");
        } else {
            System.out.println("Horario no creado");
        }
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
