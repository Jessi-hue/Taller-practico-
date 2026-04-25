package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.uniajc.config.ConexionDatabase;
import com.uniajc.modelo.Inscripcion_Curso;

public class InscripcionCursoDao {

    public void guardarInscripcion(Inscripcion_Curso inscripcion) {
        String sql = "INSERT INTO inscripciones_curso (id_estudiante, id_grupo, nota_final, estado) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, inscripcion.getIdEstudiante());
            pstmt.setInt(2, inscripcion.getIdGrupo());
            pstmt.setFloat(3, inscripcion.getNotaFinal());
            pstmt.setString(4, inscripcion.getEstado());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    inscripcion.setIdInscripcion(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public List<Inscripcion_Curso> obtenerTodasLasInscripciones() {
        List<Inscripcion_Curso> inscripciones = new ArrayList<>();
        String sql = "SELECT id_inscripcion, id_estudiante, id_grupo, nota_final, estado FROM inscripciones_curso";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Inscripcion_Curso inscripcion = new Inscripcion_Curso();
                inscripcion.setIdInscripcion(rs.getInt("id_inscripcion"));
                inscripcion.setIdEstudiante(rs.getInt("id_estudiante"));
                inscripcion.setIdGrupo(rs.getInt("id_grupo"));
                inscripcion.setNotaFinal(rs.getFloat("nota_final"));
                inscripcion.setEstado(rs.getString("estado"));
                inscripciones.add(inscripcion);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return inscripciones;
    }
}
