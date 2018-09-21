package com.corex.sisconot;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Equivalencia {

    /*private Object SepararMateria() {
        //
    }*/

    private ArrayList ExtraerMateria(String mencion, Object record) {
        // Extraer materia del campo DBF
        int NroSemestres = 0;
        if (mencion.equals("Basica")) { NroSemestres = 6; } else if (mencion.equals("Diversificado")) { NroSemestres = 4; }

        int inicio = 0;
        int fin = 12;
        ArrayList Materia = null;
        for (int i=0; i<NroSemestres; i++) {
            //Materia.add(SepararMateria());
        }
        return Materia;
    }

    private void HacerEquivalencia(String mencion, Object[] record) {
        // Castellano -> LCC (Todos los periodos)
        ArrayList Castellano = ExtraerMateria(mencion, record[3]);
        //Array LCC = Transferir('LCC', mencion, Castellano);
    }

    public Equivalencia() {
        dbf.BuscarNotas("V-13120813");

        if (Notas.recordBasica != null) {
            System.out.println("llamando a equivalencia de Basica");
            HacerEquivalencia("Basica", Notas.recordBasica);
        } else if (Notas.recordDiversificado != null) {
            System.out.println("llamando a equivalencia de Diversificado");
            HacerEquivalencia("Diversificado", Notas.recordDiversificado);
        }
    }
}
