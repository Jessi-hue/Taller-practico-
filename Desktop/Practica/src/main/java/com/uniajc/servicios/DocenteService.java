package com.uniajc.servicios;

import java.util.List;

import com.uniajc.dao.DocenteDao;
import com.uniajc.modelo.Docente;

public class DocenteService {

    private final DocenteDao docenteDao;

    public DocenteService(DocenteDao docenteDao) {
        this.docenteDao = docenteDao;
    }

    public void registrarDocente(Docente docente) {
        if (docente == null) {
            throw new IllegalArgumentException("El docente no puede ser nulo.");
        }

        if (docente.getNombre() == null || docente.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del docente es obligatorio.");
        }

        if (docente.getEspecialidad() == null || docente.getEspecialidad().trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad del docente es obligatoria.");
        }

        docente.setNombre(docente.getNombre().trim());
        docente.setEspecialidad(docente.getEspecialidad().trim());

        docenteDao.guardarDocente(docente);
    }

    public List<Docente> obtenerTodosLosDocentes() {
        return docenteDao.obtenerTodosLosDocentes();
    }
}
