package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;

import java.util.List;

@Data
public class Curso {
    List<Asignatura> asignaturas;
    private long id;
    private String nombre;
    private String guiaCatedra;


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n").append("Curso: ")
                .append(nombre).append("\n")
                .append("Guia de catedra: ").append(guiaCatedra).append("\n");
        return sb.toString();
    }
}
