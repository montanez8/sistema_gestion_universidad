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


    static {
        try {
            serviceAlumno = new ImpServiceAlumno(new ImpRepositoryAlumno());
            serviceDepartemento = new ImpServiceDepartamento(new ImpRepositoryDepartamento());
            servicePrograma = new ImpServicePrograma(new ImpRepositoryPrograma());
            serviceCurso = new ImpServiceCurso(new ImpRepositoryCurso());
            servicePeriodo = new ImpServicePeriodo(new ImpRepositoryPeriodo());
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
                default -> {
                    if (op < 1 || op > 3) {
                        System.out.println("Fin");
                    }
                }
            }
        } while (op >= 1 && op < 4);
    }


    private static int menuMain() {
        System.out.println("\033[1;33m---Universidad de los Sabios-----\033[0m");
        System.out.println("\033[1;32m1. Modulo de Alumno\033[0m");
        System.out.println("\033[1;32m2. Modulo de Departamento\033[0m");
        System.out.println("\033[1;32m3. Modulo de Programa\033[0m");
        System.out.println("\033[1;32m4. Modulo de Curso\033[0m");
        System.out.println("\033[1;31m5. Salir:\033[0m");
        return leer.nextInt();
    }
}