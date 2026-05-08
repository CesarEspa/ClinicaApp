package co.generation.clinica.model;

import co.generation.clinica.interfaces.Registrable;
import java.util.Objects;

public class Paciente implements Registrable {

    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;

    // Constructor con ID (Para cargar desde CSV)
    public Paciente(int id, String cedula, String nombre, String apellido, String telefono) {
        this.id = id;
        setCedula(cedula);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    // Constructor sin ID (Para nuevos registros manuales)
    public Paciente(String cedula, String nombre, String apellido, String telefono) {
        setCedula(cedula);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getCedula() { return cedula; }
    public void setCedula(String cedula) {
        if (cedula == null || cedula.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo de cedula no puede estar vacio");
        }
        this.cedula = cedula.trim();
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo nombre no puede estar vacio");
        }
        this.nombre = nombre.trim();
    }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo apellido no puede estar vacio");
        }
        this.apellido = apellido.trim();
    }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El campo telefono no puede estar vacio");
        }
        if (!telefono.trim().matches("^[0-9]{7,10}$")) {
            throw new IllegalArgumentException("El telefono debe contener entre 7 y 10 dígitos numericos");
        }
        this.telefono = telefono.trim();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(cedula, paciente.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cedula);
    }

    @Override
    public String toString() {
        return getNombre() + " " + getApellido() + " - CC: " + getCedula() + " - Tel: " + getTelefono();
    }

    // --- Implementación de Registrable ---
    @Override
    public String getDatosRegistro() {
        return "Paciente registrado: " + this.toString();
    }

    @Override
    public boolean esValido() {
        return cedula != null && !cedula.isEmpty() &&
                nombre != null && !nombre.isEmpty() &&
                apellido != null && !apellido.isEmpty() &&
                telefono != null && telefono.matches("^[0-9]{7,10}$");
    }
}