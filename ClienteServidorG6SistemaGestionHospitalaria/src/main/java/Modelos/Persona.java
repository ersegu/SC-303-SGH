package Modelos;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

abstract class Persona {

    protected String nombre;
    protected String apellido;
    protected String fechaNacimiento;
    protected String identificacion;

    public Persona(String nombre, String apellido, String fechaNacimiento, String identificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.identificacion = identificacion;
    }

    public Persona() {
    }

    public abstract void mostrarInformacion();

    public abstract String obtenerRol();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getEdad() {
        try {
            LocalDate nacimiento = LocalDate.parse(this.fechaNacimiento);
            LocalDate hoy = LocalDate.now();
            return Period.between(nacimiento, hoy).getYears();
        } catch (Exception e) {
            return -1;
        }
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        if (identificacion == null || identificacion.isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede ser vacia");
        }
        this.identificacion = identificacion;
    }
}
