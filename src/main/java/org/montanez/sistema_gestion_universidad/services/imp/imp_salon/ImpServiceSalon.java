package org.montanez.sistema_gestion_universidad.services.imp.imp_salon;

import org.montanez.sistema_gestion_universidad.repository.RepositorySalon;
import org.montanez.sistema_gestion_universidad.repository.models.Salon;
import org.montanez.sistema_gestion_universidad.services.ServiceSalon;

import java.util.List;

public class ImpServiceSalon implements ServiceSalon {
    private final RepositorySalon repositorySalon;

    public ImpServiceSalon(RepositorySalon repositorySalon) {
        this.repositorySalon = repositorySalon;
    }

    @Override
    public List<Salon> listar() {
        return this.repositorySalon.listar();
    }

    @Override
    public Salon salon_id(long id) {
        Salon salon = this.repositorySalon.salon_id(id);
        if (salon == null) {
            throw new RuntimeException("El salon no existe");
        }
        return salon;
    }

    @Override
    public void crear(Salon salon) {
        this.repositorySalon.crear(salon);

    }

    @Override
    public void editar(Salon salon) {
        this.repositorySalon.editar(salon);
    }

    @Override
    public void eliminar(Salon salon) {
        this.repositorySalon.eliminar(salon);
    }
}