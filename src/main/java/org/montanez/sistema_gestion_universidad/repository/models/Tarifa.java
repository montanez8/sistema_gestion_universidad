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
        return String.format(
                "\nTarifa ID: %d\nCosto Crédito: %.2f\nPrograma: %s\nPeriodo: %s\n",
                id,
                costoCredito,
                programa != null ? programa.toString() : "null",
                periodo != null ? periodo.toString() : "null"
        );
    }
}
