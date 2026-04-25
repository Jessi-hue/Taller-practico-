package com.uniajc.controlador;

import com.uniajc.modelo.Docente;
import com.uniajc.servicios.DocenteService;
import com.uniajc.vista.DocenteView;

public class ControladorDocente {

    private final DocenteView vistaDocente;
    private final DocenteService docenteService;

    public ControladorDocente(DocenteView vistaDocente, DocenteService docenteService) {
        this.vistaDocente = vistaDocente;
        this.docenteService = docenteService;
    }

    public void registrarDocente() {
        Docente docente = vistaDocente.solicitarDatosDocente();
        if (docente == null) {
            vistaDocente.mostrarMensaje("Registro cancelado.");
            return;
        }

        docenteService.registrarDocente(docente);
        vistaDocente.mostrarMensaje("Docente registrado exitosamente.");
    }

    public void mostrarTodosLosDocentes() {
        vistaDocente.mostrarTodosLosDocentes(docenteService.obtenerTodosLosDocentes());
    }
}
