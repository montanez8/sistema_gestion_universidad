package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Tarifa {
    private long id;
    private double costoCredito;
    private Programa programa;
    private Periodo periodo;

    public Tarifa(double costoCredito) {
        this.costoCredito = costoCredito;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTarifa id=").append(id);
        sb.append("\ncostoCredito=").append(costoCredito);
        sb.append(" ").append(programa != null ? programa.toString() : "null");
        sb.append(" ").append(periodo != null ? periodo.toString() : "null");
        sb.append('\n');
        return sb.toString();
    }
}
