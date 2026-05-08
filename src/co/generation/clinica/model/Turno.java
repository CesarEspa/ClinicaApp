package co.generation.clinica.model;

import co.generation.clinica.interfaces.Registrable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Turno implements Registrable {

    private int id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    private EstadoTurno estado;

    // Constructor con ID (Para cargar desde CSV)
    public Turno(int id, Paciente paciente, Medico medico, LocalDateTime fechaHora, EstadoTurno estado) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    // Constructor sin ID (Para nuevos registros manuales)
    public Turno(Paciente paciente, Medico medico, LocalDateTime fechaHora) {
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estado = EstadoTurno.PENDIENTE; // Estado por defecto al crear
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public EstadoTurno getEstado() { return estado; }
    public void setEstado(EstadoTurno estado) { this.estado = estado; }

    @Override
    public String toString() {
        return "[" + this.estado + "] " +
                this.paciente.getNombre() + " -> " +
                this.medico.getNombre() +
                " ("+ this.medico.getEspecialidad() + ") - " +
                this.fechaHora;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return Objects.equals(medico, turno.medico) && Objects.equals(fechaHora, turno.fechaHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medico, fechaHora);
    }

    // --- Implementación de Registrable ---
    @Override
    public String getDatosRegistro() {
        return "Turno registrado: " + this.toString();
    }

    @Override
    public boolean esValido() {
        return paciente != null && medico != null && fechaHora != null && estado != null;
    }
}