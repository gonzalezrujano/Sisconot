package com.corex.sisconot;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellReference;

import javax.print.Doc;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;


public class Excel {
    private Workbook Documento = null;
    private Sheet Hoja1;
    private Row Columna;
    private Cell Celda;
    ArrayList<ArrayList<String[]>> Areas = Notas.Areas;
    ArrayList<String[]> PlantelesCursados = new ArrayList<>();

    public Excel() {
        try { Documento = new HSSFWorkbook(new FileInputStream("report/certificacion.xls")); }
        catch (IOException e) { e.printStackTrace(); }
        Hoja1 = Documento.getSheetAt(0);

        VaciarDocumento();
        ColocarFechaAldocumento();
        ColocarDatosDelEstudiante();
        ColocarPlantelesCursados();
        ColocarLasNotasEnElDocumento();

        // Guardar cambios en el documento
        try (OutputStream fileOut = new FileOutputStream("report/certificacion.xls")) {
            Documento.write(fileOut);
            Documento.close();

            // Abrir el documento
            sisconot.AbrirArchivo("report/certificacion.xls");

        } catch (FileNotFoundException e) { Info.LanzarInfo("Cierre el Excel de la Certificacion"); }
        catch (IOException e) { e.printStackTrace(); }


    }

    private String ObtenerFechaActual() {
        Calendar fecha = new GregorianCalendar();

        int Año = fecha.get(Calendar.YEAR);
        int Mes = fecha.get(Calendar.MONTH);
        int Dia = fecha.get(Calendar.DAY_OF_MONTH);

        return Dia + "/" + Mes + "/" + Año;
    }

    private String NotaEnLetras(String Nota) {
        switch (Nota) {
            case "1":
                return "UNO";
            case "2":
                return "DOS";
            case "3":
                return "TRES";
            case "4":
                return "CUATRO";
            case "5":
                return "CINCO";
            default:
                return "UNO";
        }
    }

    private String FormatearFechaDeNacimiento(String FechaDeNacimiento) {
        String Mes;
        switch (FechaDeNacimiento.substring(3, 5)) {
            case "01":
                Mes = "Enero";
                break;
            case "02":
                Mes = "Febrero";
                break;
            case "03":
                Mes = "Marzo";
                break;
            case "04":
                Mes = "Abril";
                break;
            case "05":
                Mes = "Mayo";
                break;
            case "06":
                Mes = "Junio";
                break;
            case "07":
                Mes = "Julio";
                break;
            case "08":
                Mes = "Agosto";
                break;
            case "09":
                Mes = "Septiembre";
                break;
            case "10":
                Mes = "Octubre";
                break;
            case "11":
                Mes = "Noviembre";
                break;
            case "12":
                Mes = "Diciembre";
                break;
            default:
                Mes = "Enero";
                break;
        }

        return FechaDeNacimiento.substring(0, 2) + " de " + Mes + " de " + FechaDeNacimiento.substring(6, 10);
    }

    private void ColocarFechaAldocumento() {
        Columna = Hoja1.getRow(3);
        Celda = Columna.getCell(42);
        Celda.setCellValue("PUERTO ORDAZ, " + ObtenerFechaActual());
    }

    private String NroDelPlantel(String CodPlantel) {
        for (int i=0; i<PlantelesCursados.size(); i++) {
            String[] Plantel = PlantelesCursados.get(i);
            if (Plantel[0].equals(CodPlantel)) { return  String.valueOf(i+1); }
        }
        return "1"; // Default
    }

    private void AgregarPlantelAlListado(String CodPlantel) { PlantelesCursados.add(dbf.BuscarPlantel(CodPlantel)); }

    private void ColocarPlantelesCursados() {
        PlantelesCursados = new ArrayList<>();

        for (int i=0; i<6; i++)
        {
            for (int j=0; j<4; j++)
            {
                ArrayList<String[]> Area = Areas.get(j);
                String[] Periodo = Area.get(i);
                String CodPlantel = Periodo[3];

                if (CodPlantel.equals("")) { continue; }
                else if (PlantelesCursados.size() == 0) { AgregarPlantelAlListado(CodPlantel); }
                else
                    {
                        boolean Encontrado = false;
                        for (int k=0; k<PlantelesCursados.size(); k++)
                        {
                            String[] Plantel = PlantelesCursados.get(k);

                            if (Plantel[0].equals(CodPlantel)) {
                                Encontrado = true;
                                break;
                            }
                        }

                        if (!Encontrado) { AgregarPlantelAlListado(CodPlantel); }
                    }
            }

        }
        // Ubicacion del plantel {Columna - Nombre - Localidad - Estado}
        ArrayList<int[]> UbicacionDeLasCeldas = new ArrayList<>();
        UbicacionDeLasCeldas.add(new int[] {13, 1, 20, 30});
        UbicacionDeLasCeldas.add(new int[] {14, 1, 20, 30});
        UbicacionDeLasCeldas.add(new int[] {12, 33, 51, 61});
        UbicacionDeLasCeldas.add(new int[] {13, 33, 51, 61});
        UbicacionDeLasCeldas.add(new int[] {14, 33, 51, 61});
        for (int i=0; i<PlantelesCursados.size(); i++) {
            String[] Plantel = PlantelesCursados.get(i);

            int[] UbicacionDelPlantel = UbicacionDeLasCeldas.get(i);
            Columna = Hoja1.getRow(UbicacionDelPlantel[0]);
            // Nombre del plantel
            Celda = Columna.getCell(UbicacionDelPlantel[1]);
            Celda.setCellValue(Plantel[1]);
            // Localidad del plantel
            Celda = Columna.getCell(UbicacionDelPlantel[2]);
            Celda.setCellValue(Plantel[2]);
            // Entidad del plantel
            Celda = Columna.getCell(UbicacionDelPlantel[3]);
            Celda.setCellValue(Plantel[3]);
        }
    }

