package com.uniajc.servicios;

import java.util.List;

import com.uniajc.dao.MateriaDao;
import com.uniajc.modelo.Materia;

public class MateriaService {

    private final MateriaDao materiaDao;

    public MateriaService(MateriaDao materiaDao) {
        this.materiaDao = materiaDao;
    }

    public void registrarMateria(Materia materia) {
        if (materia == null) {
            throw new IllegalArgumentException("La materia no puede ser nula.");
        }

        if (materia.getNombreMateria() == null || materia.getNombreMateria().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la materia es obligatorio.");
        }

        if (materia.getCreditos() == null || materia.getCreditos() <= 0) {
            throw new IllegalArgumentException("Los créditos deben ser un número positivo.");
        }

        materia.setNombreMateria(materia.getNombreMateria().trim());
        materiaDao.guardarMateria(materia);
    }

    public List<Materia> obtenerTodasLasMaterias() {
        return materiaDao.obtenerTodasLasMaterias();
    }
}
