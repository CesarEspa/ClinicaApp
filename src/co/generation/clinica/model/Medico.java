package co.generation.clinica.model;

import co.generation.clinica.interfaces.Registrable;
import java.util.Objects;

public class Medico implements Registrable {
    private int id;
    private String nombre;
    private String apellido;
    private Especialidad especialidad;

    // Constructor con ID (Para cargar desde CSV)
    public Medico(int id, String nombre, String apellido, Especialidad especialidad) {
        this.id = id;
        setNombre(nombre);
        setApellido(apellido);
        setEspecialidad(especialidad);
    }

    // Constructor sin ID (Para nuevos registros manuales)
    public Medico(String nombre, String apellido, Especialidad especialidad) {
        setNombre(nombre);
        setApellido(apellido);
        setEspecialidad(especialidad);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo nombre no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo apellido no puede estar vacío");
        }
        this.apellido = apellido.trim();
    }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) {
        if (especialidad == null) {
            throw new IllegalArgumentException("El campo especialidad no puede ser nulo.");
        }
        this.especialidad = especialidad;
    }

    @Override
    public boolean equals(Object comparador) {
        if (this == comparador) return true;
        if (comparador == null || getClass() != comparador.getClass()) return false;
        Medico medico = (Medico) comparador;
        return nombre.equalsIgnoreCase(medico.nombre) && apellido.equalsIgnoreCase(medico.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }

    @Override
    public String toString() {
        return "Dr. " + nombre + " " + apellido + " - " + especialidad;
    }

    // --- Implementación de Registrable ---
    @Override
    public String getDatosRegistro() {
        return "Médico registrado: " + this.toString();
    }

    @Override
    public boolean esValido() {
        return nombre != null && !nombre.isEmpty() &&
                apellido != null && !apellido.isEmpty() &&
                especialidad != null;
    }
}