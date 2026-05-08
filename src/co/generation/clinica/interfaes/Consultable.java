package co.generation.clinica.interfaes;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;

import java.time.LocalDateTime;
import java.util.List;

public interface Consultable {
    List listarTurnosDelDia(LocalDateTime ld);
    List buscarPorMedico(Medico medico);
    List buscarPorPaciente(Paciente paciente);
}