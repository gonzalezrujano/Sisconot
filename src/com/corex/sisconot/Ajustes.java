package com.corex.sisconot;

import javax.swing.*;
import java.awt.event.*;

public class Ajustes extends JDialog {
    private JPanel contentPane;
    private JButton guardarButton;
    private JButton buttonCancel;
    private JTextField RutaDBFTextField;

    public Ajustes() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(guardarButton);

        DefinirEventosDeLosBotones();
        DefinirEventosDeSalida();
        MostrarAjustesGuardados();
    }

    private void DefinirEventosDeLosBotones() {
        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GuardarAjustes();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });
    }

    private void DefinirEventosDeSalida() {
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

    private void MostrarAjustesGuardados() {
        // Mostrar Ruta Absoluta de los DBFs de Sisconot
        RutaDBFTextField.setText(Configuracion.RutaDeLosDBFs);
    }

    private void GuardarAjustes() {
        // Guardar Nueva Ruta de los DBF
        String newRutaDeLosDBF = RutaDBFTextField.getText();
        newRutaDeLosDBF.replace("\\", "/");
        System.out.println("Ruta a guardar: " + newRutaDeLosDBF);
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void LanzarAjustes() {
        Ajustes dialog = new Ajustes();

        dialog.setTitle("Ajustes Generales");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
