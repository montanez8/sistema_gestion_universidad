package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Matricula {
    private long id;
    private Alumno alumno;
    private Asignatura asignatura;
    private Periodo periodo;
    private Salon salon;
    private Horario horario;

    public Matricula(Alumno alumno, Asignatura asignatura, Periodo periodo, Salon salon, Horario horario) {
        this.alumno = alumno;
        this.asignatura = asignatura;
        this.periodo = periodo;
        this.salon = salon;
        this.horario = horario;


    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";

        sb.append(red + "Matricula{" + reset + "\n");
        sb.append(green + "\tid=" + reset).append(id).append(",\n");
        sb.append(green + "\talumno=" + reset).append(alumno).append(",\n");
        sb.append(green + "\tasignatura=" + reset).append(asignatura).append(",\n");
        sb.append(green + "\tperiodo=" + reset).append(periodo).append(",\n");
        sb.append(green + "\tsalon=" + reset).append(salon).append(",\n");
        sb.append(green + "\thorario=" + reset).append(horario).append("\n");
        sb.append(red + "}" + reset);

        return sb.toString();
    }
}
