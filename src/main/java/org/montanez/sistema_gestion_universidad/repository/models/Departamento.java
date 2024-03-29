package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Departamento {
    private long id;
    private String nombre;
    private List<Profesor> profesores;

    public Departamento(String nombre) {
        this.nombre = nombre;
    }

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";

        sb.append(red + "id: " + reset).append(id).append(" | ");
        sb.append(green + "Nombre: " + reset).append(nombre);

        return sb.toString();
    }
}
