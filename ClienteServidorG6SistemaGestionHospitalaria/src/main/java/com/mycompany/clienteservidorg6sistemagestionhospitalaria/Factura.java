package com.mycompany.clienteservidorg6sistemagestionhospitalaria;
class Factura {
    private Cita cita;
    private Paciente paciente;
    private double monto;

    public Factura(Cita cita, double monto) {
        this.cita = cita;
        this.paciente = cita.getPaciente();
        this.monto = monto;
    }

    public Cita getCita() {
        return cita;
    }

    public void setCita(Cita cita) {
        this.cita = cita;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void mostrarFactura() {
        System.out.println("Factura para " + paciente.getNombre() + ": $" + monto);
    }
}
