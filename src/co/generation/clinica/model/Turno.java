package co.generation.clinica.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Turno {

    private int id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    private EstadoTurno estado;

    public Turno(
            Paciente paciente,
            Medico medico,
            LocalDateTime fechaHora,
            EstadoTurno estado) {
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = LocalDateTime.now();
        this.estado = EstadoTurno.PENDIENTE;
    }

    //Getters And Setters


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public EstadoTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoTurno estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "[" + this.getEstado() + "] " +
                this.getPaciente().getNombre() + " ? " +
                this.getMedico().getNombre() +
                " ( "+ this.getMedico().getEspecialidad() + ") " +
                "? " + this.getFechaHora();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Turno turno = (Turno) o;
        return Objects.equals(medico, turno.medico) && Objects.equals(fechaHora, turno.fechaHora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medico, fechaHora);
    }
}
