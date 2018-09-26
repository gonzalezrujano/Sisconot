package com.corex.sisconot;

import javax.swing.*;

public class sisconot {
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
        System.out.println("Hola Sisconot");
        Excel excel = new Excel();
        //MainWindow.lanzarApp();
    }
}
