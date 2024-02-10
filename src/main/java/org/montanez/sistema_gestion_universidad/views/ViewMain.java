package org.montanez.sistema_gestion_universidad.views;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.imp.imp_alumno.ImpRepositoryAlumno;
import org.montanez.sistema_gestion_universidad.repository.imp.imp_departamento.ImpRepositoryDepartamento;
import org.montanez.sistema_gestion_universidad.services.ServiceAlumno;
import org.montanez.sistema_gestion_universidad.services.ServiceDepartemento;
import org.montanez.sistema_gestion_universidad.services.imp.imp_alumno.ImpServiceAlumno;
import org.montanez.sistema_gestion_universidad.services.imp.imp_departamento.ImpServiceDepartamento;

import java.sql.SQLException;
import java.util.Scanner;

public class ViewMain {
    public static final Scanner leer = new Scanner(System.in);

    public static final ServiceAlumno serviceAlumno;
    public static final ServiceDepartemento serviceDepartemento;

    static {
        try {
            serviceAlumno = new ImpServiceAlumno(new ImpRepositoryAlumno());
            serviceDepartemento = new ImpServiceDepartamento(new ImpRepositoryDepartamento());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws ExepcionesNullExeption {

        int op = 0;

        do {

            op = menuMain();
            switch (op) {
                case 1:
                    ViewAlumno.startMenu();
                    break;
                case 2:

                    break;
                case 3:

                    break;
                default:
                    System.out.println("Fin");
                    break;
            }

        } while (op >= 1 && op < 4);

    }

    public static int menuMain() {
        System.out.println("\033[1;33m---Universidad de los Sabios-----\033[0m");
        System.out.println("\033[1;32m1. Modulo de Alumno\033[0m");
        System.out.println("\033[1;32m2. Modulo de Producto\033[0m");
        System.out.println("\033[1;32m3. Modulo de Factura\033[0m");
        System.out.println("\033[1;31m4. Salir:\033[0m");
        return leer.nextInt();
    }

}

