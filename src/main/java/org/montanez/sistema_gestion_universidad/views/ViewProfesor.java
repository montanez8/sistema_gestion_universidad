package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.repository.models.Departamento;
import org.montanez.sistema_gestion_universidad.repository.models.Profesor;
import org.montanez.sistema_gestion_universidad.repository.models.TipoDocumento;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class ViewProfesor extends ViewMain {
    public static void startMenu() {
        int op = 0;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1 -> crearProfesor();
                case 2 -> listarProfesor();
                case 3 -> buscarProfesor();
                case 4 -> modificarProfesor();
                case 5 -> eliminarProfesor();
                default -> System.out.println("Opcion no valida");
            }

        } while (op >= 1 && op < 6);
    }

    private static void eliminarProfesor() {
        System.out.println("Eliminar Profesor");
        listarProfesor();
        System.out.println("Ingrese el id del profesor");
        long id = leer.nextLong();
        Profesor profesor = serviceProfesor.profesor_id(id);
        if (profesor != null) {
            System.out.println("¿Está seguro de que desea eliminar el profesor? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                serviceProfesor.eliminar(id);
                System.out.println("Profesor eliminado");
            } else {
                System.out.println("Eliminación cancelada");
            }
        } else {
            System.out.println("Profesor no encontrado");
        }
    }

    private static void modificarProfesor() {
        System.out.println("Modificar Profesor");
        listarProfesor();
        System.out.println("Ingrese el id del profesor");
        long id = leer.nextLong();
        Profesor profesor = serviceProfesor.profesor_id(id);
        if (profesor != null) {
            System.out.println("¿Desea modificar el tipo de documento? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Seleccione el tipo de documento:");
                int i = 1;
                for (TipoDocumento tipo : TipoDocumento.values()) {
                    System.out.println(i++ + ". " + tipo);
                }
                int seleccion = leer.nextInt();
                TipoDocumento tipoDocumento = TipoDocumento.values()[seleccion - 1];
                profesor.setTipoDocumento(tipoDocumento);
            }
            System.out.println("¿Desea modificar el numero de documento? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese el numero de documento");
                String numeroDocumento = leer.next();
                profesor.setNumeroDocumento(numeroDocumento);
            }
            System.out.println("¿Desea modificar el primer nombre? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese el primer nombre");
                String nombre = leer.next();
                profesor.setNombre(nombre);
            }
            System.out.println("¿Desea modificar el primer apellido? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese el primer apellido");
                String apellido = leer.next();
                profesor.setApellido(apellido);
            }
            System.out.println("¿Desea modificar la ciudad? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la ciudad");
                String ciudad = leer.next();
                profesor.setCiudad(ciudad);
            }
            System.out.println("¿Desea modificar la direccion? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la direccion");
                String direccion = leer.next();
                profesor.setDireccion(direccion);
            }
            System.out.println("¿Desea modificar el telefono? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese el telefono");
                String telefono = leer.next();
                profesor.setTelefono(telefono);
            }
            System.out.println("¿Desea modificar la fecha de nacimiento? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese la fecha de nacimiento (yyyy-MM-dd)");
                String fechaNacimiento = leer.next();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate fechaNacimientoLocalDate = LocalDate.parse(fechaNacimiento, formatter);
                Date fechaNacimientoDate = Date.valueOf(fechaNacimientoLocalDate);
                profesor.setFechaNacimiento(fechaNacimientoDate);
            }
            System.out.println("¿Desea modificar el genero? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Ingrese el genero (M/F)");
                String genero = leer.next();
                profesor.setGenero(genero);
            }
            System.out.println("¿Desea modificar el departamento? (s/n)");
            confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                System.out.println("Departamento");
                List<Departamento> departamentos = serviceDepartemento.listar();
                departamentos.forEach(System.out::println);
                long departamento_id = leer.nextLong();
                Departamento departamento = serviceDepartemento.buscarId(departamento_id);
                if (departamento != null) {
                    profesor.setDepartamento(departamento);
                } else {
                    System.out.println("Departamento no encontrado");
                }
            }
            serviceProfesor.editar(profesor);
            if (profesor != null) {
                System.out.println("Profesor modificado");
            } else {
                System.out.println("Profesor no modificado Error.");
            }
        } else {
            System.out.println("Profesor no encontrado");

        }


    }

    private static void buscarProfesor() {
        System.out.println("Buscar Profesor");
        System.out.println("Ingrese el id del profesor");
        long id = leer.nextLong();
        Profesor profesor = serviceProfesor.profesor_id(id);
        if (profesor != null) {
            System.out.println(profesor);
        } else {
            System.out.println("Profesor no encontrado");
        }
    }

    private static void listarProfesor() {
        System.out.println("Listar Profesor");
        serviceProfesor.listar().forEach(System.out::println);
    }

    private static void crearProfesor() {
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
        System.out.println("Fecha de nacimiento (yyyy-MM-dd)");
        String fechaNacimiento = leer.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaNacimientoLocalDate = LocalDate.parse(fechaNacimiento, formatter);
        Date fechaNacimientoDate = Date.valueOf(fechaNacimientoLocalDate);
        System.out.println("Genero (M/F)");
        String genero = leer.next();
        System.out.println("Departamento");
        List<Departamento> departamentos = serviceDepartemento.listar();
        departamentos.forEach(System.out::println);
        long departamento_id = leer.nextLong();
        Departamento departamento = serviceDepartemento.buscarId(departamento_id);
        if (departamento != null) {
            Profesor profesor = new Profesor(tipoDocumento, numeroDocumento, nombre, apellido, ciudad, direccion, telefono, fechaNacimientoDate, genero, departamento);
            serviceProfesor.crear(profesor);
            System.out.println("Profesor creado");
        } else {
            System.out.println("Departamento no encontrado");
        }
    }

    private static int mostrarMenu() {
        System.out.println("Menu Profesor");
        System.out.println("1. Crear Profesor");
        System.out.println("2. Listar Profesor");
        System.out.println("3. Buscar Profesor");
        System.out.println("4. Modificar Profesor");
        System.out.println("5. Eliminar Profesor");
        System.out.println("6. Salir");
        System.out.println("Ingrese una opción");
        return leer.nextInt();
    }

}