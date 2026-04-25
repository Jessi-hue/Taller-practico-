package com.uniajc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.uniajc.config.ConexionDatabase;
import com.uniajc.modelo.Grupo;

public class GrupoDao {

    public void guardarGrupo(Grupo grupo) {
        String sql = "INSERT INTO grupos (id_materia, id_docente, aula, horario) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setInt(1, grupo.getIdMateria());
            pstmt.setInt(2, grupo.getIdDocente());
            pstmt.setString(3, grupo.getAula());
            pstmt.setString(4, grupo.getHorario());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    grupo.setIdGrupo(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
    }

    public List<Grupo> obtenerTodosLosGrupos() {
        List<Grupo> grupos = new ArrayList<>();
        String sql = "SELECT id_grupo, id_materia, id_docente, aula, horario FROM grupos";

        try (Connection conn = ConexionDatabase.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Grupo grupo = new Grupo();
                grupo.setIdGrupo(rs.getInt("id_grupo"));
                grupo.setIdMateria(rs.getInt("id_materia"));
                grupo.setIdDocente(rs.getInt("id_docente"));
                grupo.setAula(rs.getString("aula"));
                grupo.setHorario(rs.getString("horario"));
                grupos.add(grupo);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return grupos;
    }
}
