package org.montanez.sistema_gestion_universidad.repository;

import org.montanez.sistema_gestion_universidad.repository.models.Edificio;

import java.util.List;

public interface RepositoryEdificio {
    List<Edificio> listar();

    Edificio edificio_id(long id);

    void crear(Edificio edificio);

    void editar(Edificio edificio);

    void eliminar(Edificio edificio);
}
