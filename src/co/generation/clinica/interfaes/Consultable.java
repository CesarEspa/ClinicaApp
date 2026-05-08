package co.generation.clinica.interfaces;

import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;

import java.time.LocalDateTime;
import java.util.List;

public interface Consultable {
    List<Turno> listarTurnosDelDia(LocalDateTime ld);
    List<Turno> buscarPorMedico(Medico medico);
    List<Turno> buscarPorPaciente(Paciente paciente);
}