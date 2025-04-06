package com.mycompany.clienteservidorg6sistemagestionhospitalaria;

abstract class Empleado extends Persona {

    protected double salario;

    public Empleado(String nombre, int edad, String identificacion, double salario) {
        super(nombre, edad, identificacion);
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
    public double getSalario() {
        return salario;
    }
    
    public abstract double calcularSalario();

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
