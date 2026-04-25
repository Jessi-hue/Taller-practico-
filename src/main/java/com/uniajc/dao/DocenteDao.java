package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.uniajc.config.ConexionDatabase;
import com.uniajc.modelo.Docente;

public class DocenteDao {

    public void guardarDocente(Docente docente) {
        String sql = "INSERT INTO docentes (nombre, especialidad) VALUES (?, ?)";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2, docente.getEspecialidad());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    docente.setIdDocente(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public List<Docente> obtenerTodosLosDocentes() {
        List<Docente> docentes = new ArrayList<>();
        String sql = "SELECT id_docente, nombre, especialidad FROM docentes";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Docente docente = new Docente();
                docente.setIdDocente(rs.getInt("id_docente"));
                docente.setNombre(rs.getString("nombre"));
                docente.setEspecialidad(rs.getString("especialidad"));
                docentes.add(docente);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return docentes;
    }

    public Optional<Docente> obtenerDocentePorId(int id) {
        String sql = "SELECT id_docente, nombre, especialidad FROM docentes WHERE id_docente = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Docente docente = new Docente();
                    docente.setIdDocente(rs.getInt("id_docente"));
                    docente.setNombre(rs.getString("nombre"));
                    docente.setEspecialidad(rs.getString("especialidad"));
                    return Optional.of(docente);
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return Optional.empty();
    }

    public void actualizarDocente(Docente docente) {
        String sql = "UPDATE docentes SET nombre = ?, especialidad = ? WHERE id_docente = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, docente.getNombre());
            pstmt.setString(2, docente.getEspecialidad());
            pstmt.setInt(3, docente.getIdDocente());
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public void eliminarDocente(int idDocente) {
        String sql = "DELETE FROM docentes WHERE id_docente = ?";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idDocente);
            pstmt.executeUpdate();
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }
}
