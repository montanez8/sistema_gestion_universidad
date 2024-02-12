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
        StringBuilder sb = new StringBuilder();
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        sb.append(red + "Id: " + reset).append(id).append(" | ");
        sb.append(red + "Tipo de documento: " + reset).append(tipoDocumento).append(" | ");
        sb.append(red + "Numero de documento: " + reset).append(numeroDocumento).append(" | ");
        sb.append(green + "Nombre: " + reset).append(nombre).append(" | ");
        sb.append(green + "Apellido: " + reset).append(apellido).append(" | ");
        sb.append(green + "Ciudad: " + reset).append(ciudad).append(" | ");
        sb.append(green + "Direccion: " + reset).append(direccion).append(" | ");
        sb.append(green + "Telefono: " + reset).append(telefono).append(" | ");
        sb.append(green + "Fecha de nacimiento: " + reset).append(fechaNacimiento).append(" | ");
        sb.append(green + "Genero: " + reset).append(genero);

        return sb.toString();
    }


}
