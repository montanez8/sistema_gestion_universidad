package org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.montanez.sistema_gestion_universidad.utils.Configuracion;

import java.sql.Connection;
import java.sql.SQLException;

public class ConexionMsql {

    private static String URL = Configuracion.obtenerPropiedad("db.url");
    private static String USERNAME = Configuracion.obtenerPropiedad("db.username");
    private static String PASSWORD = Configuracion.obtenerPropiedad("db.password");

    private static ConexionMsql instance;
    private HikariDataSource dataSource;

    private ConexionMsql() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        this.dataSource = new HikariDataSource(config);
    }

    public static ConexionMsql getInstance() {
        if (instance == null) {
            instance = new ConexionMsql();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}