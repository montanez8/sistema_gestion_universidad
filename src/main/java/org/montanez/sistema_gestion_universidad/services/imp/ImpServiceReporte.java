package org.montanez.sistema_gestion_universidad.services.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryReportes;
import org.montanez.sistema_gestion_universidad.services.ServiceReportes;

import java.util.List;

public class ImpServiceReporte implements ServiceReportes {

    private final RepositoryReportes repositoryReportes;

    public ImpServiceReporte(RepositoryReportes repositoryReportes) {
        this.repositoryReportes = repositoryReportes;
    }

    @Override
    public List<?> listarEstudiantesPorPrograma(int programaId) {
        return repositoryReportes.listarEstudiantesPorPrograma(programaId);
    }

    @Override
    public List<?> calcularCostoSemestre(int alumnoId) {
        return repositoryReportes.calcularCostoSemestre(alumnoId);
    }

    @Override
    public double calcularIngresosUniversidad(int periodoId) {
        return repositoryReportes.calcularIngresosUniversidad(periodoId);
    }

    @Override
    public List<?> imprimirHorarioEstudiante(int alumnoId) {
        return repositoryReportes.imprimirHorarioEstudiante(alumnoId);
    }

    @Override
    public List<?> imprimirMatriculadosPorPrograma() {
        return repositoryReportes.imprimirMatriculadosPorPrograma();
    }
}
