package org.montanez.sistema_gestion_universidad.services.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryEdificio;
import org.montanez.sistema_gestion_universidad.repository.models.Edificio;
import org.montanez.sistema_gestion_universidad.services.ServiceEdificio;

import java.util.List;

public class ImpServiceEdificio implements ServiceEdificio {

    private final RepositoryEdificio repositoryEdificio;

    public ImpServiceEdificio(RepositoryEdificio repositoryEdificio) {
        this.repositoryEdificio = repositoryEdificio;
    }

    @Override
    public List<Edificio> listar() {
        return this.repositoryEdificio.listar();
    }

    @Override
    public Edificio edificio_id(long id) {
        Edificio edificio = this.repositoryEdificio.edificio_id(id);
        if (edificio == null) {
            try {
                throw new RuntimeException("El edificio no existe");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return edificio;
    }

    @Override
    public void crear(Edificio edificio) {
        this.repositoryEdificio.crear(edificio);
    }

    @Override
    public void editar(Edificio edificio) {
        this.repositoryEdificio.editar(edificio);
    }

    @Override
    public void eliminar(Edificio edificio) {
        this.repositoryEdificio.eliminar(edificio);
    }
}
