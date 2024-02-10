package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;

@Data
public class Asignatura {
    private long id;
    private String nombre;
    private String codigo;
    private int creditos;
    private int cupo;
    private Profesor profesor;
    private Programa programa;
    private Curso curso;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n")
                .append("Asignatura id = ").append(id).append("\n")
                .append("nombre = ").append(nombre).append("\n")
                .append("creditos = ").append(creditos).append("\n")
                .append("cupo = ").append(cupo);
        return sb.toString();
    }
}
