package org.montanez.sistema_gestion_universidad.services.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryCurso;
import org.montanez.sistema_gestion_universidad.repository.models.Curso;
import org.montanez.sistema_gestion_universidad.services.ServiceCurso;

import java.util.List;

public class ImpServiceCurso implements ServiceCurso {
    private final RepositoryCurso repositoryCurso;

    public ImpServiceCurso(RepositoryCurso repositoryCurso) {
        this.repositoryCurso = repositoryCurso;
    }

    @Override
    public List<Curso> listar() {
        return this.repositoryCurso.listar();
    }

    @Override
    public Curso curso_id(long id) throws RuntimeException {
        Curso curso = this.repositoryCurso.curso_id(id);
        if (curso == null) {
            try {
                throw new RuntimeException("El curso no existe");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return curso;
    }

    @Override
    public void crear(Curso curso) {
        this.repositoryCurso.crear(curso);

    }

    @Override
    public void editar(Curso curso) {
        this.repositoryCurso.editar(curso);

    }

    @Override
    public void eliminar(long id) {
        this.repositoryCurso.eliminar(id);

    }
}
