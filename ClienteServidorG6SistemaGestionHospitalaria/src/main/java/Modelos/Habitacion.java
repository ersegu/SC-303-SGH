package Modelos;

class Habitacion {
    private int numero;
    private boolean disponible;
    private PacienteMOD paciente;

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

    public PacienteMOD getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteMOD paciente) {
        this.paciente = paciente;
    }

    public void asignarPaciente(PacienteMOD paciente) throws Exception {
        if (disponible) {
            disponible = false;
            this.paciente = paciente;
            System.out.println("Habitacion " + numero + " asignada");
        } else {
            throw new Exception("La habitacion " + numero + " no esta disponible");
        }
    }

    public void liberarHabitacion() throws Exception {
        if (paciente == null) {
            throw new Exception("La habitacion " + numero + " no tiene un paciente asignado");
        }
        disponible = true;
        this.paciente = null;
        System.out.println("Habitacion " + numero + " liberada");
    }
}
