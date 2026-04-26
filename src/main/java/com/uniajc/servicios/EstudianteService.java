package com.uniajc.servicios;

import java.util.List;

import com.uniajc.dao.EstudianteDao;
import com.uniajc.modelo.Estudiante;

public class EstudianteService {

    private final EstudianteDao estudianteDao;

    public EstudianteService(EstudianteDao estudianteDao) {
        this.estudianteDao = estudianteDao;
    }

    public void registrarEstudiante(Estudiante estudiante) {

        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }

        if (estudiante.getNombre() == null || estudiante.getNombre().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }

        if (estudiante.getApellido() == null || estudiante.getApellido().trim().isEmpty()) {
            throw new IllegalArgumentException("El apellido del estudiante es obligatorio.");
        }

        if (estudiante.getCorreo() == null || estudiante.getCorreo().trim().isEmpty()) {
            throw new IllegalArgumentException("El correo del estudiante es obligatorio.");
        }

        estudiante.setNombre(estudiante.getNombre().trim());
        estudiante.setApellido(estudiante.getApellido().trim());
        estudiante.setCorreo(estudiante.getCorreo().trim());

        estudianteDao.guardarEstudiante(estudiante);
    }

    public List<Estudiante> mostrarTodosLosEstudiantes() {
        return estudianteDao.obtenerTodosLosEstudiantes();
    }

}
