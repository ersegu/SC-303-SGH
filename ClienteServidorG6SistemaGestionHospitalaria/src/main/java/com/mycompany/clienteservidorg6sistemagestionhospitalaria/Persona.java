package com.mycompany.clienteservidorg6sistemagestionhospitalaria;
abstract class Persona {
    protected String nombre;
    protected int edad;
    protected String identificacion;

    public Persona(String nombre, int edad, String identificacion) {
        this.nombre = nombre;
        this.edad = edad;
        this.identificacion = identificacion;
    }

    public abstract void mostrarInformacion();
    public abstract String obtenerRol();

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
    
    
}
