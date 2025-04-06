package com.mycompany.clienteservidorg6sistemagestionhospitalaria;
class Medico extends Empleado {
    private String especialidad;

    public Medico(String nombre, int edad, String identificacion, double salario, String especialidad) {
        super(nombre, edad, identificacion, salario);
        this.especialidad = especialidad;
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
}
