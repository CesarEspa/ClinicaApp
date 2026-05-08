package co.generation.clinica.service;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.EstadoTurno;
import co.generation.clinica.model.Medico;
import co.generation.clinica.model.Paciente;
import co.generation.clinica.model.Turno;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClinicaService implements Consultable {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Medico> medicos = new ArrayList<>();
    private List<Turno> turnos = new ArrayList<>();


    public List<Paciente> getPacientes() { return pacientes; }
    public List<Medico> getMedicos() { return medicos; }
    public List<Turno> getTurnos() { return turnos; }

    public void registrarPaciente(Paciente p) {
        if (!p.esValido()) {
            System.out.println("Paciente no registrado, datos inválidos.");
            return;
        }
        if (pacientes.contains(p)) {
            System.out.println("Paciente existente en el sistema.");
            return;
        }
        int max = 0;
        for (Paciente paciente : pacientes) {
            if (paciente.getId() > max) {
                max = paciente.getId();
            }
        }
        p.setId(max + 1);
        pacientes.add(p);
        System.out.println(p.getDatosRegistro());
    }

    public Paciente buscarPorCedula(String cedula) {
        for (Paciente paciente : pacientes) {
            if (paciente.getCedula().equals(cedula)) {
                return paciente;
            }
        }
        return null;
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados en el sistema.");
            return;
        }
        List<Paciente> personas = new ArrayList<>(pacientes);
        personas.sort(Comparator.comparing(Paciente::getApellido).thenComparing(Paciente::getNombre));
        System.out.println("\n--- LISTADO DE PACIENTES ---");
        for (Paciente p : personas) {
            System.out.println(p.toString());
        }
    }

    public void registrarMedico(Medico m) {

        if (!m.esValido()) {
            System.out.println("Los datos del médico son inválidos o están incompletos");
            return;
        }
        if (medicos.contains(m)) {
            System.out.println("Médico ya existente en el sistema");
            return;
        }
        int max = 0;
        for (Medico medico : medicos) {
            if (medico.getId() > max) {
                max = medico.getId();
            }
        }
        m.setId(max + 1);
        medicos.add(m);
        System.out.println(m.getDatosRegistro());
    }

    public Medico buscarPorNombreApellido(String nombre, String apellido) {
        for (Medico medico : medicos) {
            if (medico.getApellido().equalsIgnoreCase(apellido) && medico.getNombre().equalsIgnoreCase(nombre)) {
                return medico;
            }
        }
        return null;
    }

    public void listarMedicos(){
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados en el sistema.");
            return;
        }
        List<Medico> especialidad = new ArrayList<>(medicos);
        especialidad.sort(Comparator.comparing(Medico::getEspecialidad).thenComparing(Medico::getApellido));
        System.out.println("\n--- LISTADO DE MEDICOS ---");
        for (Medico p : especialidad) {
            System.out.println(p.toString());
        }
    }

    public void asignarTurno(Turno t){
        Paciente existPacien = buscarPorCedula(t.getPaciente().getCedula());
        if (existPacien == null) {
            System.out.println("Paciente no encontrado: " + t.getPaciente().getCedula());
            return;
        }
        t.setPaciente(existPacien);

        Medico existMedic = buscarPorNombreApellido(t.getMedico().getNombre(), t.getMedico().getApellido());
        if (existMedic == null) {
            System.out.println("Médico no encontrado: " + t.getMedico().getNombre() + " " + t.getMedico().getApellido());
            return;
        }
        t.setMedico(existMedic);

        if (turnos.contains(t)) {
            System.out.println("Error: Conflicto de agenda. El médico ya tiene un turno asignado para esa fecha y hora exacta.");
            return;
        }

        int asigId = 0;
        for (Turno turno : turnos) {
            if (turno.getId() > asigId) {
                asigId = turno.getId();
            }
        }
        t.setId(asigId + 1);

        turnos.add(t);
        System.out.println("Turno asignado con éxito: " + t.toString());
    }

    public void cancelarTurno(int idTurno){
        Turno turnoEncontrado = null;
        for (Turno t : turnos) {
            if (t.getId() == idTurno) {
                turnoEncontrado = t;
                break;
            }
        }

        if (turnoEncontrado == null) {
            System.out.println("Turno no encontrado: " + idTurno);
            return; // CORRECCIÓN: Faltaba el return
        }

        if (turnoEncontrado.getEstado() == EstadoTurno.CANCELADO) {
            System.out.println("Aviso: Este turno ya se encuentra cancelado previamente.");
            return;
        }

        if (turnoEncontrado.getEstado() == EstadoTurno.ATENDIDO) {
            System.out.println("Error: No se puede cancelar un turno que ya fue atendido por el médico.");
            return;
        }

        turnoEncontrado.setEstado(EstadoTurno.CANCELADO);
        System.out.println("¡Turno cancelado con éxito!");
        System.out.println(turnoEncontrado.toString());
    }

    public void cambiarEstadoTurno(int idTurno, EstadoTurno nuevo){
        Turno turnoEncontrado = null;
        for (Turno t : turnos) {
            if (t.getId() == idTurno) {
                t.setEstado(nuevo);
                turnoEncontrado = t;
                break;
            }
        }

        if (turnoEncontrado == null) {
            System.out.println("Turno no encontrado: " + idTurno);
            return;
        }
        if (turnoEncontrado.getEstado() == nuevo) {
            turnoEncontrado.setEstado(nuevo);
        }
        System.out.println(turnoEncontrado.toString());
        System.out.println("El turno ha sido actualizado");
    }



    @Override
    public List<Turno> listarTurnosDelDia(LocalDateTime ld) {
        List<Turno> turnosDelDia = new ArrayList<>();
        for (Turno t : turnos) {
            if (t.getFechaHora().toLocalDate().equals(ld.toLocalDate())) {
                turnosDelDia.add(t);
            }
        }
        return turnosDelDia;
    }

    @Override
    public List<Turno> buscarPorMedico(Medico medico) {
        List<Turno> turnosMedico = new ArrayList<>();
        for (Turno t : turnos) {
            if (t.getMedico().equals(medico)) {
                turnosMedico.add(t);
            }
        }
        return turnosMedico;
    }

    @Override
    public List<Turno> buscarPorPaciente(Paciente paciente) {
        List<Turno> turnosPaciente = new ArrayList<>();
        for (Turno t : turnos) {
            if (t.getPaciente().equals(paciente)) {
                turnosPaciente.add(t);
            }
        }
        return turnosPaciente;
    }
}

