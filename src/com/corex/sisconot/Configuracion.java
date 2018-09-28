package com.corex.sisconot;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Configuracion {
    public static String RutaDeLosDBFs;
    Properties Propiedades;

    public Configuracion() {
        Propiedades = new Properties();

        // Obtener ruta absoluta de los DBF de Sisconot
        try { Propiedades.load(new FileReader("data/config.properties")); } catch (IOException e) { e.printStackTrace(); }
        RutaDeLosDBFs = Propiedades.getProperty("Patch_DBF");
    }
}
