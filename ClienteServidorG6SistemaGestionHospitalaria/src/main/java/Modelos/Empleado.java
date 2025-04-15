package Modelos;

abstract class Empleado extends Persona {

    protected double salario;

    public Empleado(String nombre, String apellido, String fechaNacimiento, String identificacion, double salario) {
        super(nombre, apellido, fechaNacimiento, identificacion);
        this.salario = salario;
    }

    public Empleado() {
    }
    

    public double getSalario() {
        return salario;
    }

    public abstract double calcularSalario();

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
