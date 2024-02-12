package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Alumno;
import org.montanez.sistema_gestion_universidad.repository.models.TipoDocumento;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.function.Function;

public class ViewAlumno extends ViewMain {
    public static void startMenu() throws ExepcionesNullExeption {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1 -> crearAlumno();
                case 2 -> listarAlumnos();
                case 3 -> buscarAlumno();
                case 4 -> modificarAlumno();
                case 5 -> eliminarAlumno();
                default -> System.out.println("Opcion no valida");
            }
        } while (op >= 1 && op <= 5);
    }

    private static void eliminarAlumno() throws ExepcionesNullExeption {
        System.out.println("Eliminar Alumno");
        listarAlumnos();
        Alumno alumno = new Alumno();
        System.out.println("Ingrese el id del alumno");
        long id = leer.nextLong();
        if (serviceAlumno.alumno_id(id) != null) {
            System.out.println("¿Está seguro de que desea eliminar al alumno? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                serviceAlumno.eliminar(id);
                System.out.println("Alumno eliminado");
            } else {
                System.out.println("Eliminación cancelada");
            }
        } else {
            System.out.println("Alumno no encontrado");
        }
    }

    private static void crearAlumno() {
        leer.nextLine();
        System.out.println("Seleccione el tipo de documento:");
        int i = 1;
        for (TipoDocumento tipo : TipoDocumento.values()) {
            System.out.println(i++ + ". " + tipo);
        }
        int seleccion = leer.nextInt();
        TipoDocumento tipoDocumento = TipoDocumento.values()[seleccion - 1];

        System.out.println("Numero de documento");
        String numeroDocumento = leer.next();
        System.out.println("Primer nombre");
        String nombre = leer.next();
        System.out.println("Primer apellido");
        String apellido = leer.next();
        System.out.println("Ciudad");
        String ciudad = leer.next();
        System.out.println("Direccion");
        String direccion = leer.next();
        System.out.println("Telefono");
        String telefono = leer.next();

        System.out.println("Fecha de nacimiento (dd-MM-yyyy)");
        LocalDate fechaNacimiento = null;
        while (fechaNacimiento == null) {
            String fechaNacimientoStr = leer.next();
            try {
                fechaNacimiento = LocalDate.parse(fechaNacimientoStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha incorrecto, por favor intente de nuevo.");
            }
        }
        Date fechaNacimientoDate = Date.valueOf(fechaNacimiento);

        System.out.println("Genero");
        System.out.println("1. Masculino");
        System.out.println("2. Femenino");
        System.out.println("3. Otro");
        int seleccionGenero = leer.nextInt();
        String genero = "";
        switch (seleccionGenero) {
            case 1:
                genero = "M";
                break;
            case 2:
                genero = "F";
                break;
            case 3:
                genero = "O";
                break;
            default:
                System.out.println("Opcion no valida");
                break;
        }

        Alumno alumno = new Alumno(tipoDocumento, numeroDocumento, nombre, apellido, ciudad, direccion, telefono, fechaNacimientoDate, genero);
        serviceAlumno.crear(alumno);
    }

    private static void modificarAlumno() throws ExepcionesNullExeption {
        listarAlumnos();
        System.out.println("Ingrese el ID del alumno que desea modificar:");
        long id = leer.nextLong();
        Alumno alumno = serviceAlumno.alumno_id(id);
        if (alumno != null) {
            leer.nextLine();
            alumno.setTipoDocumento(obtenerNuevoValor("tipo de documento", alumno.getTipoDocumento()));
            alumno.setNumeroDocumento(obtenerNuevoValor("numero de documento", alumno.getNumeroDocumento(), Function.identity()));
            alumno.setNombre(obtenerNuevoValor("nombre", alumno.getNombre(), Function.identity()));
            alumno.setApellido(obtenerNuevoValor("apellido", alumno.getApellido(), Function.identity()));
            alumno.setCiudad(obtenerNuevoValor("ciudad", alumno.getCiudad(), Function.identity()));
            alumno.setDireccion(obtenerNuevoValor("direccion", alumno.getDireccion(), Function.identity()));
            alumno.setTelefono(obtenerNuevoValor("telefono", alumno.getTelefono(), Function.identity()));

            System.out.println("Fecha de nacimiento (dd-MM-yyyy)");
            LocalDate fechaNacimiento = null;
            while (fechaNacimiento == null) {
                String fechaNacimientoStr = leer.next();
                try {
                    fechaNacimiento = LocalDate.parse(fechaNacimientoStr, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
                } catch (DateTimeParseException e) {
                    System.out.println("Formato de fecha incorrecto, por favor intente de nuevo.");
                }
            }
            Date fechaNacimientoDate = Date.valueOf(fechaNacimiento);
            alumno.setFechaNacimiento(fechaNacimientoDate);

            System.out.println("Genero");
            System.out.println("1. Masculino");
            System.out.println("2. Femenino");
            System.out.println("3. Otro");
            int seleccionGenero = leer.nextInt();
            String genero = "";
            switch (seleccionGenero) {
                case 1:
                    genero = "M";
                    break;
                case 2:
                    genero = "F";
                    break;
                case 3:
                    genero = "O";
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
            alumno.setGenero(genero);

            serviceAlumno.editar(alumno);
            System.out.println("Alumno modificado exitosamente.");
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    private static String obtenerNuevoValor(String campo, String valorAntiguo, Function<String, String> converter) {
        System.out.println("Desea modificar  " + campo + "? (s/n)");
        if (leer.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nuevo " + campo + ":");
            return converter.apply(leer.nextLine());
        }
        return valorAntiguo;
    }

    private static TipoDocumento obtenerNuevoValor(String campo, TipoDocumento valorAntiguo) {
        System.out.println("Desea modificar " + campo + "? (s/n)");
        if (leer.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Seleccione el nuevo " + campo + ":");
            int i = 1;
            for (TipoDocumento tipo : TipoDocumento.values()) {
                System.out.println(i++ + ". " + tipo);
            }
            int seleccion = leer.nextInt();
            return TipoDocumento.values()[seleccion - 1];
        }
        return valorAntiguo;
    }

    private static void buscarAlumno() throws ExepcionesNullExeption {
        System.out.println("Buscar Alumno");
        System.out.println("Ingrese el id del alumno");
        long id = leer.nextLong();
        System.out.println(serviceAlumno.alumno_id(id));

    }

    private static void listarAlumnos() {
        System.out.println("Listar Alumnos");
        serviceAlumno.listar().forEach(System.out::println);

    }


    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Alumno----\033[0m");
        System.out.println("\033[1;32m1. Crear Alumno.\033[0m");
        System.out.println("\033[1;32m2. Listar Alumno.\033[0m");
        System.out.println("\033[1;32m3. Buscar Alumno.\033[0m");
        System.out.println("\033[1;32m4. Modificar Alumno.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Alumno.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }
}
