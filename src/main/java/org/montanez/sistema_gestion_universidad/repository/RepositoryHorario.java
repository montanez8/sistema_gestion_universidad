package org.montanez.sistema_gestion_universidad.repository;

import org.montanez.sistema_gestion_universidad.repository.models.Horario;

import java.util.List;

public interface RepositoryHorario {
    List<Horario> listar();

    Horario horario_id(long id);

    void crear(Horario horario);

    void editar(Horario horario);

    void eliminar(long id);
}
