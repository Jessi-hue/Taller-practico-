package com.uniajc.vista;

import java.util.List;
import java.util.Scanner;

import com.uniajc.modelo.Docente;

public class VistaDocente implements DocenteView {

    private final Scanner scanner;

    public VistaDocente() {
        this.scanner = new Scanner(System.in);
    }

    @Override
    public Docente solicitarDatosDocente() {
        System.out.println("Registrando los datos del docente...");

        System.out.print("Ingrese el nombre del docente: ");
        String nombre = scanner.nextLine().trim();

        System.out.print("Ingrese la especialidad del docente: ");
        String especialidad = scanner.nextLine().trim();

        return new Docente(0, nombre, especialidad);
    }

    @Override
    public void mostrarDocente(Docente docente) {
        System.out.println("Docente registrado:");
        System.out.println("Id: " + docente.getIdDocente());
        System.out.println("Nombre: " + docente.getNombre());
        System.out.println("Especialidad: " + docente.getEspecialidad());
        System.out.println();
    }

    @Override
    public void mostrarTodosLosDocentes(List<Docente> docentes) {
        System.out.println("Lista de docentes:");
        if (docentes == null || docentes.isEmpty()) {
            System.out.println("No hay docentes registrados.");
            return;
        }
        for (Docente docente : docentes) {
            mostrarDocente(docente);
        }
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
