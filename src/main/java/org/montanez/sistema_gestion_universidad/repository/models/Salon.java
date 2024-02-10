package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Salon {
    private long id;
    private int capacidad;
    private int piso;
    private String numero;
    private String edificio;


    public Salon(int capacidad, int piso, String numero, String edificio) {
        this.capacidad = capacidad;
        this.piso = piso;
        this.numero = numero;
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
                .append("Salon: ").append(id)
                .append(" Capacidad: ").append(capacidad)
                .append(" Piso: ").append(piso)
                .append(" Numero: ").append(numero)
                .append(" Edificio: ").append(edificio);
        return sb.toString();
    }
}
