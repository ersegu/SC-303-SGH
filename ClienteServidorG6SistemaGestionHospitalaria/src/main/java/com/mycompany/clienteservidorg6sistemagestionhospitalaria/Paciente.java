package com.mycompany.clienteservidorg6sistemagestionhospitalaria;
class Paciente extends Persona {
    private String historialMedico;

    public Paciente(String nombre, int edad, String identificacion, String historialMedico) {
        super(nombre, edad, identificacion);
        this.historialMedico = historialMedico;
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
}
