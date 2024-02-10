package org.montanez.sistema_gestion_universidad.repository;

import org.montanez.sistema_gestion_universidad.repository.models.Salon;

import java.util.List;

public interface RepositorySalon {
    List<Salon> listar();

    Salon salon_id(long id);

    void crear(Salon salon);

    void editar(Salon salon);

    void eliminar(Salon salon);
}
