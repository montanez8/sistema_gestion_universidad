package org.montanez.sistema_gestion_universidad.services;

import org.montanez.sistema_gestion_universidad.repository.models.Matricula;

import java.util.List;

public interface ServiceMatricula {
    List<Matricula> listar();

    Matricula matricula_id(long id);

    void crear(Matricula matricula);

    void editar(Matricula matricula);

    void eliminar(long id);
}
