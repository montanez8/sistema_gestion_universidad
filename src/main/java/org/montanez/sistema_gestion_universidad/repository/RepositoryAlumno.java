package org.montanez.sistema_gestion_universidad.repository;

import org.montanez.sistema_gestion_universidad.repository.models.Alumno;

import java.util.List;

public interface RepositoryAlumno {
    List<Alumno> listar();

    Alumno alumno_id(long id);

    void crear(Alumno alumno);

    void editar(Alumno alumno);

    void eliminar(long id);
}
