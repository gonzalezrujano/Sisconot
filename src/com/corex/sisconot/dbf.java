package com.corex.sisconot;

import com.linuxense.javadbf.DBFException;
import com.linuxense.javadbf.DBFField;
import com.linuxense.javadbf.DBFReader;

import java.io.*;
import java.util.Date;

public class dbf {

    public static boolean BuscarDatos(String Cedula) {
        boolean RegistroEncontrado = false;

        InputStream inputStream  = null;
        try { inputStream = new FileInputStream("data/DATOS.DBF"); } catch (FileNotFoundException e) { e.printStackTrace(); }
        DBFReader reader = new DBFReader(inputStream);

        Object[] rowObjects;
        while( (rowObjects = reader.nextRecord()) != null) {
            if (rowObjects[0].equals(Cedula)) {
                // Guardar -> Cedula - Nombres - Fecha - y Lugar de Nacimiento - Entidad Federal
                Alumno DatosDelAlumno = new Alumno((String) rowObjects[0], (String) rowObjects[1], (Date) rowObjects[3], (String) rowObjects[4], (String) rowObjects[5]);
                RegistroEncontrado = true;
            }
        }
        return RegistroEncontrado;
    }

    public static void BuscarNotas(String Cedula) {

        InputStream inputStream  = null;
        try { inputStream = new FileInputStream("data/RECORD.DBF"); } catch (FileNotFoundException e) { e.printStackTrace(); }
        DBFReader reader = new DBFReader(inputStream);

        Object[] rowObjects;
        while( (rowObjects = reader.nextRecord()) != null) {
            if (rowObjects[0].equals(Cedula)) {
                if (rowObjects[2].equals("32012")) { Notas.recordBasica = rowObjects; }
                if (rowObjects[2].equals("31022")) { Notas.recordDiversificado = rowObjects; }
            }
        }
    }

    public static String[] BuscarPlantel(String Codigo) {
        InputStream inputStream  = null;
        try { inputStream = new FileInputStream("data/PLANTEL.DBF"); } catch (FileNotFoundException e) { e.printStackTrace(); }
        DBFReader reader = new DBFReader(inputStream);

        String[] Plantel = new String[4];

        Object[] rowObjects;
        while( (rowObjects = reader.nextRecord()) != null) {
            if (rowObjects[0].equals(Codigo)) {
                Plantel[0] = (String) rowObjects[0];
                Plantel[1] = (String) rowObjects[1];
                Plantel[2] = (String) rowObjects[2];
                Plantel[3] = (String) rowObjects[4];
            }
        }

        return Plantel;
    }

}
