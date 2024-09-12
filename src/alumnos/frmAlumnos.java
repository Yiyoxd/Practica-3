package alumnos;

import javax.print.attribute.standard.PrinterName;
import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;

public class frmAlumnos extends JFrame{
    private static final String path = "C:\\Users\\yiyoa\\Downloads\\alumno.txt";
    private JTextField jTFNombre;
    private JTextField jTFAMaterno;
    private JTextField jTFAPaterno;
    private JTextField jTFTelefono;
    private JTextField jTFCorreo;
    private JTextField jTFCurp;
    private JCheckBox cuentaConAltunaDiscapacidadCheckBox;
    private JButton btnAceptar;
    private JButton btnCancelar;
    private JLabel txtNombre;
    private JLabel txtApPaterno;
    private JLabel txtApMaterno;
    private JLabel txt;
    private JComboBox<Genero> cbSexo;
    private JLabel txtCorreo;
    private JLabel txtCurp;
    private JLabel txtSexo;
    private JPanel panel;

    public frmAlumnos() {
        setTitle("Alumnos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel);
        setResizable(false);
        poblarComboBox();
        btnAceptar.addActionListener(e -> {
            Alumno alumno = enviarDatos();
            System.out.println(alumno);
            guardarDatos(alumno);
        });
        btnCancelar.addActionListener(e -> borrar());
    }

    private Alumno enviarDatos() {
        String nombre = txtNombre.getText();
        String apPaterno = jTFAPaterno.getText();
        String apMaterno = jTFAMaterno.getText();
        String CURP = jTFCurp.getText();
        Genero genero = (Genero)cbSexo.getSelectedItem();
        String telefono = jTFTelefono.getText();
        String correo = jTFCorreo.getText();
        boolean discapacidad = cuentaConAltunaDiscapacidadCheckBox.isSelected();
        return new Alumno(nombre, apPaterno, apMaterno, CURP, genero, telefono, correo, discapacidad);
    }

    private void guardarDatos(Alumno alumno) {
        Path ruta = Path.of(path);
        String contenido = alumno.toString();
        try(FileWriter writer = new FileWriter(ruta.toFile(), true)) {
            PrintWriter printer = new PrintWriter(writer);
            printer.println(contenido);
        } catch(Exception e) {
            System.out.println("error");
        }
    }

    private void borrar() {
        jTFNombre.setText("");
        jTFAPaterno.setText("");
        jTFAMaterno.setText("");
        jTFCorreo.setText("");
        jTFTelefono.setText("");
        jTFCurp.setText("");
    }

    private void poblarComboBox() {
        DefaultComboBoxModel<Genero> cb = (DefaultComboBoxModel<Genero>)cbSexo.getModel();
        for (Genero genero : Genero.values()) {
            cb.addElement(genero);
        }
    }
}
