package Modelos;

class Factura {
    private CitaMOD cita;
    private PacienteMOD paciente;
    private double monto;

    public Factura(CitaMOD cita, double monto) {
        if (cita == null || cita.getPaciente() == null) {
            throw new IllegalArgumentException("La cita o el paciente no pueden ser nulos");
        }
        if (monto < 0) {
            throw new IllegalArgumentException("El monto de la factura no puede ser negativo");
        }
        this.cita = cita;
        this.paciente = cita.getPaciente();
        this.monto = monto;
    }

    public CitaMOD getCita() {
        return cita;
    }

    public void setCita(CitaMOD cita) {
        this.cita = cita;
    }

    public PacienteMOD getPaciente() {
        return paciente;
    }

    public void setPaciente(PacienteMOD paciente) {
        this.paciente = paciente;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        if (monto < 0) {
            throw new IllegalArgumentException("El monto de la factura no puede ser negativo");
        }
        this.monto = monto;
    }

    public void mostrarFactura() {
        if (paciente == null || paciente.getNombre() == null || paciente.getNombre().isEmpty()) {
            throw new IllegalStateException("No se puede mostrar la factura, el paciente no tiene nombre");
        }
        System.out.println("Factura para " + paciente.getNombre() + ": $" + monto);
    }
}
