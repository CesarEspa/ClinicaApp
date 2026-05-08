package co.generation.clinica.service;

import co.generation.clinica.interfaces.Consultable;
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


    public void registrarPaciente(Paciente p) {
        if (!p.esValido()) {
            System.out.println("Paciente no registrado");
            return;
        }
        if (pacientes.contains(p)) {
            System.out.println("Paciente existente");
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

    public void registrarMedico(Medico m){
        if (!m.esValido()) {

        }
    }







    public List<Paciente> getPacientes () {
            return pacientes;
        }

        public List<Medico> getMedicos () {
            return medicos;
        }

        public List<Turno> getTurnos () {
            return turnos;
        }

        @Override
        public List<Turno> listarTurnosDelDia (LocalDateTime ld){
            return List.of();
        }

        @Override
        public List<Turno> buscarPorMedico (Medico medico){
            return List.of();
        }

        @Override
        public List<Turno> buscarPorPaciente (Paciente paciente){
            return List.of();
        }
    }
