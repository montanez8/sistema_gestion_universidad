package org.montanez.sistema_gestion_universidad.repository;

import org.montanez.sistema_gestion_universidad.repository.models.Curso;

import java.util.List;

public interface RepositoryCurso {

    List<Curso> listar();

    Curso curso_id(long id);

    void crear(Curso curso);

    void editar(Curso curso);

    void eliminar(long id);

}
