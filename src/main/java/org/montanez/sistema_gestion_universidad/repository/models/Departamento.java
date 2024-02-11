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

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ").append(id).append(" Nombre: ").append(nombre);
        return sb.toString();
    }
}
