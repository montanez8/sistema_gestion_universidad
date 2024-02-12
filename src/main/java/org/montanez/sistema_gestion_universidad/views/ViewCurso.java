package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Curso;

public class ViewCurso extends ViewMain {
    public static void startMenu() throws ExepcionesNullExeption {
        int op;

        do {
            op = mostrarMenu();
            switch (op) {
                case 1 -> crearCurso();
                case 2 -> listarCurso();
                case 3 -> buscarCurso();
                case 4 -> modificarCurso();
                case 5 -> eliminarCurso();
                default -> System.out.println("Opcion no valida");
            }
        } while (op >= 1 && op < 6);
    }

    private static void eliminarCurso() {
        System.out.println("Eliminar Curso");
        listarCurso();
        System.out.println("Ingrese el id del curso");
        long id = leer.nextLong();
        if (serviceCurso.curso_id(id) != null) {
            System.out.println("¿Está seguro de que desea eliminar el curso? (s/n)");
            String confirm = leer.next();
            if (confirm.equalsIgnoreCase("s")) {
                serviceCurso.eliminar(id);
                System.out.println("Curso eliminado");
            } else {
                System.out.println("Eliminación cancelada");
            }
        } else {
            System.out.println("Curso no encontrado");
        }
    }

    private static void modificarCurso() {
        System.out.println("Modificar Curso");
        listarCurso();
        System.out.println("Ingrese el id del curso");
        long id = leer.nextLong();
        Curso curso = serviceCurso.curso_id(id);
        if (curso != null) {
            leer.nextLine();
            curso.setNombre(obtenerValor("nombre del curso", curso.getNombre()));
            curso.setGuiaCatedra(obtenerValor("guia de catedra", curso.getGuiaCatedra()));
            serviceCurso.editar(curso);
            System.out.println("Curso modificado");
        } else {
            System.out.println("Curso no encontrado");
        }
    }

    private static String obtenerValor(String nombreCampo, String valorAnterior) {
        System.out.println("Desea modificar el " + nombreCampo + "? (s/n)");
        if (leer.nextLine().equalsIgnoreCase("s")) {
            System.out.println("Ingrese el nuevo " + nombreCampo);
            return leer.nextLine();
        }
        return valorAnterior;
    }

    private static void buscarCurso() {
        System.out.println("Buscar Curso");
        System.out.println("Ingrese el id del curso");
        long id = leer.nextLong();
        if (serviceCurso.curso_id(id) != null) {
            System.out.println(serviceCurso.curso_id(id));
        } else {
            System.out.println("Curso no encontrado");
        }


    }

    private static void listarCurso() {
        System.out.println("Listar Curso");
        serviceCurso.listar().forEach(System.out::println);

    }

    private static void crearCurso() {
        System.out.println("Crear Curso");
        System.out.println("Ingrese el nombre del curso");
        String nombre = leer.next();
        System.out.println("Ingrese la guia de catedra");
        String guiaCatedra = leer.next();
        serviceCurso.crear(new Curso(nombre, guiaCatedra));
        System.out.println("Curso creado");


    }

    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Programa----\033[0m");
        System.out.println("\033[1;32m1. Crear Curso.\033[0m");
        System.out.println("\033[1;32m2. Listar Curso.\033[0m");
        System.out.println("\033[1;32m3. Buscar Curso.\033[0m");
        System.out.println("\033[1;32m4. Modificar Curso.\033[0m");
        System.out.println("\033[1;32m5. Eliminar Curso.\033[0m");
        System.out.println("\033[1;31m6. Salir\033[0m");
        return leer.nextInt();
    }

}
