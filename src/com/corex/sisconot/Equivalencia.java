package com.corex.sisconot;

import java.awt.image.AreaAveragingScaleFilter;
import java.security.SecureRandom;
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

        /*System.out.println(Mencion);
        for (int i=0; i<6; i++) {
            String[] Datos = Area.get(i);
            System.out.println(Datos[0] + " " + Datos[1] + " " +  Datos[2] + " " + Datos[3]);
        }*/

        int PosicionDelPeriodo = 0;
        if (Mencion.equals("Basica")) {
            PosicionDelPeriodo = 0;
        } else {
            PosicionDelPeriodo = 3;
        }
        for (int i=0; i<3; i++) {
            Area.set(i+PosicionDelPeriodo, AreaAGuardar.get(i));
        }
        Notas.Areas.set(PosicionDelArea, Area);

        /*System.out.println(Mencion);
        for (int i=0; i<6; i++) {
            String[] Datos = Area.get(i);
            System.out.println(Datos[0] + " " + Datos[1] + " " +  Datos[2] + " " + Datos[3]);
        }*/
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

        if (Campo.equals("")) { return Datos; }
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

    private ArrayList<int[]> ObtenerPosicionesDeDependencias(String Mencion, String NombreDelArea) {
        ArrayList<int[]> PosicionesDeDependencias = new ArrayList<>();
        if (NombreDelArea.equals("MTC")) {
            if (Mencion.equals("Basica")) {
                PosicionesDeDependencias.add(new int[] {9, 12});
                PosicionesDeDependencias.add(new int[] {10, 13});
                PosicionesDeDependencias.add(new int[] {11});
            } else if (Mencion.equals("Diversificado")) {
                PosicionesDeDependencias.add(new int[] {5, 7, 14});
                PosicionesDeDependencias.add(new int[] {12, 14});
                //PosicionesDeDependencias.add(new int[] {12, 14});
            }
        } else if (NombreDelArea.equals("CN")) {
            if (Mencion.equals("Basica")) {
                PosicionesDeDependencias.add(new int[] {6});
                PosicionesDeDependencias.add(new int[] {6});
                PosicionesDeDependencias.add(new int[] {6, 7, 8});
            } else if (Mencion.equals("Diversificado")) {
                PosicionesDeDependencias.add(new int[] {8, 9, 10});
                PosicionesDeDependencias.add(new int[] {8, 9, 10, 13});
                //PosicionesDeDependencias.add(new int[] {6, 7, 8});
            }
        }
        return PosicionesDeDependencias;
    }

    private ArrayList<ArrayList<String[]>> VerificarCuantasTieneAprobadas(ArrayList<ArrayList<String[]>> DependenciasDelPeriodo) {
        ArrayList<ArrayList<String[]>> Aprobadas = new ArrayList<>();
        //System.out.println("Verificar cuantas dependencias de este periodo estan aprobadas");
        for (int i=0; i<DependenciasDelPeriodo.size(); i++) {
            ArrayList<String[]> SemestresDeLaDependencia = DependenciasDelPeriodo.get(i);
            String[] PromedioDeLaDependencia = PromediarSemestres(SemestresDeLaDependencia.get(0), SemestresDeLaDependencia.get(1));
            // Verificar si esta dependencia esta aprobada
            int NotaDelPromedio = Integer.parseInt(PromedioDeLaDependencia[0]);
            //System.out.println("Nota del Promedio" + NotaDelPromedio);
            if (NotaDelPromedio > 9) {
                Aprobadas.add(SemestresDeLaDependencia);
            }
        }

        return Aprobadas;
    }

    private String[] EvaluarDatosDeAprobacion(ArrayList<String[]> MateriasAEvaluar) {
        String[] DatosDeAprobacion = new String[] {"0", "", "", ""};

        int Año = 0, Mes = 0, TipoDeEvaluacion = 0, PosicionDeLosDatosDeAprobacion = -1;
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
                    case "E":
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

        if (PosicionDeLosDatosDeAprobacion != -1) {
            return MateriasAEvaluar.get(PosicionDeLosDatosDeAprobacion);
        }
        return DatosDeAprobacion;
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
                else { DatosDeLaEquivalencia = DatosDeLaMateria.get(i+1); }
                MateriaEnPeriodos.set(i, DatosDeLaEquivalencia);
            }
        }
        return MateriaEnPeriodos;
    }

    private String[] PromedioDeMateriasConDependencias(ArrayList<ArrayList<String[]>> DependenciasDelPeriodo) {
        String[] PeriodoTransferido = new String[4];

        int SumatoriaNotasDeLaIzquierda = 0, SumatoriaNotasDeLaDerecha = 0;
        ArrayList<String[]> DatosDeAprobacionAEvaluar = new ArrayList<>();
        for (int i=0; i<DependenciasDelPeriodo.size(); i++) {
            ArrayList<String[]> SemestresDeLaDependencia = DependenciasDelPeriodo.get(i);

            String[] SemestreIzquierdo = SemestresDeLaDependencia.get(0);
            String[] SemestreDerecho = SemestresDeLaDependencia.get(1);

            SumatoriaNotasDeLaIzquierda += Integer.parseInt(SemestreIzquierdo[0]);
            SumatoriaNotasDeLaDerecha += Integer.parseInt(SemestreDerecho[0]);

            DatosDeAprobacionAEvaluar.add(SemestreDerecho);
        }

        PeriodoTransferido = EvaluarDatosDeAprobacion(DatosDeAprobacionAEvaluar);

        PeriodoTransferido[0] = String.valueOf((SumatoriaNotasDeLaIzquierda / DependenciasDelPeriodo.size() + SumatoriaNotasDeLaDerecha / DependenciasDelPeriodo.size()) / 2);

        return PeriodoTransferido;
    }

    private String[] TransferirMateriaConDependencia(String Mencion, ArrayList<ArrayList<String[]>> Dependencias) {
        // Equivalencia del periodo del area con las dependencias recibidas
        String[] PeriodoTransferido = new String[4];

        // Verificar posibilidad de exonerar
        if (Dependencias.size() >= 3) {
            // Verificar cuantas dependencias tiene aprobadas (para promediar por esa cantidad)
            ArrayList<ArrayList<String[]>> MateriasAprobadas = VerificarCuantasTieneAprobadas(Dependencias);
            if (MateriasAprobadas.size() == (Dependencias.size() - 1)) { PeriodoTransferido = PromedioDeMateriasConDependencias(MateriasAprobadas); }
            else { PeriodoTransferido = PromedioDeMateriasConDependencias(Dependencias); }
        } else {
            PeriodoTransferido = PromedioDeMateriasConDependencias(Dependencias);
        }
        return PeriodoTransferido;
    }

    private ArrayList<String[]> TransferirUltimosDeDiversificado(ArrayList<ArrayList<String[]>> Dependencias) {
        ArrayList<String[]> QuintoYSextoPeriodo = new ArrayList<>();

        int SumatoriaNotasDelQuinto = 0, SumatoriaNotasDelSexto = 0;
        ArrayList<String[]> DatosDeAprobacionDelQuinto = new ArrayList<>();
        ArrayList<String[]> DatosDeAprobacionDelSexto = new ArrayList<>();
        for (int i=0; i<Dependencias.size(); i++) {
            ArrayList<String[]> SemestresDeLaDependencia = Dependencias.get(i);

            String[] SemestreIzquierdo = SemestresDeLaDependencia.get(0);
            String[] SemestreDerecho = SemestresDeLaDependencia.get(1);

            SumatoriaNotasDelQuinto += Integer.parseInt(SemestreIzquierdo[0]);
            SumatoriaNotasDelSexto += Integer.parseInt(SemestreDerecho[0]);

            DatosDeAprobacionDelQuinto.add(SemestreIzquierdo);
            DatosDeAprobacionDelSexto.add(SemestreDerecho);
        }

        String[] ResultadoDelQuinto = EvaluarDatosDeAprobacion(DatosDeAprobacionDelQuinto);
        String[] ResultadoDelSexto = EvaluarDatosDeAprobacion(DatosDeAprobacionDelSexto);

        ResultadoDelQuinto[0] = String.valueOf(SumatoriaNotasDelQuinto / Dependencias.size());
        ResultadoDelSexto[0] = String.valueOf(SumatoriaNotasDelSexto / Dependencias.size());

        QuintoYSextoPeriodo.add(ResultadoDelQuinto);
        QuintoYSextoPeriodo.add(ResultadoDelSexto);

        return QuintoYSextoPeriodo;
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
            try {
                Materia.add(SepararMateria(record.substring(inicio, fin)));
            } catch (StringIndexOutOfBoundsException e) {
                Materia.add(SepararMateria(""));
            }
            inicio += 12;
            fin += 12;
        }
        return Materia;
    }

    // ABSTRAER BUCLE EN FUNCIONES
    private ArrayList<ArrayList<ArrayList<String[]>>> ExtraerDependenciasDelArea(String Mencion, String NombreDelArea, Object[] record) {
        ArrayList<int[]> PosicionesDeLasDependencias = ObtenerPosicionesDeDependencias(Mencion, NombreDelArea);
        int NroDePeriodos = ((Mencion.equals("Basica") ? 3 : 2));

        ArrayList<ArrayList<ArrayList<String[]>>> DependenciasDelPeriodo = new ArrayList<>();
        // Sacar dependencias de los 3 periodos de la mencion
        int inicioDeRecorteDelSemestre = 0, finDeRecorteDelSemestre = 12;

        //System.out.println(Mencion);
        for (int i=0; i<NroDePeriodos; i++) {

            //System.out.println("Periodo: "+(i+1));
            int[] PosicionesDeLaDependencia = PosicionesDeLasDependencias.get(i);

            // Sacar el nro de dependencias correpondientes
            int inicioDelCorte = inicioDeRecorteDelSemestre, finDelCorte = finDeRecorteDelSemestre;
            ArrayList<ArrayList<String[]>> Dependencia = new ArrayList<>();
            //System.out.println("Nro de dependencias del periodo: " + PosicionesDeLaDependencia.length);
            for (int j=0; j<PosicionesDeLaDependencia.length; j++) {
                //System.out.println("  Posicion de la Dependencia: "+(j+1)+"=> "+PosicionesDeLaDependencia[j]);
                // Sacar y cortar el registro de la dependencia en los semestre correspondiente
                String RegistroCompletoDeLaDependencia = record[PosicionesDeLaDependencia[j]].toString();

                String[] SemestreDeLaDependencia;
                ArrayList<String[]> SemestresDeLaDependencia = new ArrayList<>();
                for (int k=0; k<2; k++) {
                    // Recortar semestre de la dependencia y separar datos
                    //System.out.println("    Start: "+inicioDelCorte+" End: "+finDelCorte);
                    //System.out.println("    Recorte Completo: "+RegistroCompletoDeLaDependencia);

                    // Verificar si el campo del record esta vacio
                    String SemestreASeparar = "";
                    if (RegistroCompletoDeLaDependencia.equals("")) { SemestreASeparar = ""; }
                    else {
                        // Retornando sin datos si el semestres esta vacio (no se puede recortar hasta alli)
                        try {
                            SemestreASeparar = RegistroCompletoDeLaDependencia.substring(inicioDelCorte, finDelCorte);
                        } catch (StringIndexOutOfBoundsException e) {
                            SemestreASeparar = "";
                        }
                    }

                    SemestreDeLaDependencia = SepararMateria(SemestreASeparar);
                    //System.out.println("        Ya separado: "+SemestreDeLaDependencia[0]+" "+SemestreDeLaDependencia[1]+" "+SemestreDeLaDependencia[2]+" "+SemestreDeLaDependencia[3]);

                    SemestresDeLaDependencia.add(SemestreDeLaDependencia);
                    // Cortar el semestre posterior
                    inicioDelCorte += 12;
                    finDelCorte += 12;
                }
                // Reestablecer para cortar el primero
                inicioDelCorte -= 24;
                finDelCorte -= 24;
                // Guardar Dependencias del periodo
                Dependencia.add(SemestresDeLaDependencia);
            }
            // Cortar semestres del siguiente periodo
            inicioDeRecorteDelSemestre += 24;
            finDeRecorteDelSemestre += 24;
            // Guardar Semestres de la dependencias
            DependenciasDelPeriodo.add(Dependencia);
        }
        return DependenciasDelPeriodo;
    }

    //----------------------------------Iniciar Equivalencias---------------------------------------------------------//

    private void HacerEquivalencia(String mencion, Object[] record) {
        // Castellano -> LCC
        ArrayList<String[]> Castellano = ExtraerMateria(mencion, record[3].toString());
        ArrayList<String[]> LCC = TransferirMateriaSinDependencias(mencion, Castellano);
        LCC = TransferirEscalaDeNotas(LCC);
        GuardarMateria(mencion,"LCC", LCC);
        //-----------------------------------------------------------------------------------------------------------------------//
        // Matematica -> MAT (Todos los periodos)
        int PosicionEnElRecord = ((mencion == "Basica")?5:4);
        ArrayList<String[]> Matematica = ExtraerMateria(mencion, record[PosicionEnElRecord].toString());
        ArrayList<String[]> MAT = TransferirMateriaSinDependencias(mencion, Matematica);
        MAT = TransferirEscalaDeNotas(MAT);
        GuardarMateria(mencion,"MAT", MAT);
        //-----------------------------------------------------------------------------------------------------------------------//
        // Historias -> MTC
        ArrayList<String[]> MTC = new ArrayList<>();
        ArrayList<ArrayList<ArrayList<String[]>>> Dependencias = ExtraerDependenciasDelArea(mencion, "MTC", record);
        for (int i=0; i<Dependencias.size(); i++) {
            // Hacer equivalencia del periodo del area
            ArrayList<ArrayList<String[]>> DependenciasDelPeriodo = Dependencias.get(i);
            if (mencion == "Diversificado" && i == 1) {
                // Transformar el 3 y 4 semestre a Quinto y Sexto Periodo
                ArrayList<String[]> QuintoYSextoPeriodo = TransferirUltimosDeDiversificado(DependenciasDelPeriodo);
                MTC.add(QuintoYSextoPeriodo.get(0));
                MTC.add(QuintoYSextoPeriodo.get(1));
            } else {
                String[] EquivalenciaDelPeriodo = TransferirMateriaConDependencia(mencion, DependenciasDelPeriodo);
                MTC.add(EquivalenciaDelPeriodo);
            }

        }
        MTC = TransferirEscalaDeNotas(MTC);
        GuardarMateria(mencion,"MTC", MTC);
        //-----------------------------------------------------------------------------------------------------------------------//
        // Ciencias -> CN
        ArrayList<String[]> CN = new ArrayList<>();
        Dependencias = ExtraerDependenciasDelArea(mencion, "CN", record);
        for (int i=0; i<Dependencias.size(); i++) {
            // Hacer equivalencia del periodo del area
            ArrayList<ArrayList<String[]>> DependenciasDelPeriodo = Dependencias.get(i);
            if (mencion == "Diversificado" && i == 1) {
                // Transformar el 3 y 4 semestre a Quinto y Sexto Periodo
                ArrayList<String[]> QuintoYSextoPeriodo = TransferirUltimosDeDiversificado(DependenciasDelPeriodo);
                CN.add(QuintoYSextoPeriodo.get(0));
                CN.add(QuintoYSextoPeriodo.get(1));
            } else {
                String[] EquivalenciaDelPeriodo = TransferirMateriaConDependencia(mencion, DependenciasDelPeriodo);
                CN.add(EquivalenciaDelPeriodo);
            }

        }
        CN = TransferirEscalaDeNotas(CN);
        GuardarMateria(mencion,"CN", CN);
    }

    public Equivalencia(String Cedula) {
        dbf.BuscarNotas(Cedula);

        Notas.LlenarAreasSinDatos();

        if (Notas.recordBasica != null) {
            HacerEquivalencia("Basica", Notas.recordBasica);
        }
        if (Notas.recordDiversificado != null) {
            HacerEquivalencia("Diversificado", Notas.recordDiversificado);
        }

        //VER NUEVO RECORD (TEMPORAL)
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