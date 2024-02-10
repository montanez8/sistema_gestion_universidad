package org.montanez.sistema_gestion_universidad.services;

import org.montanez.sistema_gestion_universidad.repository.models.Departamento;

import java.util.List;

public interface ServiceDepartemento {

    List<Departamento> listar();

    Departamento buscarId(long id);

    void crear(Departamento departamento);

    void editar(Departamento departamento);

    void eliminar(Departamento departamento);


}
