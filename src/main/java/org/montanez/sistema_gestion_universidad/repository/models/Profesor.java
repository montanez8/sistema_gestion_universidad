package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;

@Data
public class Profesor extends Persona {
    private Departamento departamento;

    @Override
    public String toString() {
        return super.toString();
    }
}
      