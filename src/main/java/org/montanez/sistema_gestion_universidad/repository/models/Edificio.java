package org.montanez.sistema_gestion_universidad.repository.models;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Edificio {
    private long id;
    private String nombre;

    public Edificio(String nombre) {
        this.nombre = nombre;
    }

    // TosString con estilo
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        sb.append(red + "Edificio: " + reset).append(id)
                .append(green + " Nombre: " + reset).append(nombre);
        return sb.toString();
    }
}
