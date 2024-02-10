package org.montanez.sistema_gestion_universidad.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class Configuracion {
    private static Properties propiedades ;

    static {
        propiedades = new Properties();
        cargarPropiedades();
    }

    private static void cargarPropiedades() {
        try(FileInputStream entrada =new FileInputStream("configuracion.properties")) {
            propiedades.load(entrada);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String obtenerPropiedad(String nombrePropiedad) {
        return propiedades.getProperty(nombrePropiedad);
    }
}
