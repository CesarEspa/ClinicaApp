package co.generation.clinica;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        linicaService servicio = new ClinicaService();
        DatosCSV.cargar(servicio);

        do {
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            System.out.println("■ CLINICAAPP — MENÚ ■");
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            System.out.println(" ■ 1. Registrar paciente ■");
            System.out.println(" ■ 2. Registrar médico ■");
            System.out.println("■ 3. Asignar turno ■");
            System.out.println("■ 4. Listar turnos del día ■");
            System.out.println("■ 5. Cancelar turno ■");
            System.out.println("■ 6. Ver turnos por médico ■");
            System.out.println("■ 7. Ver turnos por paciente ■");
            System.out.println("■ 8. Cambiar estado de turno ■");
            System.out.println("■ 9. Listar pacientes ■");
            System.out.println("■ 10. Listar médicos ■");
            System.out.println("■ 0. Salir ■");
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            int res = sc.nextInt();

        }while (res == 0);




    }
}