    private void ColocarDatosDelEstudiante() {
        Columna = Hoja1.getRow(8);
        // Cedula del alumno
        Celda = Columna.getCell(7);
        Celda.setCellValue(Alumno.Cedula);
        // Fecha de nacimiento del alumno
        Celda = Columna.getCell(38);
        Celda.setCellValue(FormatearFechaDeNacimiento(Alumno.FechaDeNacimiento));
        Columna = Hoja1.getRow(9);
        // Apellidos del alumno
        Celda = Columna.getCell(4);
        Celda.setCellValue(Alumno.Apellidos);
        // Nombres del alumno
        Celda = Columna.getCell(35);
        Celda.setCellValue(Alumno.Nombres);
        Columna = Hoja1.getRow(10);
        // Lugar de nacimiento del alumno
        Celda = Columna.getCell(10);
        Celda.setCellValue(Alumno.LugarDeNacimiento);
        // Entidad de nacimiento del alumno
        Celda = Columna.getCell(41);
        Celda.setCellValue(Alumno.EntidadFederal);
    }

    private void ColocarLasNotasEnElDocumento() {
        int[] ColumnasDeLasAreas = new int[] {20, 21, 22, 23, 29, 30, 31, 32, 38, 39, 40, 41};

        // {Nota(nro) - Nota(letras) - TipoEval - Mes - Año - NroPlantel}
        ArrayList<int[]> CeldasDeLosDatos = new ArrayList<>();
        CeldasDeLosDatos.add(new int[] {17, 19, 23, 25, 27, 29});
        CeldasDeLosDatos.add(new int[] {49, 51, 55, 57, 59, 61});

        int PosicionDelArea = 0;
        int[] PosicionDeLosPeriodos = new int[] {0, 1};

        for (int i=0; i<ColumnasDeLasAreas.length; i++) {

            if (PosicionDelArea == 4) { PosicionDelArea = 0; }
            if (i == 4 || i == 8) {
                PosicionDeLosPeriodos[0] += 2;
                PosicionDeLosPeriodos[1] += 2;
            }
            ArrayList<String[]> Area = Areas.get(PosicionDelArea);

            Columna = Hoja1.getRow(ColumnasDeLasAreas[i]);

            for (int j=0; j<2; j++) {
                int[] PosicionDeLasCeldas = CeldasDeLosDatos.get(j);
                String[] Periodo = Area.get(PosicionDeLosPeriodos[j]);

                // Nota en numeros
                Celda = Columna.getCell(PosicionDeLasCeldas[0]);
                if (Periodo[0].equals("0")) { Celda.setCellValue(" * "); }
                else { Celda.setCellValue(Periodo[0]); }
                // Nota en Letras
                Celda = Columna.getCell(PosicionDeLasCeldas[1]);
                if (Periodo[0].equals("0")) { Celda.setCellValue(" *** "); }
                else { Celda.setCellValue(NotaEnLetras(Periodo[0])); }
                // Tipo de Evaluacion
                Celda = Columna.getCell(PosicionDeLasCeldas[2]);
                if (Periodo[0].equals("0")) { Celda.setCellValue(" * "); }
                else { Celda.setCellValue(Periodo[1]); }
                // Mes de Aprobacion
                Celda = Columna.getCell(PosicionDeLasCeldas[3]);
                if (Periodo[2].equals("")) { Celda.setCellValue(" * "); }
                else { Celda.setCellValue(Periodo[2].substring(0, 2)); }
                // Año de Aprobacion
                Celda = Columna.getCell(PosicionDeLasCeldas[4]);
                if (Periodo[2].equals("")) { Celda.setCellValue(" * "); }
                else { Celda.setCellValue(Periodo[2].substring(2, 6)); }
                // Nro del Plantel
                Celda = Columna.getCell(PosicionDeLasCeldas[5]);
                if (Periodo[3].equals("")) { Celda.setCellValue(" * "); }
                else { Celda.setCellValue(NroDelPlantel(Periodo[3])); }
            }
            PosicionDelArea += 1;
        }
    }

    private void VaciarDocumento() {
        // Vaciar lista de planteles
        // Ubicacion del plantel {Columna - Nombre - Localidad - Estado}
        ArrayList<int[]> UbicacionDeLasCeldas = new ArrayList<>();
        UbicacionDeLasCeldas.add(new int[] {13, 1, 20, 30});
        UbicacionDeLasCeldas.add(new int[] {14, 1, 20, 30});
        UbicacionDeLasCeldas.add(new int[] {12, 33, 51, 61});
        UbicacionDeLasCeldas.add(new int[] {13, 33, 51, 61});
        UbicacionDeLasCeldas.add(new int[] {14, 33, 51, 61});
        for (int i=0; i<5; i++) {
            int[] UbicacionDelPlantel = UbicacionDeLasCeldas.get(i);

            Columna = Hoja1.getRow(UbicacionDelPlantel[0]);
            // Nombre del plantel
            Celda = Columna.getCell(UbicacionDelPlantel[1]);
            Celda.setCellValue("");
            // Localidad del plantel
            Celda = Columna.getCell(UbicacionDelPlantel[2]);
            Celda.setCellValue("");
            // Entidad del plantel
            Celda = Columna.getCell(UbicacionDelPlantel[3]);
            Celda.setCellValue("");
        }
    }

}
