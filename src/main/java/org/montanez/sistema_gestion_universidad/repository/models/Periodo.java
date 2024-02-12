package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Periodo {
    private long id;
    private String codigo;
    private int anio;
    private int semestre;

    public Periodo(int anio, int semestre) {
        this.codigo = codigo;
        this.anio = anio;
        this.semestre = semestre;
    }

    public Periodo(long id, String codigo, int anio, int semestre) {
        this.id = id;
        this.codigo = codigo;
        this.anio = anio;
        this.semestre = semestre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";

        sb.append(red + "id: " + reset).append(id).append(" | ");
        sb.append(green + "codigo: " + reset).append(codigo).append(" | ");
        sb.append(red + "anio: " + reset).append(anio).append(" | ");
        sb.append(green + "semestre: " + reset).append(semestre);

        return sb.toString();
    }
}
