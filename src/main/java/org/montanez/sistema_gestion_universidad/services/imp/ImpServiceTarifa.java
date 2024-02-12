package org.montanez.sistema_gestion_universidad.services.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryTarifa;
import org.montanez.sistema_gestion_universidad.repository.models.Tarifa;
import org.montanez.sistema_gestion_universidad.services.ServiceTarifa;

import java.util.List;

public class ImpServiceTarifa implements ServiceTarifa {
    private final RepositoryTarifa repositoryTarifa;

    public ImpServiceTarifa(RepositoryTarifa repositoryTarifa) {
        this.repositoryTarifa = repositoryTarifa;
    }

    @Override
    public List<Tarifa> listar() {
        return this.repositoryTarifa.listar();
    }

    @Override
    public Tarifa buscarId(long id) {
        Tarifa tarifa = this.repositoryTarifa.buscarId(id);
        if (tarifa == null) {
            try {
                throw new RuntimeException("La tarifa no existe");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return tarifa;
    }

    @Override
    public void crear(Tarifa tarifa) {
        this.repositoryTarifa.crear(tarifa);
    }

    @Override
    public void editar(Tarifa tarifa) {
        this.repositoryTarifa.editar(tarifa);
    }

    @Override
    public void eliminar(Tarifa tarifa) {
        this.repositoryTarifa.eliminar(tarifa);
    }
}
