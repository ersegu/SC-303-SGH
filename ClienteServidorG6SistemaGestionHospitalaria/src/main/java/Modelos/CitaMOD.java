package Modelos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

public class CitaMOD {

    private int codigoCita;
    private MedicoMOD medico;
    private PacienteMOD paciente;
    private String fecha;
    private String hora;

    public CitaMOD(int codigoCita, MedicoMOD medico, PacienteMOD paciente, String fecha, String hora) {
        this.codigoCita = codigoCita;
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
    }

    public CitaMOD(MedicoMOD medico, PacienteMOD paciente, String fecha, String hora) {
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
    }

    public CitaMOD() {
    }

    public int getCodigoCita() {
        return codigoCita;
    }

    public void setCodigoCita(int codigoCita) {
        this.codigoCita = codigoCita;
    }

    public MedicoMOD getMedico() {
        return medico;
    }

    public void setMedico(MedicoMOD medico) {
        this.medico = medico;
    }

    public PacienteMOD getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteMOD paciente) {
        this.paciente = paciente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public void mostrarDetalles() {
        System.out.println("Cita con el Dr. " + medico.getNombre() + " para el paciente " + paciente.getNombre() + " el " + fecha + " a las " + hora);
    }

    public void verificarCampos() throws Exception {
        if (medico == null || medico.getCodigo() <= 0) {
            throw new Exception("Debe seleccionar un médico válido.");
        }

        if (paciente == null || paciente.getCodigo() <= 0) {
            throw new Exception("Debe seleccionar un paciente válido.");
        }

        if (fecha == null || fecha.isBlank()) {
            throw new Exception("La fecha no puede estar vacía.");
        }

        try {
            DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                    .appendValue(ChronoField.DAY_OF_MONTH)
                    .appendLiteral('/')
                    .appendValue(ChronoField.MONTH_OF_YEAR)
                    .appendLiteral('/')
                    .appendValue(ChronoField.YEAR)
                    .toFormatter();

            LocalDate.parse(fecha, formatter);
        } catch (DateTimeParseException e) {
            throw new Exception("El formato de la fecha es inválido. Usa dd/MM/yyyy, por ejemplo 08/04/2025.");
        }

        if (hora == null || hora.isBlank()) {
            throw new Exception("La hora no puede estar vacía.");
        }

        try {
            LocalTime.parse(hora, DateTimeFormatter.ofPattern("H:mm"));
        } catch (DateTimeParseException e) {
            throw new Exception("El formato de la hora es inválido. Usa HH:mm, por ejemplo 13:30.");
        }
    }
}
