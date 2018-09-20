package com.corex.sisconot;

import javax.swing.*;
import java.awt.event.*;

public class Certificacion extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JComboBox comboBox_Tipo_Cedula;
    private JTextField cedulaTextField;
    private JTextField nombresTextField;
    private JTextField textField1;
    private JTextField textField2;

    public Certificacion() {
        // Definiciones de la Ventana
        setContentPane(contentPane);
        setTitle("Imprimir Certificacion");
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        DefinirTipoDeDocumento();
        AgregarEscuchaALosBotones();
        DefinirEventosDeSalida();
    }

    private void DefinirTipoDeDocumento() {
        comboBox_Tipo_Cedula.addItem("V");
        comboBox_Tipo_Cedula.addItem("E");
        comboBox_Tipo_Cedula.addItem("C");
    }

    private void AgregarEscuchaALosBotones() {
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onOK(); }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onCancel(); }
        });
    }

    private void DefinirEventosDeSalida(){
        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void LanzarCertificacion() {
        Certificacion dialog = new Certificacion();
        dialog.pack();
        dialog.setVisible(true);
        //System.exit(0);
    }
}
