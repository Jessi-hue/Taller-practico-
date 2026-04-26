package com.uniajc.controlador;

import com.uniajc.modelo.Estudiante;
import com.uniajc.servicios.EstudianteService;
import com.uniajc.vista.EstudianteView;

public class ControladorEstudiante {

    private final EstudianteView vistaEstudiante;
    private final EstudianteService estudianteService;

    public ControladorEstudiante(EstudianteView vistaEstudiante, EstudianteService estudianteService) {
        this.vistaEstudiante = vistaEstudiante;
        this.estudianteService = estudianteService;
    }

    public void registrarEstudiante() {
        Estudiante nuevoEstudiante = vistaEstudiante.solicitarDatosEstudiante();

        if (nuevoEstudiante == null) {
            vistaEstudiante.mostrarMensaje("Registro cancelado.");
            return;
        }

        estudianteService.registrarEstudiante(nuevoEstudiante);
        vistaEstudiante.mostrarMensaje("Estudiante registrado exitosamente.");
    }

    public void mostrarTodosLosEstudiantes() {
        vistaEstudiante.mostrarTodosLosEstudiantes(estudianteService.mostrarTodosLosEstudiantes());
    }
}
