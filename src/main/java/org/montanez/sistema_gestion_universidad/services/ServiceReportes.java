package org.montanez.sistema_gestion_universidad.services;

import java.util.List;

public interface ServiceReportes {
    List<?> listarEstudiantesPorPrograma(int programaId);

    List<?> calcularCostoSemestre(int alumnoId);

    double calcularIngresosUniversidad(int periodoId);

    List<?> imprimirHorarioEstudiante(int alumnoId);

    List<?> imprimirMatriculadosPorPrograma();

}
