package org.montanez.sistema_gestion_universidad.services;

import org.montanez.sistema_gestion_universidad.repository.models.Programa;

import java.util.List;

public interface ServicePrograma {
    List<Programa> listar();

    Programa programa_id(long id);

    void crear(Programa programa);

    void editar(Programa programa);

    void eliminar(Programa programa);
}
