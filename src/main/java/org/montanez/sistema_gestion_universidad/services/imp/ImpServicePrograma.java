package org.montanez.sistema_gestion_universidad.services.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryPrograma;
import org.montanez.sistema_gestion_universidad.repository.models.Programa;
import org.montanez.sistema_gestion_universidad.services.ServicePrograma;

import java.util.List;

public class ImpServicePrograma implements ServicePrograma {
    private final RepositoryPrograma repositoryPrograma;

    public ImpServicePrograma(RepositoryPrograma repositoryPrograma) {
        this.repositoryPrograma = repositoryPrograma;
    }

    @Override
    public List<Programa> listar() {
        return this.repositoryPrograma.listar();
    }

    @Override
    public Programa programa_id(long id) {
        return this.repositoryPrograma.programa_id(id);
    }

    @Override
    public void crear(Programa programa) {
        this.repositoryPrograma.crear(programa);

    }

    @Override
    public void editar(Programa programa) {
        this.repositoryPrograma.editar(programa);

    }

    @Override
    public void eliminar(Programa programa) {
        this.repositoryPrograma.eliminar(programa);

    }
}
