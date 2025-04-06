package com.mycompany.clienteservidorg6sistemagestionhospitalaria;
class Cita {
    private Medico medico;
    private Paciente paciente;
    private String fecha;
    private String hora;

    public Cita(Medico medico, Paciente paciente, String fecha, String hora) {
        this.medico = medico;
        this.paciente = paciente;
        this.fecha = fecha;
        this.hora = hora;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
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
}
