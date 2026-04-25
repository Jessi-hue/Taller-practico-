package com.uniajc.vista;

import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import com.uniajc.modelo.Docente;

public class VistaDocenteSwing implements DocenteView {

    @Override
    public Docente solicitarDatosDocente() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del docente:", "Registro Docente", JOptionPane.QUESTION_MESSAGE);
        if (nombre == null) {
            return null;
        }

        String especialidad = JOptionPane.showInputDialog(null, "Ingrese la especialidad del docente:", "Registro Docente", JOptionPane.QUESTION_MESSAGE);
        if (especialidad == null) {
            return null;
        }

        return new Docente(0, nombre.trim(), especialidad.trim());
    }

    @Override
    public void mostrarDocente(Docente docente) {
        if (docente == null) {
            return;
        }
        String mensaje = String.format("Id: %d\nNombre: %s\nEspecialidad: %s", docente.getIdDocente(), docente.getNombre(), docente.getEspecialidad());
        JOptionPane.showMessageDialog(null, mensaje, "Docente", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarTodosLosDocentes(List<Docente> docentes) {
        if (docentes == null || docentes.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay docentes registrados.", "Docentes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String lista = docentes.stream()
                .map(docente -> String.format("Id: %d | Nombre: %s | Especialidad: %s", docente.getIdDocente(), docente.getNombre(), docente.getEspecialidad()))
                .collect(Collectors.joining("\n\n"));

        JOptionPane.showMessageDialog(null, lista, "Docentes", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(null, mensaje, "Información", JOptionPane.INFORMATION_MESSAGE);
    }
}
