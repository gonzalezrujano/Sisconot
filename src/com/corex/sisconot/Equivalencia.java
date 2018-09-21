package com.corex.sisconot;

import java.util.ArrayList;

public class Equivalencia {

    private void GuardarMateria(String NombreDelArea, ArrayList<String[]> Area) {
        int PosicionDelArea = 0;
        switch (NombreDelArea) {
            case "LCC":
                PosicionDelArea = 0;
                break;
            case "MAT":
                PosicionDelArea = 1;
                break;
            case "MTC":
                PosicionDelArea = 2;
                break;
            case "CN":
                PosicionDelArea = 3;
                break;
        }
        Notas.Areas.set(PosicionDelArea, Area);
    }

    private ArrayList<String[]> TransferirEscalaDeNotas(ArrayList<String[]> MateriaEnPeriodos) {
        for (int i=0; i<MateriaEnPeriodos.size(); i++) {
            String[] DatosDelPeriodo = MateriaEnPeriodos.get(i);
            int NotaDelPeriodo = Integer.parseInt(DatosDelPeriodo[0]);

            if (NotaDelPeriodo <= 4) { NotaDelPeriodo = 1; }
            else if (NotaDelPeriodo > 4 && NotaDelPeriodo < 10) { NotaDelPeriodo = 2; }
            else if (NotaDelPeriodo > 9 && NotaDelPeriodo < 13) { NotaDelPeriodo = 3; }
            else if (NotaDelPeriodo > 12 && NotaDelPeriodo < 17) { NotaDelPeriodo = 4; }
            else if (NotaDelPeriodo > 16) { NotaDelPeriodo = 5; }

            DatosDelPeriodo[0] = NotaDelPeriodo+"";
            MateriaEnPeriodos.set(i, DatosDelPeriodo);
        }
        return MateriaEnPeriodos;
    }

    private String[] TomarDatosDelSemestre(String[] DatosDelSemestre) {
        String[] DatosDeLaEquivalencia = new String[4];

        // Nota del Periodo
        DatosDeLaEquivalencia[0] = DatosDelSemestre[0];
        // Tipo de Evaluacion del Periodo
        DatosDeLaEquivalencia[1] = DatosDelSemestre[1];
        // Fecha de aprobacion del Periodo
        DatosDeLaEquivalencia[2] = DatosDelSemestre[2];
        // Codigo del Plantel
        DatosDeLaEquivalencia[3] = DatosDelSemestre[3];

        return DatosDeLaEquivalencia;
    }

    private String[] PromediarSemestres(String[] SemestreIzquierdo, String[] SemestreDerecho) {
        String[] DatosDeLaEquivalencia = new String[4];
        // Nota del Periodo
        DatosDeLaEquivalencia[0] = (int) Math.ceil((Double.parseDouble(SemestreIzquierdo[0]) + Double.parseDouble(SemestreDerecho[0])) / 2)+"";
        // Tipo de Evaluacion del Periodo
        DatosDeLaEquivalencia[1] = SemestreDerecho[1];
        // Fecha de aprobacion del Periodo
        DatosDeLaEquivalencia[2] = SemestreDerecho[2];
        // Codigo del Plantel
        DatosDeLaEquivalencia[3] = SemestreDerecho[3];

        return DatosDeLaEquivalencia;
    }

    private ArrayList<String[]> TransferirMateriaSinDependencias(String Mencion, ArrayList<String[]> DatosDeLaMateria) {
        ArrayList<String[]> MateriaEnPeriodos = new ArrayList<String[]>();

        if (Mencion.equals("Basica")) {
            // Llenar sin datos
            for (int i=0; i<3; i++) {
                MateriaEnPeriodos.add(new String[]{"0", "", "", ""});
            }
            // Llenar con equivalencia
            int Inicio = 0; //Indice del Primer Semestre
            int Fin = 1;    //Indice del Segundo Semestre
            for (int i=0; i<3; i++) {
                String[] DatosDeLaEquivalencia = PromediarSemestres(DatosDeLaMateria.get(Inicio), DatosDeLaMateria.get(Fin));
                MateriaEnPeriodos.set(i, DatosDeLaEquivalencia);
                Inicio += 2;
                Fin += 2;
            }
        } else if (Mencion.equals("Diversificado")) {
            // Llenar sin datos
            for (int i=0; i<3; i++) {
                MateriaEnPeriodos.add(new String[]{"0", "", "", ""});
            }
            // Llenar con equivalencia
            String[] DatosDeLaEquivalencia;
            // Cuarto Periodo
            DatosDeLaEquivalencia = PromediarSemestres(DatosDeLaMateria.get(0), DatosDeLaMateria.get(1));
            MateriaEnPeriodos.set(0, DatosDeLaEquivalencia);
            // Quinto Periodo
            DatosDeLaEquivalencia = TomarDatosDelSemestre(DatosDeLaMateria.get(2));
            MateriaEnPeriodos.set(1, DatosDeLaEquivalencia);
            // Sexto Periodo
            DatosDeLaEquivalencia = TomarDatosDelSemestre(DatosDeLaMateria.get(3));
            MateriaEnPeriodos.set(2, DatosDeLaEquivalencia);
        }
        return MateriaEnPeriodos;
    }

    private String[] EvaluarDatosDeAprobacion(ArrayList<String[]> MateriasAEvaluar) {
        String[] DatosDeAprobacion = new String[4];
        return DatosDeAprobacion;
    }

