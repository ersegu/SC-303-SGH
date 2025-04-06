package com.mycompany.clienteservidorg6sistemagestionhospitalaria;
class Habitacion {
    private int numero;
    private boolean disponible;
    private Paciente paciente;

    public Habitacion(int numero) {
        this.numero = numero;
        this.disponible = true;
        this.paciente = null;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    

    public void asignarPaciente(Paciente paciente) {
        if (disponible) {
            disponible = false;
            this.paciente = paciente;
            System.out.println("Habitacion " + numero + " asignada");
        } else {
            System.out.println("La habitacion " + numero + " no esta disponible");
        }
    }

    public void liberarHabitacion() {
        disponible = true;
        this.paciente = null;
        System.out.println("Habitacion " + numero + " liberada");
    }
}
