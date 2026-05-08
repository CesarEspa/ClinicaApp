package co.generation.clinica;

import co.generation.clinica.datos.DatosCSV;
import co.generation.clinica.model.*;
import co.generation.clinica.service.ClinicaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ClinicaService servicio = new ClinicaService();
        System.out.println("Cargando datos...");
        DatosCSV.cargar(servicio);

        boolean continuar = true;

        while (continuar) {
            try {
                System.out.println(mostrarMenu());
                System.out.print("Seleccione una opción: ");
                int opcion = Integer.parseInt(sc.nextLine());

                switch (opcion) {
                    case 1 -> registrarPaciente(servicio, sc);
                    case 2 -> registrarMedico(servicio, sc);
                    case 3 -> asignarTurno(servicio, sc);
                    case 4 -> listarTurnosDelDia(servicio, sc);
                    case 5 -> cancelarTurno(servicio, sc);
                    case 6 -> verTurnosPorMedico(servicio, sc);
                    case 7 -> verTurnosPorPaciente(servicio, sc);
                    case 8 -> cambiarEstadoTurno(servicio, sc);
                    case 9 -> servicio.listarPacientes();
                    case 10 -> servicio.listarMedicos();
                    case 0 -> {
                        System.out.println("Guardando datos y saliendo...");
                        DatosCSV.guardar(servicio);
                        continuar = false;
                    }
                    default -> System.out.println("Opción no válida.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Por favor, ingrese solo números.");
            } catch (Exception e) {
                System.out.println("Ocurrió un error: " + e.getMessage());
            }
        }
    }

    public static String mostrarMenu() {
        return """
                ╔═══════════════════════════════════════╗
                ║          CLINICA APP - MENÚ           ║
                ╚═══════════════════════════════════════╝
                1. Registrar paciente
                2. Registrar médico
                3. Asignar turno
                4. Listar turnos del día
                5. Cancelar turno
                6. Ver turnos por médico
                7. Ver turnos por paciente
                8. Cambiar estado de turno
                9. Listar pacientes
                10. Listar médicos
                0. Salir y Guardar
                """;
    }

    public static void registrarPaciente(ClinicaService service, Scanner sc) {
        System.out.print("Cédula: ");
        String cedula = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.print("Teléfono (7-10 dígitos): ");
        String tel = sc.nextLine();

        try {
            service.registrarPaciente(new Paciente(cedula, nombre, apellido, tel));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void registrarMedico(ClinicaService service, Scanner sc) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Apellido: ");
        String apellido = sc.nextLine();
        System.out.println("Especialidad: 1.General, 2.Pediatría, 3.Cardiología, 4.Urgencias");
        int espOpt = Integer.parseInt(sc.nextLine());
        Especialidad esp = switch (espOpt) {
            case 2 -> Especialidad.PEDIATRIA;
            case 3 -> Especialidad.CARDIOLOGIA;
            case 4 -> Especialidad.URGENCIAS;
            default -> Especialidad.GENERAL;
        };

        service.registrarMedico(new Medico(nombre, apellido, esp));
    }

    public static void asignarTurno(ClinicaService service, Scanner sc) {
        System.out.print("Cédula del paciente: ");
        String cedula = sc.nextLine();
        Paciente p = service.buscarPorCedula(cedula);

        System.out.print("Nombre del médico: ");
        String nomM = sc.nextLine();
        System.out.print("Apellido del médico: ");
        String apeM = sc.nextLine();
        Medico m = service.buscarPorNombreApellido(nomM, apeM);

        if (p == null || m == null) {
            System.out.println("Error: Paciente o Médico no encontrados.");
            return;
        }

        System.out.print("Fecha (AAAA-MM-DD): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine());
        System.out.print("Hora (HH:mm): ");
        LocalTime hora = LocalTime.parse(sc.nextLine());

        service.asignarTurno(new Turno(p, m, LocalDateTime.of(fecha, hora)));
    }

    public static void listarTurnosDelDia(ClinicaService service, Scanner sc) {
        System.out.print("Ingrese fecha a consultar (AAAA-MM-DD): ");
        LocalDate fecha = LocalDate.parse(sc.nextLine());
        List<Turno> turnos = service.listarTurnosDelDia(fecha.atStartOfDay());
        if (turnos.isEmpty()) System.out.println("No hay turnos para esa fecha.");
        else turnos.forEach(System.out::println);
    }

    public static void cancelarTurno(ClinicaService service, Scanner sc) {
        System.out.print("ID del turno a cancelar: ");
        int id = Integer.parseInt(sc.nextLine());
        service.cancelarTurno(id);
    }

    public static void verTurnosPorMedico(ClinicaService service, Scanner sc) {
        System.out.print("Nombre del médico: ");
        String n = sc.nextLine();
        System.out.print("Apellido del médico: ");
        String a = sc.nextLine();
        Medico m = service.buscarPorNombreApellido(n, a);
        if (m != null) service.buscarPorMedico(m).forEach(System.out::println);
        else System.out.println("Médico no encontrado.");
    }

    public static void verTurnosPorPaciente(ClinicaService service, Scanner sc) {
        System.out.print("Cédula del paciente: ");
        String c = sc.nextLine();
        Paciente p = service.buscarPorCedula(c);
        if (p != null) service.buscarPorPaciente(p).forEach(System.out::println);
        else System.out.println("Paciente no encontrado.");
    }

    public static void cambiarEstadoTurno(ClinicaService service, Scanner sc) {
        System.out.print("ID del turno: ");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Nuevo estado: 1.ATENDIDO, 2.PENDIENTE, 3.CANCELADO");
        int opt = Integer.parseInt(sc.nextLine());
        EstadoTurno est = null;
        switch (opt) {
            case 1: est = EstadoTurno.ATENDIDO;
                break;
            case 2:est = EstadoTurno.PENDIENTE;
                break;
            case 3: est = EstadoTurno.CANCELADO;
                break;
            default:
                System.out.println("Opción no válida.");
                return;
        };
        service.cambiarEstadoTurno(id, est);
    }
}
