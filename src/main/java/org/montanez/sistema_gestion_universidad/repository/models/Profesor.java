package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class Profesor extends Persona {
    private Departamento departamento;

    public Profesor(TipoDocumento tipoDocumento, String numeroDocumento, String nombre, String primerApellido, String ciudad, String direccion, String telefono, Date fechaNacimiento, String genero, Departamento departamento) {
        super(tipoDocumento, numeroDocumento, nombre, primerApellido, ciudad, direccion, telefono, fechaNacimiento, genero);
        this.departamento = departamento;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        sb.append(red + "Profesor: " + reset).append(super.toString())
                .append(green + " Departamento: " + reset).append(departamento);
        sb.append(red + "Profesor: " + reset).append(super.toString())
                .append(green + " \nDepartamento: " + reset).append(departamento);
        sb.append("\n");
        return sb.toString();
    }


}
      