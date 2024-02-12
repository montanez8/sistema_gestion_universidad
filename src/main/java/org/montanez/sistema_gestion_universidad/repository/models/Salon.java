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
    private Edificio edificio;


    public Salon(int capacidad, int piso, String numero, Edificio edificio) {
        this.capacidad = capacidad;
        this.piso = piso;
        this.numero = numero;
        this.edificio = edificio;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";


        sb.append(red + "Salon: " + reset).append(id)
                .append(green + " Capacidad: " + reset).append(capacidad)
                .append(green + " Piso: " + reset).append(piso)
                .append(green + " Numero: " + reset).append(numero)
                .append(green + " Edificio: " + reset).append(edificio);
        return sb.toString();
    }
}
