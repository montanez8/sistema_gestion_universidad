package org.montanez.sistema_gestion_universidad.services.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryPeriodo;
import org.montanez.sistema_gestion_universidad.repository.models.Periodo;
import org.montanez.sistema_gestion_universidad.services.ServicePeriodo;

import java.util.List;

public class ImpServicePeriodo implements ServicePeriodo {
    private final RepositoryPeriodo repositoryPeriodo;

    public ImpServicePeriodo(RepositoryPeriodo repositoryPeriodo) {
        this.repositoryPeriodo = repositoryPeriodo;
    }

    @Override
    public List<Periodo> listar() {
        return this.repositoryPeriodo.listar();
    }

    @Override
    public Periodo buscarId(long id) {
        Periodo periodo = this.repositoryPeriodo.buscarId(id);
        if (periodo == null) {
            try {
                throw new RuntimeException("El periodo no existe");
            } catch (RuntimeException e) {
                e.printStackTrace();

            }
        }
        return periodo;
    }

    @Override
    public void crear(Periodo periodo) {
        this.repositoryPeriodo.crear(periodo);

    }

    @Override
    public void editar(Periodo periodo) {
        this.repositoryPeriodo.editar(periodo);
    }

    @Override
    public void eliminar(long id) {
        this.repositoryPeriodo.eliminar(id);
    }
}
