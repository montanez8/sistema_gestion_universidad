package org.montanez.sistema_gestion_universidad.repository;

import org.montanez.sistema_gestion_universidad.repository.models.Asignatura;

import java.util.List;

public interface RepositoryAsignatura {
    List<Asignatura> listar();

    Asignatura asignatura_id(long id);

    void crear(Asignatura asignatura);

    void editar(Asignatura asignatura);

    void eliminar(long id);
}
