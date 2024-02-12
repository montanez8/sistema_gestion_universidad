package org.montanez.sistema_gestion_universidad.services;

import org.montanez.sistema_gestion_universidad.repository.models.Curso;

import java.util.List;

public interface ServiceCurso {

    List<Curso> listar();

    Curso curso_id(long id) throws RuntimeException;

    void crear(Curso curso);

    void editar(Curso curso);

    void eliminar(long id);

}
