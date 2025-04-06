package com.mycompany.clienteservidorg6sistemagestionhospitalaria;
class Enfermero extends Empleado {
    private int experiencia;

    public Enfermero(String nombre, int edad, String identificacion, double salario, int experiencia) {
        super(nombre, edad, identificacion, salario);
        this.experiencia = experiencia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Enfermero: " + nombre + ", Experiencia: " + experiencia + " años");
    }

    @Override
    public String obtenerRol() {
        return "Enfermero";
    }

    @Override
    public double calcularSalario() {
        return salario + (experiencia * 50);
    }
}

