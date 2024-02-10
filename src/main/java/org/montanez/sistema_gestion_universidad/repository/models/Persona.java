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

    public String toString() {
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        String green = "\u001B[32m";
        String yellow = "\u001B[33m";
        String blue = "\u001B[34m";

        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append(red).append("Nombre: ").append(reset).append(nombre).append(" ").append(apellido).append("\n");
        sb.append(green).append("Tipo Documento: ").append(reset).append(tipoDocumento).append("\n");
        sb.append(yellow).append("Numero Documento: ").append(reset).append(numeroDocumento).append("\n");
        sb.append(blue).append("Fecha Nacimiento: ").append(reset).append(fechaNacimiento).append("\n");
        sb.append(red).append("Genero: ").append(reset).append(genero).append("\n");
        sb.append(green).append("Ciudad: ").append(reset).append(ciudad).append("\n");
        sb.append(yellow).append("Direccion: ").append(reset).append(direccion).append("\n");
        sb.append(blue).append("Telefono: ").append(reset).append(telefono).append("\n");
        return sb.toString();
    }


}
