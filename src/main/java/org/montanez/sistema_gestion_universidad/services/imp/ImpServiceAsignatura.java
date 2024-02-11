package org.montanez.sistema_gestion_universidad.services.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryAsignatura;
import org.montanez.sistema_gestion_universidad.repository.models.Asignatura;
import org.montanez.sistema_gestion_universidad.services.ServiceAsignatura;

import java.util.List;

public class ImpServiceAsignatura implements ServiceAsignatura {
    private final RepositoryAsignatura repositoryAsignatura;

    public ImpServiceAsignatura(RepositoryAsignatura repositoryAsignatura) {
        this.repositoryAsignatura = repositoryAsignatura;
    }

    @Override
    public List<Asignatura> listar() {
        return this.repositoryAsignatura.listar();
    }

    @Override
    public Asignatura asignatura_id(long id) {
        Asignatura asignatura = this.repositoryAsignatura.asignatura_id(id);
        if (asignatura == null) {
            throw new RuntimeException("La asignatura no existe");
        }
        return asignatura;
    }

    @Override
    public void crear(Asignatura asignatura) {
        this.repositoryAsignatura.crear(asignatura);

    }

    @Override
    public void editar(Asignatura asignatura) {
        this.repositoryAsignatura.editar(asignatura);

    }

    @Override
    public void eliminar(long id) {
        this.repositoryAsignatura.eliminar(id);
    }
}
