package org.montanez.sistema_gestion_universidad.services;

import org.montanez.sistema_gestion_universidad.repository.models.Tarifa;

import java.util.List;

public interface ServiceTarifa {
    List<Tarifa> listar();

    Tarifa buscarId(long id);

    void crear(Tarifa tarifa);

    void editar(Tarifa tarifa);

    void eliminar(Tarifa tarifa);
}
