package org.montanez.sistema_gestion_universidad.services.imp.imp_alumno;

import org.montanez.sistema_gestion_universidad.exepciones.ExepcionesNullExeption;
import org.montanez.sistema_gestion_universidad.repository.RepositoryAlumno;
import org.montanez.sistema_gestion_universidad.repository.models.Alumno;
import org.montanez.sistema_gestion_universidad.services.ServiceAlumno;

import java.util.List;

public class ImpServiceAlumno implements ServiceAlumno {
    private final RepositoryAlumno repositoryAlumno;

    public ImpServiceAlumno(RepositoryAlumno repositoryAlumno) {
        this.repositoryAlumno = repositoryAlumno;
    }

    @Override
    public List<Alumno> listar() {
        return this.repositoryAlumno.listar();
    }

    @Override
    public Alumno alumno_id(long id) throws ExepcionesNullExeption {
        Alumno alumno = this.repositoryAlumno.alumno_id(id);
        if (alumno == null) {
            throw new ExepcionesNullExeption("El alumno no existe");
        }
        return alumno;
    }

    @Override
    public void crear(Alumno alumno) {
        this.repositoryAlumno.crear(alumno);

    }

    @Override
    public void editar(Alumno alumno) {
        this.repositoryAlumno.editar(alumno);

    }

    @Override
    public void eliminar(long id) {
        this.repositoryAlumno.eliminar(id);

    }
}