package com.corex.sisconot;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;

public class Equivalencia {

    //------------------------------------Guardar Datos---------------------------------------------------------------//

    private void GuardarMateria(String Mencion, String NombreDelArea, ArrayList<String[]> AreaAGuardar) {
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
        ArrayList<String[]> Area = Notas.Areas.get(PosicionDelArea);
        int PosicionDelPeriodo = 0;
        if (!Mencion.equals("Basica")) {
            PosicionDelPeriodo = 3;
        }
        for (int i=0; i<3; i++) {
            Area.set(i+PosicionDelPeriodo, AreaAGuardar.get(i));
        }
        Notas.Areas.set(PosicionDelArea, Area);
    }

    //------------------------------------Convertir Escala------------------------------------------------------------//

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

    //------------------------------------Utilidades------------------------------------------------------------------//

    private String[] SepararMateria(String Campo) {
        String[] Datos = {"0", "", "", ""};

        String Nota = Campo.substring(0, 2);
        if ((!Nota.equals("")) || (!Nota.equals(" ")) || (Nota != null) || (!Nota.equals("  ")) || (!Nota.equals("P "))) {
            Datos[0] = Nota;
            Datos[1] = Campo.substring(2, 3);
            if (Datos[1].equals("E")) { Datos[1] = "X"; }
            Datos[2] = Campo.substring(3, 9);
            Datos[3] = Campo.substring(9, Campo.length());
        }
        return Datos;
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

    private String[] EvaluarDatosDeAprobacion(ArrayList<String[]> MateriasAEvaluar) {
        String[] DatosDeAprobacion = new String[] {"", "", ""};

        int Año = 0, Mes = 0, TipoDeEvaluacion = 0, PosicionDeLosDatosDeAprobacion = 0;
        for (int i=0; i<MateriasAEvaluar.size(); i++) {
            String[] MateriaAEvaluar = MateriasAEvaluar.get(i);

            if (!MateriaAEvaluar[2].equals("")) {
                boolean EstosSonLosDatosDeAprobacion = false;
                int AñoAEvaluar = Integer.parseInt(MateriaAEvaluar[2].substring(2, MateriaAEvaluar.length));
                int MesAEvaluar = Integer.parseInt(MateriaAEvaluar[2].substring(0, 2));
                int TipoDeAprobacionAEvaluar = 0;
                switch (MateriaAEvaluar[1]) {
                    case "F":
                        TipoDeAprobacionAEvaluar = 1;
                        break;
                    case "X":
                        TipoDeAprobacionAEvaluar = 2;
                        break;
                    case "R":
                        TipoDeAprobacionAEvaluar = 3;
                }

                if (AñoAEvaluar > Año) {
                    EstosSonLosDatosDeAprobacion = true;
                } else if (AñoAEvaluar == Año && MesAEvaluar > Mes) {
                    EstosSonLosDatosDeAprobacion = true;
                } else if (MesAEvaluar == Mes && TipoDeAprobacionAEvaluar > TipoDeEvaluacion) {
                    EstosSonLosDatosDeAprobacion = true;
                }

                if (EstosSonLosDatosDeAprobacion) {
                    PosicionDeLosDatosDeAprobacion = i;
                    Año = AñoAEvaluar;
                    Mes = MesAEvaluar;
                    TipoDeEvaluacion = TipoDeAprobacionAEvaluar;
                }
            } else {
                continue;
            }

        }
        return DatosDeAprobacion;
    }

    private String PromedioVerticalDeSemestres(ArrayList<ArrayList<String[]>> Materias) {
        // MALO
        if (Materias.size() == 2) {
            ArrayList<String[]> Materia1 = Materias.get(0);
            ArrayList<String[]> Materia2 = Materias.get(1);

            String[] Notas1_1 = Materia1.get(0);
            String[] Notas2_1 = Materia2.get(0);
            int Vertical1 = (Integer.parseInt(Notas1_1[0]) + Integer.parseInt(Notas2_1[0])) / 2;
            String[] Notas1_2 = Materia1.get(1);
            String[] Notas2_2 = Materia2.get(1);
            int Vertical2 = (Integer.parseInt(Notas1_1[0]) + Integer.parseInt(Notas2_1[0])) / 2;

            return ((Vertical1 + Vertical2) / 2)+"";
        } else if (Materias.size() == 3) {
            ArrayList<String[]> Materia1 = Materias.get(0);
            ArrayList<String[]> Materia2 = Materias.get(1);
            ArrayList<String[]> Materia3 = Materias.get(2);

            String[] Notas1_1 = Materia1.get(0);
            String[] Notas2_1 = Materia2.get(0);
            String[] Notas3_1 = Materia3.get(0);
            int Vertical1 = (Integer.parseInt(Notas1_1[0]) + Integer.parseInt(Notas2_1[0]) + Integer.parseInt(Notas3_1[0])) / 3;
            String[] Notas1_2 = Materia1.get(1);
            String[] Notas2_2 = Materia2.get(1);
            String[] Notas3_2 = Materia3.get(1);
            int Vertical2 = (Integer.parseInt(Notas1_2[0]) + Integer.parseInt(Notas2_2[0]) + Integer.parseInt(Notas3_2[0])) / 3;

            return ((Vertical1 + Vertical2) / 2)+"";
        } else if (Materias.size() == 4) {
            ArrayList<String[]> Materia1 = Materias.get(0);
            ArrayList<String[]> Materia2 = Materias.get(1);
            ArrayList<String[]> Materia3 = Materias.get(2);
            ArrayList<String[]> Materia4 = Materias.get(3);

            String[] Notas1_1 = Materia1.get(0);
            String[] Notas2_1 = Materia2.get(0);
            String[] Notas3_1 = Materia3.get(0);
            String[] Notas4_1 = Materia4.get(0);
            int Vertical1 = (Integer.parseInt(Notas1_1[0]) + Integer.parseInt(Notas2_1[0]) + Integer.parseInt(Notas3_1[0]) + Integer.parseInt(Notas4_1[0])) / 4;
            String[] Notas1_2 = Materia1.get(1);
            String[] Notas2_2 = Materia2.get(1);
            String[] Notas3_2 = Materia3.get(1);
            String[] Notas4_2 = Materia4.get(1);
            int Vertical2 = (Integer.parseInt(Notas1_2[0]) + Integer.parseInt(Notas2_2[0]) + Integer.parseInt(Notas3_2[0]) + Integer.parseInt(Notas4_2[0])) / 4;

            return ((Vertical1 + Vertical2) / 2)+"";
        }
        return null;
    }

    //------------------------------------Transferencias--------------------------------------------------------------//

    private ArrayList<String[]> TransferirMateriaSinDependencias(String Mencion, ArrayList<String[]> DatosDeLaMateria) {
        ArrayList<String[]> MateriaEnPeriodos = new ArrayList<String[]>();

        for (int i=0; i<3; i++) { MateriaEnPeriodos.add(new String[]{"0", "", "", ""}); }

        if (Mencion.equals("Basica")) {
            int Inicio = 0, Fin = 1;
            for (int i=0; i<3; i++) {
                String[] DatosDeLaEquivalencia = PromediarSemestres(DatosDeLaMateria.get(Inicio), DatosDeLaMateria.get(Fin));
                MateriaEnPeriodos.set(i, DatosDeLaEquivalencia);
                Inicio += 2;
                Fin += 2;
            }
        } else if (Mencion.equals("Diversificado")) {
            String[] DatosDeLaEquivalencia;
            for (int i=0; i<3; i++) {
                if (i == 0) { DatosDeLaEquivalencia = PromediarSemestres(DatosDeLaMateria.get(i), DatosDeLaMateria.get(i+1)); }
                else { DatosDeLaEquivalencia = TomarDatosDelSemestre(DatosDeLaMateria.get(i+1)); }
                MateriaEnPeriodos.set(i, DatosDeLaEquivalencia);
            }
        }
        return MateriaEnPeriodos;
    }

    private String[] TransferirMateriaConDependencia(ArrayList<ArrayList<String[]>> Materias) {
        String[] Area = new String[4];
        Area[0] = PromedioVerticalDeSemestres(Materias);
        ArrayList<String[]> DatosAEvaluar = new ArrayList<>();
        if (Materias.size() == 1) {
            ArrayList<String[]> Materia = Materias.get(0);
            return PromediarSemestres(Materia.get(0), Materia.get(1));
        } else if (Materias.size() == 2) {
            ArrayList<String[]> Materia1 = Materias.get(0);
            ArrayList<String[]> Materia2 = Materias.get(1);

            DatosAEvaluar.add(Materia1.get(1));
            DatosAEvaluar.add(Materia2.get(1));
        } else if (Materias.size() == 3) {
            ArrayList<String[]> Materia1 = Materias.get(0);
            ArrayList<String[]> Materia2 = Materias.get(1);
            ArrayList<String[]> Materia3 = Materias.get(2);

            DatosAEvaluar.add(Materia1.get(1));
            DatosAEvaluar.add(Materia2.get(1));
            DatosAEvaluar.add(Materia3.get(1));
        } else if (Materias.size() == 4) {
            ArrayList<String[]> Materia1 = Materias.get(0);
            ArrayList<String[]> Materia2 = Materias.get(1);
            ArrayList<String[]> Materia3 = Materias.get(2);
            ArrayList<String[]> Materia4 = Materias.get(3);

            DatosAEvaluar.add(Materia1.get(1));
            DatosAEvaluar.add(Materia2.get(1));
            DatosAEvaluar.add(Materia3.get(1));
            DatosAEvaluar.add(Materia4.get(1));
        }

        String[] DatosDeAprobacion = EvaluarDatosDeAprobacion(DatosAEvaluar);
        for (int i=0; i<3; i++) {
            Area[i+1] = DatosDeAprobacion[i];
        }

        return Area;
    }

    private ArrayList<String[]> EquivalenciaDeMTC(String mencion, Object[] record) {
        ArrayList<int[]> InformacionParaLaEquivalencia = new ArrayList<>();
        if (mencion == "Basica") {
            InformacionParaLaEquivalencia.add(new int[] {2, 12, 9});
            InformacionParaLaEquivalencia.add(new int[] {2, 10, 13});
            InformacionParaLaEquivalencia.add(new int[] {1, 11});
        } else if (mencion == "Diversificado") {
            InformacionParaLaEquivalencia.add(new int[] {3, 5, 14, 7});
        }
        //---------------
        ArrayList<String[]> MTC = new ArrayList<>();
        for (int i=0; i<3; i++) {
            if (mencion == "Diversificado" && i == 1) {
                ArrayList<String[]> GeografiaEconomica = ExtraerMateriaDeUnPeriodo(record[12].toString());
                ArrayList<String[]> Premilitar = ExtraerMateriaDeUnPeriodo(record[14].toString());

                for (int j=0; j<2; j++) {
                    String[] Materia;
                    ArrayList<String[]> DatosAEvaluar = new ArrayList<>();
                    DatosAEvaluar.add(GeografiaEconomica.get(j));
                    DatosAEvaluar.add(Premilitar.get(j));
                    Materia = PromediarSemestres(DatosAEvaluar.get(0), DatosAEvaluar.get(1));
                    String[] DatosDeAprobacion = EvaluarDatosDeAprobacion(DatosAEvaluar);
                    Materia[1] = DatosDeAprobacion[0];
                    Materia[2] = DatosDeAprobacion[1];
                    Materia[3] = DatosDeAprobacion[2];
                    MTC.add(Materia);
                }
                break;
            } else {
                int[] DetallesDeLaEquivalencia = InformacionParaLaEquivalencia.get(i);
                ArrayList<ArrayList<String[]>> Materias = new ArrayList<>();

                for (int j=0; j<DetallesDeLaEquivalencia[0]; j++) {
                    Materias.add(ExtraerMateriaDeUnPeriodo(record[DetallesDeLaEquivalencia[j+1]].toString()));
                }
                MTC.add(TransferirMateriaConDependencia(Materias));
            }
        }
        return MTC;
    }

    private ArrayList<String[]> EquivalenciaDeCN(String mencion, Object[] record) {
        ArrayList<int[]> InformacionParaLaEquivalencia = new ArrayList<>();
        if (mencion == "Basica") {
            InformacionParaLaEquivalencia.add(new int[] {2, 12, 9});
            InformacionParaLaEquivalencia.add(new int[] {2, 10, 13});
            InformacionParaLaEquivalencia.add(new int[] {1, 11});
        } else if (mencion == "Diversificado") {
            InformacionParaLaEquivalencia.add(new int[] {3, 5, 14, 7});
        }
        //---------------
        ArrayList<String[]> MTC = new ArrayList<>();
        for (int i=0; i<3; i++) {
            if (mencion == "Diversificado" && i == 1) {
                ArrayList<String[]> GeografiaEconomica = ExtraerMateriaDeUnPeriodo(record[12].toString());
                ArrayList<String[]> Premilitar = ExtraerMateriaDeUnPeriodo(record[14].toString());

                for (int j=0; j<2; j++) {
                    String[] Materia;
                    ArrayList<String[]> DatosAEvaluar = new ArrayList<>();
                    DatosAEvaluar.add(GeografiaEconomica.get(j));
                    DatosAEvaluar.add(Premilitar.get(j));
                    Materia = PromediarSemestres(DatosAEvaluar.get(0), DatosAEvaluar.get(1));
                    String[] DatosDeAprobacion = EvaluarDatosDeAprobacion(DatosAEvaluar);
                    Materia[1] = DatosDeAprobacion[0];
                    Materia[2] = DatosDeAprobacion[1];
                    Materia[3] = DatosDeAprobacion[2];
                    MTC.add(Materia);
                }
                break;
            } else {
                int[] DetallesDeLaEquivalencia = InformacionParaLaEquivalencia.get(i);
                ArrayList<ArrayList<String[]>> Materias = new ArrayList<>();

                for (int j=0; j<DetallesDeLaEquivalencia[0]; j++) {
                    Materias.add(ExtraerMateriaDeUnPeriodo(record[DetallesDeLaEquivalencia[j+1]].toString()));
                }
                MTC.add(TransferirMateriaConDependencia(Materias));
            }
        }
        return MTC;
    }

    //----------------------------------Extraccion de Materias--------------------------------------------------------//

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

    //----------------------------------Iniciar Equivalencias---------------------------------------------------------//

    private void HacerEquivalencia(String mencion, Object[] record) {
        Notas.LlenarAreasSinDatos();

        // Castellano -> LCC (Todos los periodos)
        ArrayList<String[]> Castellano = ExtraerMateria(mencion, record[3].toString());
        ArrayList<String[]> LCC = TransferirMateriaSinDependencias(mencion, Castellano);
        LCC = TransferirEscalaDeNotas(LCC);
        GuardarMateria(mencion,"LCC", LCC);

        // Matematica -> MAT (Todos los periodos)
        int PosicionEnElRecord = ((mencion == "Basica")?5:4);
        ArrayList<String[]> Matematica = ExtraerMateria(mencion, record[PosicionEnElRecord].toString());
        ArrayList<String[]> MAT = TransferirMateriaSinDependencias(mencion, Matematica);
        MAT = TransferirEscalaDeNotas(MAT);
        GuardarMateria(mencion,"MAT", MAT);

        // Historias -> MTC (Todos los periodos)
        /*ArrayList<String[]> MTC = EquivalenciaDeMTC(mencion, record);
        MTC = TransferirEscalaDeNotas(MTC);
        GuardarMateria(mencion,"MTC", MTC);*/

        // Ciencias -> CN (Todos los periodos)
        /*ArrayList<String[]> CN = EquivalenciaDeCN(mencion, record);
        CN = TransferirEscalaDeNotas(CN);
        GuardarMateria(mencion,"CN", CN);*/
    }

    public Equivalencia() {
        //PROBAR RECORD CON HUECOS
        //PROBAR NOTAS BAJAS
        dbf.BuscarNotas("V-14089500");

        if (Notas.recordBasica != null) {
            HacerEquivalencia("Basica", Notas.recordBasica);
        }
        if (Notas.recordDiversificado != null) {
            HacerEquivalencia("Diversificado", Notas.recordDiversificado);
        }

        //VER NUEVO RECORD
        String[] NombresMuestra = new String[] {"LCC", "MAT", "MTC", "CN"};
        for (int i=0; i<4; i++) {
            System.out.println(NombresMuestra[i]);
            ArrayList<String[]> Area = Notas.Areas.get(i);
            for (int j=0; j<6; j++) {
                String[] DatosDelArea = Area.get(j);
                System.out.println("Nota: " + DatosDelArea[0] + " TE: " + DatosDelArea[1] + " Fecha: " + DatosDelArea[2] + " Cod: " + DatosDelArea[3]);
            }
        }
    }
}
