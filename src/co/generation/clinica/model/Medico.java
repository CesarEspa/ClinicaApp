package co.generation.clinica.model;

import java.util.Objects;

public class Medico {
    private int id;
    private String nombre;
    private String apellido;
    private Especialidad especialidad;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El campo nombre no puede estar vacío");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.trim().isEmpty()) {
            System.out.println("El campo apellido no puede estar vacío");
        }
        this.apellido = apellido;
    }


    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        if (especialidad == null) {
            System.out.println("El campo especialidad no puede ser nulo.");
        }
        this.especialidad = especialidad;
    }

    @Override
    public boolean equals(Object comparador) {
        if (this == comparador) {
            return true;
        }
        if (comparador == null || getClass() != comparador.getClass()) {
            return false;
        }
        Medico medico = (Medico) comparador;
        return nombre.equalsIgnoreCase(medico.nombre) && apellido.equalsIgnoreCase(medico.apellido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre.toLowerCase(), apellido.toLowerCase());
    }

    @Override
    public String toString() {
        return "Dr." + nombre + apellido + " - "+ especialidad;
    }

}
