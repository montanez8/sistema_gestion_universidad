package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Asignatura {
    private long id;
    private String nombre;
    private String codigo;
    private int creditos;
    private int cupo;
    private Profesor profesor;
    private Programa programa;
    private Curso curso;
    private Periodo periodo;


    public Asignatura(String nombre, int creditos, int cupo, Profesor profesor, Programa programa, Curso curso, Periodo periodo) {
        this.nombre = nombre;
        this.creditos = creditos;
        this.cupo = cupo;
        this.profesor = profesor;
        this.programa = programa;
        this.curso = curso;
        this.periodo = periodo;
    }

    @Override
    public String toString() {
        return "\033[1;33mAsignatura{\033[0m" + System.lineSeparator() +
                "\t\033[1;34mid=\033[0m" + id + ',' + System.lineSeparator() +
                "\t\033[1;34mnombre=\033[0m'" + nombre + '\'' + ',' + System.lineSeparator() +
                "\t\033[1;34mcodigo=\033[0m'" + codigo + '\'' + ',' + System.lineSeparator() +
                "\t\033[1;34mcreditos=\033[0m" + creditos + ',' + System.lineSeparator() +
                "\t\033[1;34mcupo=\033[0m" + cupo + ',' + System.lineSeparator() +
                "\t\033[1;34mprofesor=\033[0m" + (profesor != null ? "\033[32m" + profesor.getNombre() + "\033[0m" : "null") + ',' + System.lineSeparator() +
                "\t\033[1;34mprograma=\033[0m" + (programa != null ? "\033[32m" + programa.getNombre() + "\033[0m" : "null") + ',' + System.lineSeparator() +
                "\t\033[1;34mcurso=\033[0m" + (curso != null ? "\033[32m" + curso.getNombre() + "\033[0m" : "null") + ',' + System.lineSeparator() +
                "\t\033[1;34mperiodo=\033[0m" + (periodo != null ? "\033[32m" + periodo.getCodigo() + "\033[0m" : "null") + System.lineSeparator() +
                "\033[1;33m}\033[0m";
    }
}
