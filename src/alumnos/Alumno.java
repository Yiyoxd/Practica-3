package alumnos;

import javax.swing.text.DateFormatter;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.Period;
import java.time.chrono.ChronoLocalDate;
import java.time.temporal.TemporalAmount;
import java.util.Date;

public class Alumno {
    private String nombre, aPaterno, aMaterno;
    private String CURP;
    private String telefono;
    private String correo;
    private boolean discapacidad;
    private Genero genero;
    private int edad;

    public Alumno(String nombre, String aPaterno, String aMaterno, String CURP, Genero genero, String telefono, String correo, boolean discapacidad) {
        this.nombre = nombre;
        this.aPaterno = aPaterno;
        this.aMaterno = aMaterno;
        this.CURP = CURP;
        this.genero = genero;
        this.edad = calcularEdad(CURP);
        this.telefono = telefono;
        this.correo = correo;
        this.discapacidad = discapacidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int calcularEdad(String curp) {
        LocalDate fechaNac = obtenerFechaNac(curp);
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fechaNac, fechaActual);
        return periodo.getYears();
    }

    private LocalDate obtenerFechaNac(String curp) {
        int anio = Integer.parseInt(curp.substring(4, 6));
        int mes = Integer.parseInt(curp.substring(6, 8));
        int dia = Integer.parseInt(curp.substring(8, 10));

        LocalDate fecha = LocalDate.of(anio + 2000, mes, dia);
        if (fecha.isAfter(LocalDate.now())) {
            fecha = LocalDate.of(anio + 1900, mes, dia);
        }
        return fecha;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Alumno").append('\n');
        sb.append("Nombre = ").append(nombre).append('\n');
        sb.append("Ap. Paterno = ").append(aPaterno).append('\n');
        sb.append("Ap. Materno = ").append(aMaterno).append('\n');
        sb.append("Edad = ").append(edad).append(" años").append('\n');
        sb.append("CURP = ").append(CURP).append('\n');
        sb.append("Teléfono = ").append(telefono).append('\n');
        sb.append("Correo = ").append(correo).append('\n');
        sb.append("Discapacidad = ").append(discapacidad ? "Sí" : "No").append('\n');
        return sb.toString();
    }

    /*public static void main(String[] args) {
        Alumno alu = new Alumno("Roberto", "Perez", "Paredez", "PARA010530HCLLDLA0", "8713950729", "yiyoaapr@gmail.com", false);
        System.out.println(alu);
    }*/
}