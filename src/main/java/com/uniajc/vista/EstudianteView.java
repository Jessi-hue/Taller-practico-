package com.uniajc.vista;

import java.util.List;

import com.uniajc.modelo.Estudiante;

public interface EstudianteView {

    Estudiante solicitarDatosEstudiante();

    void mostrarDetallesEstudiante(Estudiante estudiante);

    void mostrarTodosLosEstudiantes(List<Estudiante> estudiantes);

    void mostrarMensaje(String mensaje);
}
