package org.montanez.sistema_gestion_universidad.services.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryMatricula;
import org.montanez.sistema_gestion_universidad.repository.models.Matricula;
import org.montanez.sistema_gestion_universidad.services.ServiceMatricula;

import java.util.List;

public class ImpServiceMatricula implements ServiceMatricula {
    private final RepositoryMatricula repositoryMatricula;

    public ImpServiceMatricula(RepositoryMatricula repositoryMatricula) {
        this.repositoryMatricula = repositoryMatricula;
    }

    @Override
    public List<Matricula> listar() {
        return this.repositoryMatricula.listar();
    }

    @Override
    public Matricula matricula_id(long id) {
        Matricula matricula = null;
        try {
            matricula = this.repositoryMatricula.matricula_id(id);
            if (matricula == null) {
                throw new RuntimeException("La matricula no existe");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return matricula;
    }

    @Override
    public void crear(Matricula matricula) {
        this.repositoryMatricula.crear(matricula);
    }

    @Override
    public void editar(Matricula matricula) {
        this.repositoryMatricula.editar(matricula);

    }

    @Override
    public void eliminar(long id) {
        this.repositoryMatricula.eliminar(id);

    }
}
