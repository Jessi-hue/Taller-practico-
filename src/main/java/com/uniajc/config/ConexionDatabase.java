package com.uniajc.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexionDatabase {

    private static final Properties properties = new Properties();

    static {
        try (FileInputStream configuracion = new FileInputStream(new File("config.properties"))) {
            properties.load(configuracion);
        } catch (IOException e) {
            System.err.println("CRITICAL: Failed to load config.properties. " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        String driver = properties.getProperty("db.driver");
        String url = properties.getProperty("db.url");
        String user = properties.getProperty("db.user");
        String password = properties.getProperty("db.password");

        if (url == null || url.isBlank()) {
            throw new IllegalStateException("Base de datos configurada incorrectamente. Verifique que db.url esté presente en config.properties.");
        }

        if (driver != null && !driver.isBlank()) {
            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("No se encontró el driver JDBC: " + driver, e);
            }
        }

        if (user == null || user.isBlank()) {
            return DriverManager.getConnection(url);
        }

        return DriverManager.getConnection(url, user, password == null ? "" : password);
    }
}
