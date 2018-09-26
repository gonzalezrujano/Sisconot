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
    private DataFormatter formatter = new DataFormatter();
    ArrayList<ArrayList<String[]>> Areas = Notas.Areas;
    ArrayList<String[]> PlantelesCursados = new ArrayList<>();

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
        Celda.setCellValue(Alumno.Cedula);
        // Fecha de nacimiento del alumno
        Celda = Columna.getCell(38);
        Celda.setCellValue("20 de Julio de 1999");
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

                            System.out.println("Verificando plantel: " + k + Plantel[0] + " " + CodPlantel);
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
            System.out.println("Area a llamar en esta columna: " + PosicionDelArea);

            Columna = Hoja1.getRow(ColumnasDeLasAreas[i]);

            System.out.println("    Periodos a llamar en este ciclo: " + PosicionDeLosPeriodos[0] + PosicionDeLosPeriodos[1]);
            for (int j=0; j<2; j++) {
                int[] PosicionDeLasCeldas = CeldasDeLosDatos.get(j);
                System.out.println("Posicion de la celda: " + PosicionDeLasCeldas[0]);

                // Nota en numeros
                Celda = Columna.getCell(PosicionDeLasCeldas[0]);
                Celda.setCellValue("1");
                // Nota en Letras
                Celda = Columna.getCell(PosicionDeLasCeldas[1]);
                Celda.setCellValue("UNO");
                // Tipo de Evaluacion
                Celda = Columna.getCell(PosicionDeLasCeldas[2]);
                Celda.setCellValue("F");
                // Mes de Aprobacion
                Celda = Columna.getCell(PosicionDeLasCeldas[3]);
                Celda.setCellValue("07");
                // Año de Aprobacion
                Celda = Columna.getCell(PosicionDeLasCeldas[4]);
                Celda.setCellValue("1999");
                // Nro del Plantel
                Celda = Columna.getCell(PosicionDeLasCeldas[5]);
                Celda.setCellValue("1");
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
            System.out.println("What: " + i + UbicacionDeLasCeldas.get(i));
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

    public Excel() {
        try { Documento = new HSSFWorkbook(new FileInputStream("report/certificacion.xls")); }
        catch (IOException e) { e.printStackTrace(); }

        VaciarDocumento();

        Hoja1 = Documento.getSheetAt(0);

        ColocarFechaAldocumento();
        ColocarDatosDelEstudiante();
        ColocarPlantelesCursados();
        //ColocarLasNotasEnElDocumento();


        /*Columna = Hoja1.getRow(20);
        for (int i=0; i<70; i++) {
            Celda = Columna.getCell(i);
            String text = formatter.formatCellValue(Celda);
            System.out.println("Valor de la celda => " + i + " " + text);
        }*/



        // Guardar cambios en el documento
        try (OutputStream fileOut = new FileOutputStream("report/certificacion.xls")) {
            Documento.write(fileOut);
            Documento.close();

            // Abrir el documento
            sisconot.AbrirArchivo("report/certificacion.xls");

        } catch (FileNotFoundException e) { Info.LanzarInfo("Cierre el Excel de la Certificacion"); }
        catch (IOException e) { e.printStackTrace(); }


    }

}