    private String[] TransferirMateriaConDependencia(ArrayList<ArrayList<String[]>> Materias) {
        String[] Area = new String[4];
        ArrayList<String[]> MateriasAEvaluar = new ArrayList<>();

        //                                              ABSTRAER EN BUCLE
        if (Materias.size() == 2) {
            ArrayList<String[]> Materia1 = Materias.get(0);
            ArrayList<String[]> Materia2 = Materias.get(1);

            String[] PromedioVertical1 = PromediarSemestres(Materia1.get(0), Materia2.get(0));
            String[] PromedioVertical2 = PromediarSemestres(Materia1.get(1), Materia2.get(1));

            MateriasAEvaluar.add(PromedioVertical1);
            MateriasAEvaluar.add(PromedioVertical2);

            Area = EvaluarDatosDeAprobacion(MateriasAEvaluar);

            Area[0] = (int) Math.ceil((Double.parseDouble(PromedioVertical1[0]) + Double.parseDouble(PromedioVertical2[0])) / 2)+"";

            System.out.println("Que datos vienen: ");
        } else if (Materias.size() == 3) {
            //
        }
        return Area;
    }

    private String[] SepararMateria(String Campo) {
        String[] Datos = {"0", "", "", ""};

        String Nota = Campo.substring(0, 2);
        if ((!Nota.equals("")) || (!Nota.equals(" ")) || (Nota != null) || (!Nota.equals("  "))) {
            Datos[0] = Nota;
            Datos[1] = Campo.substring(2, 3);
            if (Datos[1].equals("E")) { Datos[1] = "X"; }
            Datos[2] = Campo.substring(3, 9);
            Datos[3] = Campo.substring(9, Campo.length());
        }
        return Datos;
    }

    private ArrayList<String[]> ExtraerMateriaDeUnPeriodo(String record) {
        int inicio = 0;
        int fin = 12;
        ArrayList<String[]> Materia = new ArrayList<String[]>();
        for (int i=0; i<2; i++) {
            Materia.add(SepararMateria(record.substring(inicio, fin)));
            inicio += 12;
            fin += 12;
        }
        return Materia;
    }

    private ArrayList<String[]> ExtraerMateria(String mencion, String record) {
        // Extraer materia del campo DBF
        int NroSemestres = 0;
        if (mencion.equals("Basica")) {
            NroSemestres = 6;
        } else if (mencion.equals("Diversificado")) {
            NroSemestres = 4;
        }

        int inicio = 0;
        int fin = 12;
        ArrayList<String[]> Materia = new ArrayList<String[]>();
        for (int i=0; i<NroSemestres; i++) {
            Materia.add(SepararMateria(record.substring(inicio, fin)));
            inicio += 12;
            fin += 12;
        }
        return Materia;
    }

    private void HacerEquivalencia(String mencion, Object[] record) {
        int PosicionEnElRecord = 0;
        Notas.LlenarAreasSinDatos();

        // Castellano -> LCC (Todos los periodos)
        ArrayList<String[]> Castellano = ExtraerMateria(mencion, record[3].toString());
        ArrayList<String[]> LCC = TransferirMateriaSinDependencias(mencion, Castellano);
        LCC = TransferirEscalaDeNotas(LCC);
        GuardarMateria("LCC", LCC);

        // Matematica -> MAT (Todos los periodos)
        PosicionEnElRecord = ((mencion == "Basica")?5:4);
        ArrayList<String[]> Matematica = ExtraerMateria(mencion, record[PosicionEnElRecord].toString());
        ArrayList<String[]> MAT = TransferirMateriaSinDependencias(mencion, Matematica);
        MAT = TransferirEscalaDeNotas(MAT);
        GuardarMateria("MAT", MAT);

        // Historias -> MTC
        if (mencion == "Basica") {
            ArrayList<ArrayList<String[]>> Materias = null;

            // Primer Periodo
            Materias = new ArrayList<>();
            ArrayList<String[]> Ciudadana = ExtraerMateriaDeUnPeriodo(record[12].toString());
            ArrayList<String[]> GeografiaGeneral = ExtraerMateriaDeUnPeriodo(record[9].toString());
            Materias.add(Ciudadana);
            Materias.add(GeografiaGeneral);
            String[] MTC = TransferirMateriaConDependencia(Materias);
        }


        /*if (mencion.equals("Diversificado")) {
            ArrayList<String[]> LLCC = Notas.Areas.get(0);
            String[] Cuarto = LLCC.get(0);
            String[] Quinto = LLCC.get(1);
            String[] Sexto = LLCC.get(2);
            System.out.println("Nota LCC Diversificado");
            System.out.println(Cuarto[0]);
            System.out.println(Quinto[0]);
            System.out.println(Sexto[0]);
        }*/

    }

    public Equivalencia() {
        //PROBAR RECORD CON HUECOS
        //PROBAR NOTAS BAJAS
        dbf.BuscarNotas("V-13120813");

        if (Notas.recordBasica != null) {
            System.out.println("llamando a equivalencia de Basica");
            HacerEquivalencia("Basica", Notas.recordBasica);
        }
        if (Notas.recordDiversificado != null) {
            System.out.println("llamando a equivalencia de Diversificado");
            HacerEquivalencia("Diversificado", Notas.recordDiversificado);
        }
    }
}
