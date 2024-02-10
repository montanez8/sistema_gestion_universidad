package org.montanez.sistema_gestion_universidad.services;

import org.montanez.sistema_gestion_universidad.repository.models.Periodo;

import java.util.List;

public interface ServicePeriodo {

    List<Periodo> listar();

    Periodo buscarId(long id);

    void crear(Periodo periodo);

    void editar(Periodo periodo);

    void eliminar(long id);
}
