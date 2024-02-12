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

    private HikariDataSource dataSource;

    public ConexionMsql() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(URL);
        config.setUsername(USERNAME);
        config.setPassword(PASSWORD);
        config.setMaximumPoolSize(2);
        this.dataSource = new HikariDataSource(config);
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}