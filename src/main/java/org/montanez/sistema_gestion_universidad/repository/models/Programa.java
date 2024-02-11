package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Programa {
    private long id;
    private String nombre;
    private String nivel;


    public Programa(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    @Override
    public String toString() {
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";

        StringBuilder sb = new StringBuilder();
        sb.append(red).append(" Programa id: ").append(reset).append(id);
        sb.append(green).append(" Nombre: ").append(reset).append(nombre);
        sb.append(green).append(" Nivel: ").append(reset).append(nivel);
        return sb.toString();
    }
}
