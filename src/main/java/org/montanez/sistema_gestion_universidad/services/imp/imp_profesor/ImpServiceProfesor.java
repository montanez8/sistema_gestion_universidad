package org.montanez.sistema_gestion_universidad.services.imp.imp_profesor;


import org.montanez.sistema_gestion_universidad.repository.RepositoryProfesor;
import org.montanez.sistema_gestion_universidad.repository.models.Profesor;
import org.montanez.sistema_gestion_universidad.services.ServiceProfesor;

import java.util.List;

public class ImpServiceProfesor implements ServiceProfesor {
    private final RepositoryProfesor repositoryProfesor;

    public ImpServiceProfesor(RepositoryProfesor repositoryProfesor) {
        this.repositoryProfesor = repositoryProfesor;
    }
    
    @Override
    public List<Profesor> listar() {
        return this.repositoryProfesor.listar();
    }

    @Override
    public Profesor profesor_id(long id) {
        Profesor profesor = this.repositoryProfesor.profesor_id(id);
        if (profesor == null) {
            throw new RuntimeException("El profesor no existe");
        }
        return profesor;
    }

    @Override
    public void crear(Profesor profesor) {
        this.repositoryProfesor.crear(profesor);

    }

    @Override
    public void editar(Profesor profesor) {
        this.repositoryProfesor.editar(profesor);

    }

    @Override
    public void eliminar(long id) {
        this.repositoryProfesor.eliminar(id);

    }
}



