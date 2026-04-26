package com.uniajc.vista;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.uniajc.modelo.Estudiante;

public class VistaEstudianteSwing implements EstudianteView {

    @Override
    public Estudiante solicitarDatosEstudiante() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del estudiante:", "Registro Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (nombre == null) {
            return null;
        }

        String apellido = JOptionPane.showInputDialog(null, "Ingrese el apellido del estudiante:", "Registro Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (apellido == null) {
            return null;
        }

        String correo = JOptionPane.showInputDialog(null, "Ingrese el correo del estudiante:", "Registro Estudiante", JOptionPane.QUESTION_MESSAGE);
        if (correo == null) {
            return null;
        }

        return new Estudiante(0, nombre.trim(), apellido.trim(), correo.trim());
    }

    @Override
    public void mostrarDetallesEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            return;
        }

        String mensaje = String.format(
                "Id: %d\nNombre: %s\nApellido: %s\nCorreo: %s",
                estudiante.getId(),
                estudiante.getNombre(),
                estudiante.getApellido(),
                estudiante.getCorreo());
        JOptionPane.showMessageDialog(null, mensaje, "Estudiante", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarTodosLosEstudiantes(List<Estudiante> estudiantes) {
        if (estudiantes == null || estudiantes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay estudiantes registrados.", "Estudiantes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String lista = estudiantes.stream()
                .map(estudiante -> String.format(
                        "Id: %d | Nombre: %s %s | Correo: %s",
                        estudiante.getId(),
                        estudiante.getNombre(),
                        estudiante.getApellido(),
                        estudiante.getCorreo()))
                .collect(Collectors.joining("\n\n"));

        JOptionPane.showMessageDialog(null, lista, "Estudiantes", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
