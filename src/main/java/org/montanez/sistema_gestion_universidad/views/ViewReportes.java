package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Map;


public class ViewReportes extends ViewMain {
    public static void startMenu() throws ExepcionesNullExeption {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1 -> listarEstudiantesPorPrograma();
                case 2 -> calcularCostoSemestre();
                case 3 -> calcularIngresosUniversidad();
                case 4 -> imprimirHorarioEstudiante();
                case 5 -> imprimirMatriculadosPorPrograma();

                default -> System.out.println("Opcion no valida");
            }
        } while (op >= 1 && op <= 5);
    }

    private static void imprimirMatriculadosPorPrograma() {
        System.out.println("\033[1;33mImprimir el número de matriculados por programa\033[0m");
        List<Map<String, Object>> matriculadosPorPrograma = (List<Map<String, Object>>) serviceReportes.imprimirMatriculadosPorPrograma();
        if (matriculadosPorPrograma.isEmpty()) {
            System.out.println("\033[1;31mNo hay datos disponibles.\033[0m");
        } else {
            System.out.println("\033[1;34m----------------------------------------\033[0m");
            System.out.println("\033[1;33m| Nombre del programa | Estudiantes matriculados |\033[0m");
            System.out.println("\033[1;34m----------------------------------------\033[0m");
            for (Map<String, Object> programa : matriculadosPorPrograma) {
                System.out.println("\033[1;34m| " + programa.get("Nombre_programa") + " | " + programa.get("estudiantes_matriculados") + " |\033[0m");
                System.out.println("\033[1;34m----------------------------------------\033[0m");
            }
        }
    }

    private static void imprimirHorarioEstudiante() {
        System.out.println("\033[1;36mImprimir el horario de un estudiante\033[0m");
        serviceAlumno.listar().forEach(System.out::println);
        System.out.println("\033[1;35mIngrese el id del estudiante\033[0m");
        int id = leer.nextInt();
        List<Map<String, Object>> horarioEstudiante = (List<Map<String, Object>>) serviceReportes.imprimirHorarioEstudiante(id);
        if (horarioEstudiante.isEmpty()) {
            System.out.println("\033[1;31mEl estudiante con id: " + id + " No tiene asignaturas matriculadas.\033[0m ");
        } else {
            for (Map<String, Object> horario : horarioEstudiante) {
                System.out.println("\033[1;32m----------------------------------------\033[0m");
                System.out.println("Nombre del estudiante: " + horario.get("nombre_estudiante"));
                System.out.println("\033[1;32mApellido del estudiante: \033[0m" + horario.get("apellido_estudiante"));
                System.out.println("\033[1;32mAsignatura: \033[0m" + horario.get("asignatura"));
                System.out.println("\033[1;32mDia: \033[0m" + horario.get("dia"));
                System.out.println("\033[1;32mHora de inicio: \033[0m" + horario.get("hora_inicio"));
                System.out.println("\033[1;32mHora final: \033[0m" + horario.get("hora_final"));
                System.out.println("\033[1;32m----------------------------------------\033[0m");
            }
        }
    }

    private static void calcularIngresosUniversidad() {
        System.out.println("\033[1;33mCalcular los ingreso de la universidad por semestre\033[0m");
        servicePeriodo.listar().forEach(System.out::println);
        System.out.println("\033[1;32mIngrese el id del periodo\033[0m");
        int id = leer.nextInt();
        double ingresos = serviceReportes.calcularIngresosUniversidad(id);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        System.out.println("\033[1;34mLos ingresos de la universidad para el periodo seleccionado son: \033[0m" + currencyFormatter.format(ingresos));

    }


    private static void calcularCostoSemestre() {
        System.out.println("\033[1;33mCalcular el costo del semestre de un estudiante\033[0m");
        serviceAlumno.listar().forEach(System.out::println);
        System.out.println("\033[1;32mIngrese el id del estudiante\033[0m");
        int id = leer.nextInt();
        List<Map<String, Object>> detallesEstudiante = (List<Map<String, Object>>) serviceReportes.calcularCostoSemestre(id);
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        for (Map<String, Object> detalle : detallesEstudiante) {
            System.out.println("\033[1;34m----------------------------------------\033[0m");
            System.out.println("Id del estudiante: " + detalle.get("id_alumno"));
            System.out.println("\033[1;34mNombres: \033[0m" + detalle.get("nombres"));
            System.out.println("\033[1;34mApellidos: \033[0m" + detalle.get("apellidos"));
            System.out.println("\033[1;34mPeriodo: \033[0m" + detalle.get("periodo"));
            double costoSemestre = (double) detalle.get("costo_semestre");
            System.out.println("\033[1;34mCosto del semestre: \033[0m" + currencyFormatter.format(costoSemestre));
            System.out.println("\033[1;34m----------------------------------------\033[0m");
        }
    }

    private static void listarEstudiantesPorPrograma() {
        System.out.println("\033[1;33mListar estudiantes por programa\033[0m");
        servicePrograma.listar().forEach(System.out::println);
        System.out.println("\033[1;32mIngrese el id del programa\033[0m");
        int id = leer.nextInt();
        List<Map<String, Object>> estudiantes = (List<Map<String, Object>>) serviceReportes.listarEstudiantesPorPrograma(id);
        for (Map<String, Object> estudiante : estudiantes) {
            System.out.println("\033[1;32mID Alumno: \033[0m" + estudiante.get("idAlumno"));
            System.out.println("\033[1;32mNombres: \033[0m" + estudiante.get("nombres"));
            System.out.println("\033[1;32mApellidos: \033[0m" + estudiante.get("apellidos"));
            System.out.println("\033[1;32mPrograma: \033[0m" + estudiante.get("programa"));
            System.out.println();
        }
    }

    public static int mostrarMenu() {
        System.out.println("\033[1;33m----Menu--Reportes----\033[0m");
        System.out.println("\033[1;32m1.Listar los estudiantes matriculados por programa.\033[0m");
        System.out.println("\033[1;32m2.Calcular el costo del semestre de un estudiante según las asignaturas matriculadas.\033[0m");
        System.out.println("\033[1;32m3.Calcular los ingreso de la universidad por semestre.\033[0m");
        System.out.println("\033[1;32m4.Imprimir el horario de un estudiante.\033[0m");
        System.out.println("\033[1;32m5.Imprimir en forma descendente el número de matriculados por programa.\033[0m");
        System.out.println("\033[1;31m6.Salir\033[0m");
        return leer.nextInt();
    }


}
