package com.corex.sisconot;

import com.corex.sisconot.utils.TextPrompt;

import javax.swing.*;
import java.awt.event.*;

public class Certificacion extends JDialog {
    private JPanel contentPane;
    private JButton buscarButton;
    private JButton salirButton;
    private JComboBox comboBox_Tipo_Cedula;
    private JTextField cedulaTextField;
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
                //BUSCARN NOTAS
                MostrarDatosDelAlumno();
            } else {
                Info.LanzarInfo("Alumno no encontrado");
            }
        }
    }

    private void MostrarDatosDelAlumno() {
        MostrarDatos();
        //MostrarNotas();
    }

    private void MostrarDatos() {
        nombresTextField.setText(Alumno.Nombres);
        apellidosTextField.setText(Alumno.Apellidos);
        fechaNacimientoTextField.setText(String.valueOf(Alumno.FechaDeNacimiento));
        lugarNacimientoTextField.setText(Alumno.LugarDeNacimiento);
        entidadTextField.setText(Alumno.EntidadFederal);
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
        //System.exit(0);
    }
}
