package org.montanez.sistema_gestion_universidad.services.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryHorario;
import org.montanez.sistema_gestion_universidad.repository.models.Horario;
import org.montanez.sistema_gestion_universidad.services.ServiceHorario;

import java.util.List;

public class ImpServiceHorario implements ServiceHorario {
    private final RepositoryHorario repositoryHorario;

    public ImpServiceHorario(RepositoryHorario repositoryHorario) {
        this.repositoryHorario = repositoryHorario;
    }

    @Override
    public List<Horario> listar() {
        return this.repositoryHorario.listar();
    }

    @Override
    public Horario horario_id(long id) {
        Horario horario = this.repositoryHorario.horario_id(id);
        if (horario == null) {
            try {
                throw new RuntimeException("El horario no existe");
            } catch (RuntimeException e) {
                e.printStackTrace();

            }
        }
        return horario;
    }

    @Override
    public void crear(Horario horario) {
        this.repositoryHorario.crear(horario);
    }

    @Override
    public void editar(Horario horario) {
        this.repositoryHorario.editar(horario);
    }

    @Override
    public void eliminar(long id) {
        this.repositoryHorario.eliminar(id);
    }
}
