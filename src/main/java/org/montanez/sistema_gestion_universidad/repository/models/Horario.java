package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class Horario {
    long id;
    private DayOfWeek dia;
    private LocalTime horaInicio;
    private LocalTime horaFin;


    public Horario(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        sb.append(red + "Id: " + reset).append(id).append(" | ");
        sb.append(green + "Dia: " + reset).append(dia).append(" | ");
        sb.append(green + "Hora de inicio: " + reset).append(horaInicio).append(" | ");
        sb.append(green + "Hora de fin: " + reset).append(horaFin);

        return sb.toString();
    }
}
