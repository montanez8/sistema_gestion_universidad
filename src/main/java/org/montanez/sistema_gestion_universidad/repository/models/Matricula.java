package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;

@Data
public class Matricula {
    private long id;
    private Alumno alumno;
    private Asignatura asignatura;
    private Periodo periodo;
    private Salon salon;
    private Horario horario;
}
