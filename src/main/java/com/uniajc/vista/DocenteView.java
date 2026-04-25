package com.uniajc.vista;

import java.util.List;

import com.uniajc.modelo.Docente;

public interface DocenteView {

    Docente solicitarDatosDocente();

    void mostrarDocente(Docente docente);

    void mostrarTodosLosDocentes(List<Docente> docentes);

    void mostrarMensaje(String mensaje);
}
