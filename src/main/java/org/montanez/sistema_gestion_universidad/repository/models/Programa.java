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
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Programa{id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", nivel=").append(nivel);
        sb.append('}');
        return sb.toString();
    }
}
