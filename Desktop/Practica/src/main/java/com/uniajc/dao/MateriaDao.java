package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.uniajc.config.ConexionDatabase;
import com.uniajc.modelo.Materia;

public class MateriaDao {

    public void guardarMateria(Materia materia) {
        String sql = "INSERT INTO materias (nombre_materia, creditos) VALUES (?, ?)";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, materia.getNombreMateria());
            pstmt.setInt(2, materia.getCreditos());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    materia.setIdMateria(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public List<Materia> obtenerTodasLasMaterias() {
        List<Materia> materias = new ArrayList<>();
        String sql = "SELECT id_materia, nombre_materia, creditos FROM materias";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombreMateria(rs.getString("nombre_materia"));
                materia.setCreditos(rs.getInt("creditos"));
                materias.add(materia);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return materias;
    }
}
