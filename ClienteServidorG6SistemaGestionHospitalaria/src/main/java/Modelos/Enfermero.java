package Modelos;

class Enfermero extends Empleado {
    private int experiencia;

    public Enfermero(String nombre, String apellido, String fechaNacimiento, String identificacion, double salario, int experiencia) {
        super(nombre, apellido, fechaNacimiento, identificacion, salario);
        
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser vacío.");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser vacío.");
        }
        if (fechaNacimiento == null || fechaNacimiento.isEmpty()) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser vacía.");
        }
        if (identificacion == null || identificacion.isEmpty()) {
            throw new IllegalArgumentException("La identificación no puede ser vacía.");
        }
        if (salario < 0) {
            throw new IllegalArgumentException("El salario no puede ser negativo.");
        }
        if (experiencia < 0) {
            throw new IllegalArgumentException("La experiencia no puede ser negativa.");
        }

        this.experiencia = experiencia;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        if (experiencia < 0) {
            throw new IllegalArgumentException("La experiencia no puede ser negativa.");
        }
        this.experiencia = experiencia;
    }

    @Override
    public void mostrarInformacion() {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalStateException("No se puede mostrar la información, el nombre no está definido.");
        }
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
