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
    private Asignatura asignatura;
    private Salon salon;
    private Periodo periodo;

    public Horario(DayOfWeek dia, LocalTime horaInicio, LocalTime horaFin) {
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Horario{id=").append(id);
        sb.append(", dia=").append(dia);
        sb.append(", horaInicio=").append(horaInicio);
        sb.append(", horaFin=").append(horaFin);
        sb.append(", asignatura=").append(asignatura);
        sb.append(", salon=").append(salon);
        sb.append(", periodo=").append(periodo);
        sb.append('}');
        return sb.toString();

    }
}
