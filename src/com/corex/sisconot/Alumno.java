package com.corex.sisconot;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.String.format;

public class Alumno {
    public static String Cedula = "";
    public static String Nombres = "";
    public static String Apellidos = "";
    public static Date FechaDeNacimiento;
    public static String LugarDeNacimiento = "";
    public static String EntidadFederal = "";

    public Alumno(String newCedula, String newNombres, Date newFechaNacimiento, String newLugarNacimiento, String newEntidadFederal) {
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Cedula = newCedula;
        SepararNombresYApellidos(newNombres);
        FechaDeNacimiento = newFechaNacimiento;
        LugarDeNacimiento = newLugarNacimiento;
        EntidadFederal = newEntidadFederal;
    }

    private void SepararNombresYApellidos(String newNombres) {
        for (int i=0; i<newNombres.length(); i++) {
            if (newNombres.charAt(i) == ',') {
                Apellidos = newNombres.substring(0, i);
                Nombres = newNombres.substring(i+1, newNombres.length());
            }
        }
    }

    public static void VaciarDatos() {
        Cedula = "";
        Nombres = "";
        FechaDeNacimiento = null;
        LugarDeNacimiento = "";
        EntidadFederal = "";
    }
}
