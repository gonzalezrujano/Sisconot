package com.corex.sisconot;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class sisconot {

    public static void AbrirArchivo(String archivo){
        try {
            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);
        }catch (IOException ex) { System.out.println(ex); }
    }

    public static void main(String[] args) {
        // ITERAR EN ESTILOS DE INTERFAZ DISPONIBLES
        // MIGRAR A JAVAFX
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        /*UIManager.LookAndFeelInfo[] lista = UIManager.getInstalledLookAndFeels();
        for (int i = 0; i < lista.length; i++) {
            System.out.println(lista[i].getClassName());
        }*/

        /* CORREGIR */
        /*
        > Archivo de conf con rutas de los dbf
        > Agregar promedio a 5 y 6 to periodo
        > Corregir formato de fecha de nacimiento
        > Quitar temporal de tony por defecto ?
        > Borrar temporal de revision de record nuevo ?
        > Colocar identificador del plantel en el documento
        > Quitar todos los System ?
        */
        System.out.println("Hola Sisconot");
        MainWindow.lanzarApp();
    }
}
