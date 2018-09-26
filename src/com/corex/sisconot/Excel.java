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

    private String ObtenerFechaActual() {
        Calendar fecha = new GregorianCalendar();

        int Año = fecha.get(Calendar.YEAR);
        int Mes = fecha.get(Calendar.MONTH);
        int Dia = fecha.get(Calendar.DAY_OF_MONTH);

        return Dia + "/" + Mes + "/" + Año;
    }

    private void ColocarFechaAldocumento() {
        Columna = Hoja1.getRow(3);
        Celda = Columna.getCell(42);
        Celda.setCellValue("PUERTO ORDAZ, " + ObtenerFechaActual());
    }

    private void ColocarDatosDelEstudiante() {
        Columna = Hoja1.getRow(8);
        // Cedula del alumno
        Celda = Columna.getCell(7);
        Celda.setCellValue("V-27077383");
        // Fecha de nacimiento del alumno
        Celda = Columna.getCell(38);
        Celda.setCellValue("20 de Julio de 1999");
        Columna = Hoja1.getRow(9);
        // Apellidos del alumno
        Celda = Columna.getCell(4);
        Celda.setCellValue("GONZALEZ RUJANO");
        // Nombres del alumno
        Celda = Columna.getCell(35);
        Celda.setCellValue("LEONARDO JOSE");
        Columna = Hoja1.getRow(10);
        // Lugar de nacimiento del alumno
        Celda = Columna.getCell(10);
        Celda.setCellValue("PUERTO ORDAZ");
        // Entidad de nacimiento del alumno
        Celda = Columna.getCell(41);
        Celda.setCellValue("BOLIVAR");
    }

    private void ColocarPlantelesCursados() {
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
            Celda.setCellValue("FFF");
            // Localidad del plantel
            Celda = Columna.getCell(UbicacionDelPlantel[2]);
            Celda.setCellValue("FFF");
            // Entidad del plantel
            Celda = Columna.getCell(UbicacionDelPlantel[3]);
            Celda.setCellValue("FFF");
        }
    }

    private void ColocarLasNotasEnElDocumento() {
        ArrayList<ArrayList<String[]>> Areas = Notas.Areas;
        int[] ColumnasDeLasAreas = new int[] {20, 21, 22, 23, 29, 30, 31, 32, 38, 39, 40, 41};
        int PosicionDelArea = 0;

        for (int i=0; i<ColumnasDeLasAreas.length; i++) {
            for (int j=0; j<12; j++) {
                //
            }
        }
    }

    private void VaciarDocumento() {}

    public Excel() {
        try { Documento = new HSSFWorkbook(new FileInputStream("report/certificacion.xls")); }
        catch (IOException e) { e.printStackTrace(); }

        //VaciarDocumento();

        DataFormatter formatter = new DataFormatter();
        Hoja1 = Documento.getSheetAt(0);

        //ColocarFechaAldocumento();
        //ColocarDatosDelEstudiante();
        //ColocarPlantelesCursados();
        ColocarLasNotasEnElDocumento();

        //INICIAR Y GUARDAR PLANTELES EN NOTAS

        Columna = Hoja1.getRow(20);
        for (int i=0; i<70; i++) {
            Celda = Columna.getCell(i);
            String text = formatter.formatCellValue(Celda);
            System.out.println("Valor de la celda => " + i + " " + text);
        }



        // Write the output to a file
        try (OutputStream fileOut = new FileOutputStream("report/certificacion.xls")) {
            Documento.write(fileOut);
            Documento.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*for (int i=0; i<50; i++) {
            Cell Celda = Columna.getCell(i);
            String text = formatter.formatCellValue(Celda);
            System.out.println("Valor de la celda => " + i + " " + text);
        }*/

    }

}
