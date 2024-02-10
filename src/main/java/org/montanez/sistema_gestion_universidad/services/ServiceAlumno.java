package org.montanez.sistema_gestion_universidad.services;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.models.Alumno;

import java.util.List;

public interface ServiceAlumno {
    List<Alumno> listar();

    Alumno alumno_id(long id) throws ExepcionesNullExeption;

    void crear(Alumno alumno);

    void editar(Alumno alumno);

    void eliminar(long id);
}
