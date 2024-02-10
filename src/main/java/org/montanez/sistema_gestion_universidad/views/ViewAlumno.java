package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Alumno;
import org.montanez.sistema_gestion_universidad.repository.models.TipoDocumento;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ViewAlumno extends ViewMain {
    public static void startMenu() throws ExepcionesNullExeption {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    crearAlumno();
                    break;
                case 2:
                    listarAlumnos();
                    break;
                case 3:
                    buscarAlumno();
                    break;
                case 4:
                    modificarAlumno();
                    break;
                case 5:
                    eliminarAlumno();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (op >= 1 && op < 6);

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
            System.out.println("Desea modificar el tipo de documento? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Seleccione el nuevo tipo de documento:");
                int i = 1;
                for (TipoDocumento tipo : TipoDocumento.values()) {
                    System.out.println(i++ + ". " + tipo);
                }
                int seleccion = leer.nextInt();
                alumno.setTipoDocumento(TipoDocumento.values()[seleccion - 1]);
            }
            System.out.println("Desea modificar el numero de documento? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo numero de documento:");
                alumno.setNumeroDocumento(leer.nextLine());
            }
            System.out.println("Desea modificar el nombre? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo nombre:");
                alumno.setNombre(leer.nextLine());
            }
            System.out.println("Desea modificar el apellido? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo apellido:");
                alumno.setApellido(leer.nextLine());
            }
            System.out.println("Desea modificar la ciudad? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese la nueva ciudad:");
                alumno.setCiudad(leer.nextLine());
            }
            System.out.println("Desea modificar la direccion? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese la nueva direccion:");
                alumno.setDireccion(leer.nextLine());
            }
            System.out.println("Desea modificar el telefono? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo telefono:");
                alumno.setTelefono(leer.nextLine());
            }
            System.out.println("Desea modificar la fecha de nacimiento? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese la nueva fecha de nacimiento (dd-MM-yyyy):");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                Date fechaNacimiento = null;
                try {
                    fechaNacimiento = formatter.parse(leer.nextLine());
                } catch (ParseException e) {
                    System.out.println("Formato de fecha incorrecto");
                }
                alumno.setFechaNacimiento(fechaNacimiento);
            }

            System.out.println("Desea modificar el genero? (s/n)");
            if (leer.nextLine().equalsIgnoreCase("s")) {
                System.out.println("Ingrese el nuevo genero:");
                alumno.setGenero(leer.nextLine());
            }

            serviceAlumno.editar(alumno);
            System.out.println("Alumno modificado exitosamente.");
        } else {
            System.out.println("Alumno no encontrado.");
        }
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
