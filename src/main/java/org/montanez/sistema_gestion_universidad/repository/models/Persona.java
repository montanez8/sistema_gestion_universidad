package org.montanez.sistema_gestion_universidad.repository.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public abstract class Persona {
    private Long id;
    private TipoDocumento tipoDocumento;
    private String numeroDocumento;
    private String nombre;
    private String apellido;
    private String ciudad;
    private String direccion;
    private String telefono;
    private Date fechaNacimiento;
    private String genero;


    public Persona(TipoDocumento tipoDocumento, String numeroDocumento, String nombre, String primerApellido, String ciudad, String direccion, String telefono, Date fechaNacimiento, String genero) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombre = nombre;
        this.apellido = primerApellido;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    @Override
    public String toString() {
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";


        String format = "| %-3s | %-20s | %-10s | %-15s | %-15s | %-5s | %-15s | %-15s |        %-15s%n";
        return String.format(format, red + "ID", green + "Nombre", green + "Tipo Documento", green + "Numero Documento", green + "Fecha Nacimiento", green + "Genero", green + "Ciudad", green + "Direccion", green + "Telefono") + reset +
                String.format(format, id, nombre + " " + apellido, tipoDocumento, numeroDocumento, fechaNacimiento, genero, ciudad, direccion, telefono, reset);
    }


}
