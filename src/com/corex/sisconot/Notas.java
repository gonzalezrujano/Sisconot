package com.corex.sisconot;

import java.util.ArrayList;

public class Notas {
    public static Object[] recordBasica = null;
    public static Object[] recordDiversificado = null;
    public static ArrayList<ArrayList<String[]>> Areas = null;

    public static void LlenarAreasSinDatos() {
        Areas = new ArrayList<>();
        for (int i=0; i<4; i++) {
            String[] Vacio = {"0", "", "", ""};
            ArrayList<String[]> Area = new ArrayList<>();

            for (int j=0; j<6; j++) {
                Area.add(Vacio);
            }
            Areas.add(Area);
        }
    }

    public static void VaciarDatos() {
        LlenarAreasSinDatos();
        recordBasica = null;
        recordDiversificado = null;
    }
}
