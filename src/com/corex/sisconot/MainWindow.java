package com.corex.sisconot;

import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Frame.MAXIMIZED_BOTH;

public class MainWindow implements ActionListener {
    JFrame Ventana;
    private JMenuBar mb;
    private JMenu menu1;
    private JMenuItem mi1,mi2,mi3;
    private JPanel PanelPrincipal;

    public void CrearMenu() {
        //setLayout(null);
        mb= new JMenuBar();
        Ventana.setJMenuBar(mb);
        menu1=new JMenu("Opciones");
        mb.add(menu1);
        mi1= new JMenuItem("Imprimir Certificacion");
        mi1.addActionListener(e -> Certificacion.LanzarCertificacion());
        menu1.add(mi1);
    }

    public static void lanzarApp() {
        MainWindow mainWindow = new MainWindow();

        mainWindow.Ventana = new JFrame("Sisconot");
        // Crear Menu Superior
        mainWindow.CrearMenu();

        // Establecer ventana principal
        mainWindow.Ventana.setContentPane(new MainWindow().PanelPrincipal);
        mainWindow.Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWindow.Ventana.pack();
        mainWindow.Ventana.setVisible(true);

        // Maximizar Ventana Principal
        mainWindow.Ventana.setExtendedState(MAXIMIZED_BOTH);

    }

    @Override
    public void actionPerformed(ActionEvent e) {}
}
