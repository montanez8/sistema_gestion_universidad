package org.montanez.sistema_gestion_universidad.services.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryDepartemento;
import org.montanez.sistema_gestion_universidad.repository.models.Departamento;
import org.montanez.sistema_gestion_universidad.services.ServiceDepartemento;

import java.util.List;

public class ImpServiceDepartamento implements ServiceDepartemento {
    private final RepositoryDepartemento repositoryDepartamento;

    public ImpServiceDepartamento(RepositoryDepartemento repositoryDepartamento) {
        this.repositoryDepartamento = repositoryDepartamento;
    }

    @Override
    public List<Departamento> listar() {
        return this.repositoryDepartamento.listar();
    }

    @Override
    public Departamento buscarId(long id) {
        Departamento departamento = this.repositoryDepartamento.buscarId(id);
        if (departamento == null) {
            try {
                throw new RuntimeException("El departamento no existe");
            } catch (RuntimeException e) {
                e.printStackTrace();

            }
        }
        return departamento;
    }

    @Override
    public void crear(Departamento departamento) {
        this.repositoryDepartamento.crear(departamento);

    }

    @Override
    public void editar(Departamento departamento) {
        this.repositoryDepartamento.editar(departamento);

    }

    @Override
    public void eliminar(Departamento departamento) {
        this.repositoryDepartamento.eliminar(departamento);

    }
}