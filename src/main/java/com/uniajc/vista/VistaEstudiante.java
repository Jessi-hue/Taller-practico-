package com.uniajc.vista;

import java.util.List;
import java.util.Scanner;

import com.uniajc.modelo.Estudiante;

public class VistaEstudiante implements EstudianteView {

    private Scanner scanner;

    public VistaEstudiante() {
        this.scanner = new Scanner(System.in);
    }

    public Estudiante solicitarDatosEstudiante() {

        System.out.println("Registrando los datos del estudiante...");

        System.out.print("Ingrese el nombre del estudiante: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese el apellido del estudiante: ");
        String apellido = scanner.nextLine().trim();

        System.out.print("Ingrese el correo del estudiante: ");
        String correo = scanner.nextLine().trim();

        return new Estudiante(0, nombre, apellido, correo);
    }

    public void mostrarDetallesEstudiante(Estudiante estudiante) {
        System.out.println("Id: " + estudiante.getId());
        System.out.println("Nombre: " + estudiante.getNombre());
        System.out.println("Apellido: " + estudiante.getApellido());
        System.out.println("Correo: " + estudiante.getCorreo());
        System.out.println();
    }

    public void mostrarTodosLosEstudiantes(List<Estudiante> estudiantes) {
        System.out.println("Lista de Estudiantes: ");
        if (estudiantes == null || estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (Estudiante estudiante : estudiantes) {
            mostrarDetallesEstudiante(estudiante);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
