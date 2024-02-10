package org.montanez.sistema_gestion_universidad.repository.models;


// Enum definition
public enum TipoDocumento {
    CEDULA, CEDULA_EXTRANJERIA, PASAPORTE, TARJETA_IDENTIDAD;

    public static TipoDocumento setEnumTipo(String text) {
        for (TipoDocumento baseDatos : TipoDocumento.values()) {
            if (baseDatos.name().equalsIgnoreCase(text)) {
                return baseDatos;
            }
        }
        return null;
    }
}

