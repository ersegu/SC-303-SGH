package Modelos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class PacienteMOD extends Persona {

    private int codigo;
    private String direccion;
    private String telefono;
    private String correo;
    private String historialMedico;

    public PacienteMOD(int codigo, String apellido, String fechaNacimiento,
            String direccion, String telefono, String correo, String historialMedico,
            String nombre, String identificacion) {
        super(nombre, apellido, fechaNacimiento, identificacion);
        this.codigo = codigo;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.historialMedico = historialMedico;
    }

    public PacienteMOD() {
    }

    public PacienteMOD(String apellido, String fechaNacimiento, String direccion, String telefono, String correo, String historialMedico, String nombre, String identificacion) {
        super(nombre, apellido, fechaNacimiento, identificacion);
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.historialMedico = historialMedico;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHistorialMedico() {
        return historialMedico;
    }

    public void setHistorialMedico(String historialMedico) {
        this.historialMedico = historialMedico;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Paciente: " + nombre + "\nHistorial: " + historialMedico);
    }

    @Override
    public String obtenerRol() {
        return "Paciente";
    }

    public String nombre_completo() {
        return nombre + " " + apellido;
    }

    public void verificarCampos() throws Exception {
        if (nombre == null || nombre.isBlank()) {
            throw new Exception("El Nombre no puede estar vacío");
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
        if (direccion == null || direccion.isBlank()) {
            throw new Exception("La dirección no puede estar vacía");
        }
        if (telefono == null || telefono.isBlank()) {
            throw new Exception("El teléfono no puede estar vacío");
        }
        if (!telefono.matches("[0-9\\- ]+")) {
            throw new Exception("El teléfono solo puede contener números");
        }
        if (correo == null || correo.isBlank()) {
            throw new Exception("El correo no puede estar vacío");
        }
        if (identificacion == null || identificacion.isBlank()) {
            throw new Exception("La identificación no puede estar vacía");
        }
        if (!identificacion.matches("[0-9\\- ]+")) {
            throw new Exception("La identificación solo puede contener números");
        }
        if (historialMedico == null || historialMedico.isBlank()) {
            throw new Exception("El historial médico no puede estar vacío");
        }
    }
}
