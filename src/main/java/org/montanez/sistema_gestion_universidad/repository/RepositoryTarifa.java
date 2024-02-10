package org.montanez.sistema_gestion_universidad.repository;

import org.montanez.sistema_gestion_universidad.repository.models.Tarifa;

import java.util.List;

public interface RepositoryTarifa {
    List<Tarifa> listar();

    Tarifa buscarId(long id);

    void crear(Tarifa tarifa);

    void editar(Tarifa tarifa);

    void eliminar(Tarifa tarifa);
}
