package org.montanez.sistema_gestion_universidad.repository.models;


import lombok.Data;

import java.util.Date;
import java.util.List;

@Data

public class Alumno extends Persona {

    List<Matricula> matriculas;

    public Alumno(Long id, TipoDocumento tipoDocumento, String numeroDocumento, String primerNombre, String primerApellido, String ciudad, String direccion, String telefono, Date fechaNacimiento, String genero) {
        super(id, tipoDocumento, numeroDocumento, primerNombre, primerApellido, ciudad, direccion, telefono, fechaNacimiento, genero);
    }

    public Alumno(TipoDocumento tipoDocumento, String numeroDocumento, String primerNombre, String primerApellido, String ciudad, String direccion, String telefono, Date fechaNacimiento, String genero) {
        super(tipoDocumento, numeroDocumento, primerNombre, primerApellido, ciudad, direccion, telefono, fechaNacimiento, genero);
    }

    public Alumno() {

    }


    public String toString() {
        return super.toString();
    }

}
