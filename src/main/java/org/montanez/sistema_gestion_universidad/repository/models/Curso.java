package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Curso {
    List<Asignatura> asignaturas;
    private long id;
    private String nombre;
    private String guiaCatedra;

    public Curso(String nombre, String guiaCatedra) {
        this.nombre = nombre;
        this.guiaCatedra = guiaCatedra;
    }

    @Override
    public String toString() {
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";

        StringBuilder sb = new StringBuilder();
        sb.append(red).append("ID: ").append(reset).append(id);
        sb.append(green).append(" Nombre: ").append(reset).append(nombre);
        sb.append(green).append(" Guia de catedra: ").append(reset).append(guiaCatedra);
        return sb.toString();
    }
}
