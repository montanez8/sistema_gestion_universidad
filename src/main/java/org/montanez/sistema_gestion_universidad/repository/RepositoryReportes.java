package org.montanez.sistema_gestion_universidad.repository;

import java.util.List;

public interface RepositoryReportes {
    List<?> listarEstudiantesPorPrograma(int programaId);


    List<?> calcularCostoSemestre(int alumnoId);

    double calcularIngresosUniversidad(int periodoId);

    List<?> imprimirHorarioEstudiante(int alumnoId);

    List<?> imprimirMatriculadosPorPrograma();

}
