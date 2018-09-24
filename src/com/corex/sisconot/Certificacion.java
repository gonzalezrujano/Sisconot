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
    private JLabel Label_Primero_LCC;
    private JTextField Fecha_Primero_LCC;
    private JSpinner Nota_Primero_LCC;
    private JComboBox TE_Primero_LCC;
    private JTextField Cod_Primero_LCC;
    private JTextField Fecha_Primero_MTC;
    private JSpinner Nota_Primero_MTC;
    private JComboBox TE_Primero_MTC;
    private JTextField Cod_Primero_MTC;
    private JTextField Fecha_Primero_CN;
    private JSpinner Nota_Primero_CN;
    private JComboBox TE_Primero_CN;
    private JTextField Cod_Primero_CN;
    private JTextField Fecha_Primero_MAT;
    private JSpinner Nota_Primero_MAT;
    private JComboBox TE_Primero_MAT;
    private JTextField Cod_Primero_MAT;
    private JTextField Fecha_Tercero_CN;
    private JSpinner Nota_Tercero_CN;
    private JComboBox TE_Tercero_CN;
    private JTextField Cod_Tercero_CN;
    private JTextField Fecha_Tercero_MTC;
    private JSpinner Nota_Tercero_MTC;
    private JComboBox TE_Tercero_MTC;
    private JTextField Cod_Tercero_MTC;
    private JTextField Fecha_Tercero_MAT;
    private JSpinner Nota_Tercero_MAT;
    private JComboBox TE_Tercero_MAT;
    private JTextField Cod_Tercero_MAT;
    private JTextField Fecha_Tercero_LCC;
    private JSpinner Nota_Tercero_LCC;
    private JComboBox TE_Tercero_LCC;
    private JTextField Cod_Tercero_LCC;
    private JTextField Fecha_Segundo_CN;
    private JSpinner Nota_Segundo_CN;
    private JComboBox TE_Segundo_CN;
    private JTextField Cod_Segundo_CN;
    private JTextField Fecha_Segundo_MTC;
    private JSpinner Nota_Segundo_MTC;
    private JComboBox TE_Segundo_MTC;
    private JTextField Cod_Segundo_MTC;
    private JTextField Fecha_Segundo_MAT;
    private JSpinner Nota_Segundo_MAT;
    private JComboBox TE_Segundo_MAT;
    private JTextField Cod_Segundo_MAT;
    private JTextField Fecha_Segundo_LCC;
    private JSpinner Nota_Segundo_LCC;
    private JComboBox TE_Segundo_LCC;
    private JTextField Cod_Segundo_LCC;
    private JTextField Fecha_Cuarto_CN;
    private JSpinner Nota_Cuarto_CN;
    private JComboBox TE_Cuarto_CN;
    private JTextField Cod_Cuarto_CN;
    private JTextField Fecha_Cuarto_MTC;
    private JSpinner Nota_Cuarto_MTC;
    private JComboBox TE_Cuarto_MTC;
    private JTextField Cod_Cuarto_MTC;
    private JTextField Fecha_Cuarto_LCC;
    private JSpinner Nota_Cuarto_LCC;
    private JComboBox TE_Cuarto_LCC;
    private JTextField Cod_Cuarto_LCC;
    private JTextField Fecha_Cuarto_MAT;
    private JSpinner Nota_Cuarto_MAT;
    private JComboBox TE_Cuarto_MAT;
    private JTextField Cod_Cuarto_MAT;
    private JTextField Fecha_Sexto_CN;
    private JSpinner Nota_Sexto_CN;
    private JComboBox TE_Sexto_CN;
    private JTextField Cod_Sexto_CN;
    private JTextField Fecha_Sexto_MTC;
    private JSpinner Nota_Sexto_MTC;
    private JComboBox TE_Sexto_MTC;
    private JTextField Cod_Sexto_MTC;
    private JTextField Fecha_Sexto_MAT;
    private JSpinner Nota_Sexto_MAT;
    private JComboBox TE_Sexto_MAT;
    private JTextField Cod_Sexto_MAT;
    private JTextField Fecha_Sexto_LCC;
    private JSpinner Nota_Sexto_LCC;
    private JComboBox TE_Sexto_LCC;
    private JTextField Cod_Sexto_LCC;
    private JTextField Fecha_Quinto_CN;
    private JSpinner Nota_Quinto_CN;
    private JComboBox TE_Quinto_CN;
    private JTextField Cod_Quinto_CN;
    private JTextField Fecha_Quinto_MTC;
    private JSpinner Nota_Quinto_MTC;
    private JComboBox TE_Quinto_MTC;
    private JTextField Cod_Quinto_MTC;
    private JTextField Fecha_Quinto_MAT;
    private JSpinner Nota_Quinto_MAT;
    private JComboBox TE_Quinto_MAT;
    private JTextField Cod_Quinto_MAT;
    private JTextField Fecha_Quinto_LCC;
    private JSpinner Nota_Quinto_LCC;
    private JComboBox TE_Quinto_LCC;
    private JTextField Cod_Quinto_LCC;
    public static String Test = "ssss";

    public Certificacion() {
        // Definiciones de la Ventana
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buscarButton);

        DefinirTipoDeDocumento();
        ColocarPlaceholdersEnTextField();
        AgregarEscuchaALosBotones();
        DefinirEventosDeSalida();
    }

    private void DefinirTipoDeDocumento() {
        comboBox_Tipo_Cedula.addItem("V");
        comboBox_Tipo_Cedula.addItem("E");
        comboBox_Tipo_Cedula.addItem("C");
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
        String Tipo_Cedula = (String) comboBox_Tipo_Cedula.getSelectedItem();
        String Cedula = cedulaTextField.getText();

        if (Cedula.equals("")) {
            Info.LanzarInfo("Ingrese una cedula");
        } else {
            Cedula = Tipo_Cedula + "-" + Cedula;

            //BUSCAR PRIMERO EN SQL
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

        // Area de LCC
        Area = Notas.Areas.get(0);

        // Primer periodo
        Periodo = Area.get(0);
        Nota_Primero_LCC.setValue(Integer.parseInt(Periodo[0]));
        TE_Primero_LCC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Primero_LCC.setText(Periodo[2]);
        Cod_Primero_LCC.setText(Periodo[3]);

        // Segundo periodo
        Periodo = Area.get(1);
        Nota_Segundo_LCC.setValue(Integer.parseInt(Periodo[0]));
        TE_Segundo_LCC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Segundo_LCC.setText(Periodo[2]);
        Cod_Segundo_LCC.setText(Periodo[3]);

        // Tercer periodo
        Periodo = Area.get(2);
        Nota_Tercero_LCC.setValue(Integer.parseInt(Periodo[0]));
        TE_Tercero_LCC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Tercero_LCC.setText(Periodo[2]);
        Cod_Tercero_LCC.setText(Periodo[3]);

        // Cuarto periodo
        Periodo = Area.get(3);
        Nota_Cuarto_LCC.setValue(Integer.parseInt(Periodo[0]));
        TE_Cuarto_LCC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Cuarto_LCC.setText(Periodo[2]);
        Cod_Cuarto_LCC.setText(Periodo[3]);

        // Quinto periodo
        Periodo = Area.get(4);
        Nota_Quinto_LCC.setValue(Integer.parseInt(Periodo[0]));
        TE_Quinto_LCC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Quinto_LCC.setText(Periodo[2]);
        Cod_Quinto_LCC.setText(Periodo[3]);

        // Sexto periodo
        Periodo = Area.get(5);
        Nota_Sexto_LCC.setValue(Integer.parseInt(Periodo[0]));
        TE_Sexto_LCC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Sexto_LCC.setText(Periodo[2]);
        Cod_Sexto_LCC.setText(Periodo[3]);
        //-------------------------------------------------------------------------------------------------------------

        // Area de MAT
        Area = Notas.Areas.get(1);

        // Primer periodo
        Periodo = Area.get(0);
        Nota_Primero_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Primero_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Primero_MAT.setText(Periodo[2]);
        Cod_Primero_MAT.setText(Periodo[3]);

        // Segundo periodo
        Periodo = Area.get(1);
        Nota_Segundo_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Segundo_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Segundo_MAT.setText(Periodo[2]);
        Cod_Segundo_MAT.setText(Periodo[3]);

        // Tercer periodo
        Periodo = Area.get(2);
        Nota_Tercero_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Tercero_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Tercero_MAT.setText(Periodo[2]);
        Cod_Tercero_MAT.setText(Periodo[3]);

        // Cuarto periodo
        Periodo = Area.get(3);
        Nota_Cuarto_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Cuarto_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Cuarto_MAT.setText(Periodo[2]);
        Cod_Cuarto_MAT.setText(Periodo[3]);

        // Quinto periodo
        Periodo = Area.get(4);
        Nota_Quinto_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Quinto_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Quinto_MAT.setText(Periodo[2]);
        Cod_Quinto_MAT.setText(Periodo[3]);

        // Sexto periodo
        Periodo = Area.get(5);
        Nota_Sexto_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Sexto_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Sexto_MAT.setText(Periodo[2]);
        Cod_Sexto_MAT.setText(Periodo[3]);
        //-------------------------------------------------------------------------------------------------------------

        // Area de MAT
        Area = Notas.Areas.get(1);

        // Primer periodo
        Periodo = Area.get(0);
        Nota_Primero_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Primero_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Primero_MAT.setText(Periodo[2]);
        Cod_Primero_MAT.setText(Periodo[3]);

        // Segundo periodo
        Periodo = Area.get(1);
        Nota_Segundo_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Segundo_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Segundo_MAT.setText(Periodo[2]);
        Cod_Segundo_MAT.setText(Periodo[3]);

        // Tercer periodo
        Periodo = Area.get(2);
        Nota_Tercero_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Tercero_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Tercero_MAT.setText(Periodo[2]);
        Cod_Tercero_MAT.setText(Periodo[3]);

        // Cuarto periodo
        Periodo = Area.get(3);
        Nota_Cuarto_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Cuarto_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Cuarto_MAT.setText(Periodo[2]);
        Cod_Cuarto_MAT.setText(Periodo[3]);

        // Quinto periodo
        Periodo = Area.get(4);
        Nota_Quinto_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Quinto_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Quinto_MAT.setText(Periodo[2]);
        Cod_Quinto_MAT.setText(Periodo[3]);

        // Sexto periodo
        Periodo = Area.get(5);
        Nota_Sexto_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Sexto_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Sexto_MAT.setText(Periodo[2]);
        Cod_Sexto_MAT.setText(Periodo[3]);
        //-------------------------------------------------------------------------------------------------------------

        // Area de MAT
        Area = Notas.Areas.get(1);

        // Primer periodo
        Periodo = Area.get(0);
        Nota_Primero_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Primero_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Primero_MAT.setText(Periodo[2]);
        Cod_Primero_MAT.setText(Periodo[3]);

        // Segundo periodo
        Periodo = Area.get(1);
        Nota_Segundo_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Segundo_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Segundo_MAT.setText(Periodo[2]);
        Cod_Segundo_MAT.setText(Periodo[3]);

        // Tercer periodo
        Periodo = Area.get(2);
        Nota_Tercero_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Tercero_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Tercero_MAT.setText(Periodo[2]);
        Cod_Tercero_MAT.setText(Periodo[3]);

        // Cuarto periodo
        Periodo = Area.get(3);
        Nota_Cuarto_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Cuarto_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Cuarto_MAT.setText(Periodo[2]);
        Cod_Cuarto_MAT.setText(Periodo[3]);

        // Quinto periodo
        Periodo = Area.get(4);
        Nota_Quinto_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Quinto_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Quinto_MAT.setText(Periodo[2]);
        Cod_Quinto_MAT.setText(Periodo[3]);

        // Sexto periodo
        Periodo = Area.get(5);
        Nota_Sexto_MAT.setValue(Integer.parseInt(Periodo[0]));
        TE_Sexto_MAT.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Sexto_MAT.setText(Periodo[2]);
        Cod_Sexto_MAT.setText(Periodo[3]);
        //-------------------------------------------------------------------------------------------------------------

        // Area de MTC
        Area = Notas.Areas.get(2);

        // Primer periodo
        Periodo = Area.get(0);
        Nota_Primero_MTC.setValue(Integer.parseInt(Periodo[0]));
        TE_Primero_MTC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Primero_MTC.setText(Periodo[2]);
        Cod_Primero_MTC.setText(Periodo[3]);

        // Segundo periodo
        Periodo = Area.get(1);
        Nota_Segundo_MTC.setValue(Integer.parseInt(Periodo[0]));
        TE_Segundo_MTC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Segundo_MTC.setText(Periodo[2]);
        Cod_Segundo_MTC.setText(Periodo[3]);

        // Tercer periodo
        Periodo = Area.get(2);
        Nota_Tercero_MTC.setValue(Integer.parseInt(Periodo[0]));
        TE_Tercero_MTC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Tercero_MTC.setText(Periodo[2]);
        Cod_Tercero_MTC.setText(Periodo[3]);

        // Cuarto periodo
        Periodo = Area.get(3);
        Nota_Cuarto_MTC.setValue(Integer.parseInt(Periodo[0]));
        TE_Cuarto_MTC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Cuarto_MTC.setText(Periodo[2]);
        Cod_Cuarto_MTC.setText(Periodo[3]);

        // Quinto periodo
        Periodo = Area.get(4);
        Nota_Quinto_MTC.setValue(Integer.parseInt(Periodo[0]));
        TE_Quinto_MTC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Quinto_MTC.setText(Periodo[2]);
        Cod_Quinto_MTC.setText(Periodo[3]);

        // Sexto periodo
        Periodo = Area.get(5);
        Nota_Sexto_MTC.setValue(Integer.parseInt(Periodo[0]));
        TE_Sexto_MTC.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Sexto_MTC.setText(Periodo[2]);
        Cod_Sexto_MTC.setText(Periodo[3]);
        //-------------------------------------------------------------------------------------------------------------

        // Area de MAT
        Area = Notas.Areas.get(3);

        // Primer periodo
        Periodo = Area.get(0);
        Nota_Primero_CN.setValue(Integer.parseInt(Periodo[0]));
        TE_Primero_CN.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Primero_CN.setText(Periodo[2]);
        Cod_Primero_CN.setText(Periodo[3]);

        // Segundo periodo
        Periodo = Area.get(1);
        Nota_Segundo_CN.setValue(Integer.parseInt(Periodo[0]));
        TE_Segundo_CN.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Segundo_CN.setText(Periodo[2]);
        Cod_Segundo_CN.setText(Periodo[3]);

        // Tercer periodo
        Periodo = Area.get(2);
        Nota_Tercero_CN.setValue(Integer.parseInt(Periodo[0]));
        TE_Tercero_CN.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Tercero_CN.setText(Periodo[2]);
        Cod_Tercero_CN.setText(Periodo[3]);

        // Cuarto periodo
        Periodo = Area.get(3);
        Nota_Cuarto_CN.setValue(Integer.parseInt(Periodo[0]));
        TE_Cuarto_CN.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Cuarto_CN.setText(Periodo[2]);
        Cod_Cuarto_CN.setText(Periodo[3]);

        // Quinto periodo
        Periodo = Area.get(4);
        Nota_Quinto_CN.setValue(Integer.parseInt(Periodo[0]));
        TE_Quinto_CN.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Quinto_CN.setText(Periodo[2]);
        Cod_Quinto_CN.setText(Periodo[3]);

        // Sexto periodo
        Periodo = Area.get(5);
        Nota_Sexto_CN.setValue(Integer.parseInt(Periodo[0]));
        TE_Sexto_CN.setSelectedIndex(DeterminarPosicionDeLaEvaluacion(Periodo[1]));
        Fecha_Sexto_CN.setText(Periodo[2]);
        Cod_Sexto_CN.setText(Periodo[3]);
        //-------------------------------------------------------------------------------------------------------------
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

    private void LimpiarDatos() {
        Alumno.VaciarDatos();
        Notas.VaciarDatos();

        nombresTextField.setText("");
        apellidosTextField.setText("");
        fechaNacimientoTextField.setText("");
        lugarNacimientoTextField.setText("");
        entidadTextField.setText("");

        Nota_Primero_LCC.setValue(0);
        TE_Primero_LCC.setSelectedIndex(0);
        Fecha_Primero_LCC.setText("");
        Cod_Primero_LCC.setText("");
        //----------------------------------------------------------------------------
        Nota_Segundo_LCC.setValue(0);
        TE_Segundo_LCC.setSelectedIndex(0);
        Fecha_Segundo_LCC.setText("");
        Cod_Segundo_LCC.setText("");
        //----------------------------------------------------------------------------
        Nota_Tercero_LCC.setValue(0);
        TE_Tercero_LCC.setSelectedIndex(0);
        Fecha_Tercero_LCC.setText("");
        Cod_Tercero_LCC.setText("");
        //----------------------------------------------------------------------------
        Nota_Cuarto_LCC.setValue(0);
        TE_Cuarto_LCC.setSelectedIndex(0);
        Fecha_Cuarto_LCC.setText("");
        Cod_Cuarto_LCC.setText("");
        //----------------------------------------------------------------------------
        Nota_Quinto_LCC.setValue(0);
        TE_Quinto_LCC.setSelectedIndex(0);
        Fecha_Quinto_LCC.setText("");
        Cod_Quinto_LCC.setText("");
        //----------------------------------------------------------------------------
        Nota_Sexto_LCC.setValue(0);
        TE_Sexto_LCC.setSelectedIndex(0);
        Fecha_Sexto_LCC.setText("");
        Cod_Sexto_LCC.setText("");
        //-------------------------------------------------------------------------------------------------------------
        Nota_Primero_MAT.setValue(0);
        TE_Primero_MAT.setSelectedIndex(0);
        Fecha_Primero_MAT.setText("");
        Cod_Primero_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Segundo_MAT.setValue(0);
        TE_Segundo_MAT.setSelectedIndex(0);
        Fecha_Segundo_MAT.setText("");
        Cod_Segundo_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Tercero_MAT.setValue(0);
        TE_Tercero_MAT.setSelectedIndex(0);
        Fecha_Tercero_MAT.setText("");
        Cod_Tercero_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Cuarto_MAT.setValue(0);
        TE_Cuarto_MAT.setSelectedIndex(0);
        Fecha_Cuarto_MAT.setText("");
        Cod_Cuarto_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Quinto_MAT.setValue(0);
        TE_Quinto_MAT.setSelectedIndex(0);
        Fecha_Quinto_MAT.setText("");
        Cod_Quinto_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Sexto_MAT.setValue(0);
        TE_Sexto_MAT.setSelectedIndex(0);
        Fecha_Sexto_MAT.setText("");
        Cod_Sexto_MAT.setText("");
        //-------------------------------------------------------------------------------------------------------------
        Nota_Primero_MAT.setValue(0);
        TE_Primero_MAT.setSelectedIndex(0);
        Fecha_Primero_MAT.setText("");
        Cod_Primero_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Segundo_MAT.setValue(0);
        TE_Segundo_MAT.setSelectedIndex(0);
        Fecha_Segundo_MAT.setText("");
        Cod_Segundo_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Tercero_MAT.setValue(0);
        TE_Tercero_MAT.setSelectedIndex(0);
        Fecha_Tercero_MAT.setText("");
        Cod_Tercero_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Cuarto_MAT.setValue(0);
        TE_Cuarto_MAT.setSelectedIndex(0);
        Fecha_Cuarto_MAT.setText("");
        Cod_Cuarto_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Quinto_MAT.setValue(0);
        TE_Quinto_MAT.setSelectedIndex(0);
        Fecha_Quinto_MAT.setText("");
        Cod_Quinto_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Sexto_MAT.setValue(0);
        TE_Sexto_MAT.setSelectedIndex(0);
        Fecha_Sexto_MAT.setText("");
        Cod_Sexto_MAT.setText("");
        //-------------------------------------------------------------------------------------------------------------        
        Nota_Primero_MAT.setValue(0);
        TE_Primero_MAT.setSelectedIndex(0);
        Fecha_Primero_MAT.setText("");
        Cod_Primero_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Segundo_MAT.setValue(0);
        TE_Segundo_MAT.setSelectedIndex(0);
        Fecha_Segundo_MAT.setText("");
        Cod_Segundo_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Tercero_MAT.setValue(0);
        TE_Tercero_MAT.setSelectedIndex(0);
        Fecha_Tercero_MAT.setText("");
        Cod_Tercero_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Cuarto_MAT.setValue(0);
        TE_Cuarto_MAT.setSelectedIndex(0);
        Fecha_Cuarto_MAT.setText("");
        Cod_Cuarto_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Quinto_MAT.setValue(0);
        TE_Quinto_MAT.setSelectedIndex(0);
        Fecha_Quinto_MAT.setText("");
        Cod_Quinto_MAT.setText("");
        //----------------------------------------------------------------------------
        Nota_Sexto_MAT.setValue(0);
        TE_Sexto_MAT.setSelectedIndex(0);
        Fecha_Sexto_MAT.setText("");
        Cod_Sexto_MAT.setText("");
        //-------------------------------------------------------------------------------------------------------------
        Nota_Primero_MTC.setValue(0);
        TE_Primero_MTC.setSelectedIndex(0);
        Fecha_Primero_MTC.setText("");
        Cod_Primero_MTC.setText("");
        //----------------------------------------------------------------------------
        Nota_Segundo_MTC.setValue(0);
        TE_Segundo_MTC.setSelectedIndex(0);
        Fecha_Segundo_MTC.setText("");
        Cod_Segundo_MTC.setText("");
        //----------------------------------------------------------------------------
        Nota_Tercero_MTC.setValue(0);
        TE_Tercero_MTC.setSelectedIndex(0);
        Fecha_Tercero_MTC.setText("");
        Cod_Tercero_MTC.setText("");
        //----------------------------------------------------------------------------
        Nota_Cuarto_MTC.setValue(0);
        TE_Cuarto_MTC.setSelectedIndex(0);
        Fecha_Cuarto_MTC.setText("");
        Cod_Cuarto_MTC.setText("");
        //----------------------------------------------------------------------------
        Nota_Quinto_MTC.setValue(0);
        TE_Quinto_MTC.setSelectedIndex(0);
        Fecha_Quinto_MTC.setText("");
        Cod_Quinto_MTC.setText("");
        //----------------------------------------------------------------------------
        Nota_Sexto_MTC.setValue(0);
        TE_Sexto_MTC.setSelectedIndex(0);
        Fecha_Sexto_MTC.setText("");
        Cod_Sexto_MTC.setText("");
        //-------------------------------------------------------------------------------------------------------------
        Nota_Primero_CN.setValue(0);
        TE_Primero_CN.setSelectedIndex(0);
        Fecha_Primero_CN.setText("");
        Cod_Primero_CN.setText("");
        //----------------------------------------------------------------------------
        Nota_Segundo_CN.setValue(0);
        TE_Segundo_CN.setSelectedIndex(0);
        Fecha_Segundo_CN.setText("");
        Cod_Segundo_CN.setText("");
        //----------------------------------------------------------------------------
        Nota_Tercero_CN.setValue(0);
        TE_Tercero_CN.setSelectedIndex(0);
        Fecha_Tercero_CN.setText("");
        Cod_Tercero_CN.setText("");
        //----------------------------------------------------------------------------
        Nota_Cuarto_CN.setValue(0);
        TE_Cuarto_CN.setSelectedIndex(0);
        Fecha_Cuarto_CN.setText("");
        Cod_Cuarto_CN.setText("");
        //----------------------------------------------------------------------------
        Nota_Quinto_CN.setValue(0);
        TE_Quinto_CN.setSelectedIndex(0);
        Fecha_Quinto_CN.setText("");
        Cod_Quinto_CN.setText("");
        //----------------------------------------------------------------------------
        Nota_Sexto_CN.setValue(0);
        TE_Sexto_CN.setSelectedIndex(0);
        Fecha_Sexto_CN.setText("");
        Cod_Sexto_CN.setText("");
        //-------------------------------------------------------------------------------------------------------------
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void LanzarCertificacion() {
        Certificacion dialog = new Certificacion();

        /*Class c = dialog.getClass();
        try {
            //Field field = c.getField("cedulaTextField");
            //Method method = (Method) JTextField.class.getMethod("setText", "Pruebaaa");
            //Object returnValue = method.invoke(null, "parameter-value1");

            //Field chap = c.getDeclaredField("cedulaTextField");
            //chap.setText("Prueba");
            //Object objeto = new Object();
            System.out.println("Valor: " + field);
            field.
            //objeto.
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }*/
        dialog.setTitle("Imprimir certificacion");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
