package org.montanez.sistema_gestion_universidad.services;

import org.montanez.sistema_gestion_universidad.repository.models.Profesor;

import java.util.List;

public interface ServiceProfesor {
    List<Profesor> listar();

    Profesor profesor_id(long id);

    void crear(Profesor profesor);

    void editar(Profesor profesor);

    void eliminar(long id);
}
