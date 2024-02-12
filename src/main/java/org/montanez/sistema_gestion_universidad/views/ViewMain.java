package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.imp.*;
import org.montanez.sistema_gestion_universidad.services.*;
import org.montanez.sistema_gestion_universidad.services.imp.*;

import java.util.Scanner;

public class ViewMain {
    public static final Scanner leer = new Scanner(System.in);

    public static final ServiceAlumno serviceAlumno;
    public static final ServiceDepartemento serviceDepartemento;
    public static final ServicePrograma servicePrograma;
    public static final ServiceCurso serviceCurso;
    public static final ServicePeriodo servicePeriodo;
    public static final ServiceSalon serviceSalon;
    public static final ServiceHorario serviceHorario;
    public static final ServiceEdificio serviceEdificio;
    public static final ServiceProfesor serviceProfesor;
    public static final ServiceAsignatura serviceAsignatura;
    public static final ServiceTarifa serviceTarifa;
    public static final ServiceMatricula serviceMatricula;

    public static final ServiceReportes serviceReportes;


    static {
        try {
            serviceAlumno = new ImpServiceAlumno(new ImpRepositoryAlumno());
            serviceDepartemento = new ImpServiceDepartamento(new ImpRepositoryDepartamento());
            servicePrograma = new ImpServicePrograma(new ImpRepositoryPrograma());
            serviceCurso = new ImpServiceCurso(new ImpRepositoryCurso());
            servicePeriodo = new ImpServicePeriodo(new ImpRepositoryPeriodo());
            serviceSalon = new ImpServiceSalon(new ImpRepositorySalon());
            serviceHorario = new ImpServiceHorario(new ImpRepositoryHorario());
            serviceEdificio = new ImpServiceEdificio(new ImpRepositoryEdificio());
            serviceProfesor = new ImpServiceProfesor(new ImpRepositoryProfesor());
            serviceAsignatura = new ImpServiceAsignatura(new ImpRepositoryAsignatura());
            serviceTarifa = new ImpServiceTarifa(new ImpRepositoryTarifa());
            serviceMatricula = new ImpServiceMatricula(new ImpRepositoryMatricula());
            serviceReportes = new ImpServiceReporte(new ImpRepositoryReportes());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ExepcionesNullExeption {
        int op;
        do {
            op = menuMain();
            switch (op) {
                case 1 -> ViewAlumno.startMenu();
                case 2 -> ViewDepartamento.startMenu();
                case 3 -> ViewPrograma.startMenu();
                case 4 -> ViewCurso.startMenu();
                case 5 -> ViewPeriodo.startMenu();
                case 6 -> ViewSalon.startMenu();
                case 7 -> ViewHorario.startMenu();
                case 8 -> ViewEdificio.startMenu();
                case 9 -> ViewProfesor.startMenu();
                case 10 -> ViewAsignatura.startMenu();
                case 11 -> ViewTarifa.startMenu();
                case 12 -> ViewMatricula.startMenu();
                case 13 -> ViewReportes.startMenu();

                default -> {
                    if (op < 1 || op > 13) {
                        System.out.println("Fin");
                    }
                }
            }
        } while (op >= 1 && op < 14);
    }


    private static int menuMain() {
        System.out.println("\033[1;33m---Universidad de los Sabios-----\033[0m");
        System.out.println("\033[1;32m1. Modulo de Alumno\033[0m");
        System.out.println("\033[1;32m2. Modulo de Departamento\033[0m");
        System.out.println("\033[1;32m3. Modulo de Programa\033[0m");
        System.out.println("\033[1;32m4. Modulo de Curso\033[0m");
        System.out.println("\033[1;32m5. Modulo de Periodo\033[0m");
        System.out.println("\033[1;32m6. Modulo de Salon\033[0m");
        System.out.println("\033[1;32m7. Modulo de Horario\033[0m");
        System.out.println("\033[1;32m8. Modulo de Edificio\033[0m");
        System.out.println("\033[1;32m9. Modulo de Profesor\033[0m");
        System.out.println("\033[1;32m10. Modulo de Asignatura\033[0m");
        System.out.println("\033[1;32m11. Modulo Tarifa:\033[0m");
        System.out.println("\033[1;32m12. Modulo Matricula:\033[0m");
        System.out.println("\033[1;32m13. Modulo Reportes:\033[0m");

        System.out.println("\033[1;31m14. Salir:\033[0m");
        return leer.nextInt();
    }
}