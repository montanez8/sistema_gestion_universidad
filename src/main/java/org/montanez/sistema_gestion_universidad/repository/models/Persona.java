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
    private String primerNombre;
    private String primerApellido;
    private String ciudad;
    private String direccion;
    private String telefono;
    private Date fechaNacimiento;
    private String genero;


    public Persona(TipoDocumento tipoDocumento, String numeroDocumento, String primerNombre, String primerApellido, String ciudad, String direccion, String telefono, Date fechaNacimiento, String genero) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.genero = genero;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Nombre: ").append(primerNombre).append(" ").append(primerApellido).append("\n");
        sb.append("Tipo Documento: ").append(tipoDocumento).append("\n");
        sb.append("Numero Documento: ").append(numeroDocumento).append("\n");
        sb.append("Fecha Nacimiento: ").append(fechaNacimiento).append("\n");
        sb.append("Genero: ").append(genero).append("\n");
        sb.append("Ciudad: ").append(ciudad).append("\n");
        sb.append("Direccion: ").append(direccion).append("\n");
        sb.append("Telefono: ").append(telefono).append("\n");
        return sb.toString();
    }


}
