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

    public Periodo(String codigo, int anio, int semestre) {
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
        sb.append("\n");
        sb.append("Periodo{id=").append(id);
        sb.append(", codigo=").append(codigo);
        sb.append(", anio=").append(anio);
        sb.append(", semestre=").append(semestre);
        sb.append('}');
        return sb.toString();
    }
}
