package org.montanez.sistema_gestion_universidad.services;

import org.montanez.sistema_gestion_universidad.repository.models.Edificio;

import java.util.List;

public interface ServiceEdificio {
    List<Edificio> listar();

    Edificio edificio_id(long id);

    void crear(Edificio edificio);

    void editar(Edificio edificio);

    void eliminar(Edificio edificio);
}
