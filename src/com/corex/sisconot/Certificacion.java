package com.corex.sisconot;

import com.corex.sisconot.TextPrompt;

import javax.swing.*;
import java.awt.event.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class Certificacion extends JDialog {
    private JPanel contentPane;
    private JButton buscarButton;
    private JButton salirButton;
    private JComboBox comboBox_Tipo_Cedula;
    public JTextField cedulaTextField;
    private JTextField nombresTextField;
    private JTextField apellidosTextField;
    private JTextField fechaNacimientoTextField;
    private JTextField lugarNacimientoTextField;
    private JTextField entidadTextField;
    private JTabbedPane tabbedPane1;
    private JButton imprimirButton;
    private JButton vaciarButton;
    private JPanel ContenedorDePeriodos;

    public Certificacion() {
        // Definiciones de la Ventana
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buscarButton);

        ColocarPlaceholdersEnTextField();
        AgregarEscuchaALosBotones();
        DefinirEventosDeSalida();
    }

    private void ColocarPlaceholdersEnTextField() {
        TextPrompt placeholder_Cedula = new TextPrompt("Cedula", cedulaTextField);
        TextPrompt placeholder_Nombres = new TextPrompt("Nombres", nombresTextField);
        TextPrompt placeholder_Apellidos = new TextPrompt("Apellidos", apellidosTextField);
        TextPrompt placeholder_FechaNacimiento = new TextPrompt("Fecha de Nacimiento", fechaNacimientoTextField);
        TextPrompt placeholder_LugarNacimiento = new TextPrompt("Lugar de Nacimiento", lugarNacimientoTextField);
        TextPrompt placeholder_Entidad = new TextPrompt("Entidad Federal", entidadTextField);
    }

    private void AgregarEscuchaALosBotones() {
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { BuscarAlumno(); }
        });

        imprimirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { ImprimirCertificacion(); }
        });

        vaciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { LimpiarDatos(); }
        });

        salirButton.addActionListener(new ActionListener() {
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

    private void BuscarAlumno() {
        LimpiarDatos();

        String Tipo_Cedula = (String) comboBox_Tipo_Cedula.getSelectedItem();
        String Cedula = cedulaTextField.getText();

        if (Cedula.equals("")) {
            Info.LanzarInfo("Ingrese una cedula");
        } else {
            Cedula = Tipo_Cedula + "-" + Cedula;

            //BUSCAR PRIMERO EN SQL!!! funcion normal
            if (false) {
                MostrarDatosDelAlumno();
            }
            else if (dbf.BuscarDatos(Cedula)) {
                Equivalencia equivalencia = new Equivalencia(Cedula);
                MostrarDatosDelAlumno();
            } else {
                Info.LanzarInfo("Alumno no encontrado");
            }
        }
    }

    private void MostrarDatosDelAlumno() {
        MostrarDatos();
        MostrarNotas();
    }

    private void MostrarDatos() {
        nombresTextField.setText(Alumno.Nombres);
        apellidosTextField.setText(Alumno.Apellidos);
        fechaNacimientoTextField.setText(String.valueOf(Alumno.FechaDeNacimiento));
        lugarNacimientoTextField.setText(Alumno.LugarDeNacimiento);
        entidadTextField.setText(Alumno.EntidadFederal);
    }

    private void MostrarNotas() {
        ArrayList<String[]> Area;
        String[] Periodo;


        JPanel ContenedorDeBasica = (JPanel) ContenedorDePeriodos.getComponent(0);
        JPanel ContenedorDeDiversificado = (JPanel) ContenedorDePeriodos.getComponent(1);
        JPanel ContenedorDelPeriodo;
        JPanel ContenedorDelArea;

        for (int i=0; i<6; i++) {

            if (i < 3) { ContenedorDelPeriodo = (JPanel) ContenedorDeBasica.getComponent(i); }
            else { ContenedorDelPeriodo = (JPanel) ContenedorDeDiversificado.getComponent(i-3); }

            for (int j=0; j<4; j++) {
                Area = Notas.Areas.get(j);
                Periodo = Area.get(i);

                ContenedorDelArea = (JPanel) ContenedorDelPeriodo.getComponent(j);

                JSpinner Nota = (JSpinner) ContenedorDelArea.getComponent(2);
                JComboBox TipoEvaluacion = (JComboBox) ContenedorDelArea.getComponent(3);
                JTextField FechaAprobacion = (JTextField) ContenedorDelArea.getComponent(1);
                JTextField CodPlantel = (JTextField) ContenedorDelArea.getComponent(4);

                Nota.setValue(Integer.parseInt(Periodo[0]));
                TipoEvaluacion.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
                FechaAprobacion.setText(Periodo[2]);
                CodPlantel.setText(Periodo[3]);
            }
        }
    }

    private int DeterminarPosicionDeLaEvaluacion(String Evaluacion) {
        switch (Evaluacion) {
            case "F":
                return 0;
            case "R":
                return 1;
            case "X":
                return 2;
            case "E":
                return 2;
            default:
                return 0;
        }
    }

    private void ImprimirCertificacion() {
        if (!Alumno.Nombres.equals(""))
        {
            ArrayList<String[]> Area;
            String[] Periodo;


            JPanel ContenedorDeBasica = (JPanel) ContenedorDePeriodos.getComponent(0);
            JPanel ContenedorDeDiversificado = (JPanel) ContenedorDePeriodos.getComponent(1);
            JPanel ContenedorDelPeriodo;
            JPanel ContenedorDelArea;

            for (int i=0; i<6; i++) {

                if (i < 3) { ContenedorDelPeriodo = (JPanel) ContenedorDeBasica.getComponent(i); }
                else { ContenedorDelPeriodo = (JPanel) ContenedorDeDiversificado.getComponent(i-3); }

                for (int j=0; j<4; j++) {
                    Area = Notas.Areas.get(j);
                    Periodo = Area.get(i);

                    ContenedorDelArea = (JPanel) ContenedorDelPeriodo.getComponent(j);

                    JSpinner Nota = (JSpinner) ContenedorDelArea.getComponent(2);
                    JComboBox TipoEvaluacion = (JComboBox) ContenedorDelArea.getComponent(3);
                    JTextField FechaAprobacion = (JTextField) ContenedorDelArea.getComponent(1);
                    JTextField CodPlantel = (JTextField) ContenedorDelArea.getComponent(4);

                    Periodo[0] = String.valueOf(Nota.getValue());
                    Periodo[1] = (String) TipoEvaluacion.getSelectedItem();
                    Periodo[2] = FechaAprobacion.getText();
                    Periodo[3] = CodPlantel.getText();

                    Area.set(i, Periodo);
                    Notas.Areas.set(j, Area);
                }
            }

            Excel excel = new Excel();
        } else { Info.LanzarInfo("Defina el alumno a imprimir"); }
    }

    private void LimpiarDatos() {
        Alumno.VaciarDatos();
        Notas.VaciarDatos();

        nombresTextField.setText("");
        apellidosTextField.setText("");
        fechaNacimientoTextField.setText("");
        lugarNacimientoTextField.setText("");
        entidadTextField.setText("");

        JPanel ContenedorDeBasica = (JPanel) ContenedorDePeriodos.getComponent(0);
        JPanel ContenedorDeDiversificado = (JPanel) ContenedorDePeriodos.getComponent(1);
        JPanel ContenedorDelPeriodo;
        JPanel ContenedorDelArea;

        for (int i=0; i<6; i++) {

            if (i < 3) { ContenedorDelPeriodo = (JPanel) ContenedorDeBasica.getComponent(i); }
            else { ContenedorDelPeriodo = (JPanel) ContenedorDeDiversificado.getComponent(i-3); }

            for (int j=0; j<4; j++) {ContenedorDelArea = (JPanel) ContenedorDelPeriodo.getComponent(j);

                JSpinner Nota = (JSpinner) ContenedorDelArea.getComponent(2);
                JComboBox TipoEvaluacion = (JComboBox) ContenedorDelArea.getComponent(3);
                JTextField FechaAprobacion = (JTextField) ContenedorDelArea.getComponent(1);
                JTextField CodPlantel = (JTextField) ContenedorDelArea.getComponent(4);

                Nota.setValue(0);
                TipoEvaluacion.setSelectedIndex(0);
                FechaAprobacion.setText("");
                CodPlantel.setText("");
            }
        }
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void LanzarCertificacion() {
        Certificacion dialog = new Certificacion();

        dialog.setTitle("Imprimir certificacion");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
