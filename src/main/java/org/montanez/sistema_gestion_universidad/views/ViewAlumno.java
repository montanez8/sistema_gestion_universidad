package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Alumno;
import org.montanez.sistema_gestion_universidad.repository.models.TipoDocumento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        listarAlumnosResumido();
        Alumno alumno = new Alumno();
        System.out.println("Ingrese el id del alumno");
        long id = leer.nextLong();
        if (serviceAlumno.alumno_id(id) != null) {
            serviceAlumno.eliminar(id);
            System.out.println("Alumno eliminado");
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
        String fechaNacimientoStr = leer.next();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = formatter.parse(fechaNacimientoStr);
        } catch (ParseException e) {
            System.out.println("Formato de fecha incorrecto");
        }

        System.out.println("Genero");
        String genero = leer.next();

        Alumno alumno = new Alumno(tipoDocumento, numeroDocumento, nombre, apellido, ciudad, direccion, telefono, fechaNacimiento, genero);
        serviceAlumno.crear(alumno);
    }

    private static void modificarAlumno() throws ExepcionesNullExeption {
        listarAlumnosResumido();
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
            alumno.setFechaNacimiento(obtenerNuevoValor("fecha de nacimiento(dd-MM-yyyy) ", alumno.getFechaNacimiento(), str -> {
                try {
                    return new SimpleDateFormat("dd-MM-yyyy").parse(str);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
            }));
            alumno.setGenero(obtenerNuevoValor("genero", alumno.getGenero(), Function.identity()));
            serviceAlumno.editar(alumno);
            System.out.println("Alumno modificado exitosamente.");
        } else {
            System.out.println("Alumno no encontrado.");
        }
    }

    private static String obtenerNuevoValor(String campo, String valorAntiguo, Function<String, String> converter) {
        System.out.println("Desea modificar el " + campo + "? (s/n)");
        if (leer.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nuevo " + campo + ":");
            return converter.apply(leer.nextLine());
        }
        return valorAntiguo;
    }

    private static TipoDocumento obtenerNuevoValor(String campo, TipoDocumento valorAntiguo) {
        System.out.println("Desea modificar el " + campo + "? (s/n)");
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

    private static <T> T obtenerNuevoValor(String field, T ValorAntiguo, Function<String, T> convertir) {
        System.out.println("Desea modificar el " + field + "? (s/n)");
        if (leer.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nuevo " + field + ":");
            return convertir.apply(leer.nextLine());
        }

        return ValorAntiguo;
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

    private static void listarAlumnosResumido() {
        System.out.println("Listado de Alumnos:");
        serviceAlumno.listar().forEach(alumno -> System.out.println("ID: " + alumno.getId() + ", Numero de Documento: " + alumno.getNumeroDocumento() + ", Nombre: " + alumno.getNombre()
                + " " + alumno.getApellido()));
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
