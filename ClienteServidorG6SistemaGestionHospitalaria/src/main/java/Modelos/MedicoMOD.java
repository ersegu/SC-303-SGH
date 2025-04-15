package Modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class MedicoMOD extends Empleado {

    private int codigo;
    private String especialidad;
    private String disponible;

    public MedicoMOD(int codigo, String nombre, String apellido, String fechaNacimiento, String identificacion, double salario, String especialidad, String disponible) {
        super(nombre, apellido, fechaNacimiento, identificacion, salario);
        this.codigo = codigo;
        this.especialidad = especialidad;
        this.disponible = disponible;
    }

    public MedicoMOD(String nombre, String apellido, String fechaNacimiento, String identificacion, double salario, String especialidad, String disponible) {
        super(nombre, apellido, fechaNacimiento, identificacion, salario);
        this.especialidad = especialidad;
        this.disponible = disponible;
    }

    public MedicoMOD() {
        super();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDisponible() {
        return disponible;
    }

    public void setDisponible(String disponible) {
        this.disponible = disponible;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Médico: " + nombre + ", Especialidad: " + especialidad);
    }

    @Override
    public String obtenerRol() {
        return "Médico";
    }

    @Override
    public double calcularSalario() {
        return salario + 500;
    }

    public String nombre_completo() {
        return nombre + " " + apellido;
    }

    public void verificarCampos() throws Exception {
        if (nombre == null || nombre.isBlank()) {
            throw new Exception("El nombre no puede estar vacío");
        }
        if (apellido == null || apellido.isBlank()) {
            throw new Exception("El apellido no puede estar vacío");
        }
        if (fechaNacimiento == null || fechaNacimiento.isBlank()) {
            throw new Exception("La fecha de nacimiento no puede estar vacía");
        }

        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendValue(ChronoField.DAY_OF_MONTH)
                    .appendLiteral('/')
                    .appendValue(ChronoField.MONTH_OF_YEAR)
                    .appendLiteral('/')
                    .appendValue(ChronoField.YEAR)
                    .toFormatter();

            LocalDate.parse(fechaNacimiento, formatter);
        } catch (DateTimeParseException e) {
            throw new Exception("El formato de la fecha de nacimiento es inválido. Usa dd/MM/yyyy, por ejemplo 9/9/2025 o 09/09/2025");
        }

        if (identificacion == null || identificacion.isBlank()) {
            throw new Exception("La identificación no puede estar vacía");
        }
        if (!identificacion.matches("[0-9\\- ]+")) {
            throw new Exception("La identificación solo puede contener números");
        }

        if (especialidad == null || especialidad.equals("Seleccionar") || especialidad.isBlank()) {
            throw new Exception("La especialidad no puede estar vacía ni sin seleccionar");
        }

        if (disponible == null || disponible.equals("Seleccionar") || disponible.isBlank()) {
            throw new Exception("La disponibilidad no puede estar vacía ni sin seleccionar");
        }

        if (salario <= 0) {
            throw new Exception("El salario debe ser mayor a 0");
        }
    }
}
