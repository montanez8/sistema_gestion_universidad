package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryPrograma;
import org.montanez.sistema_gestion_universidad.repository.models.Programa;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryPrograma implements RepositoryPrograma {
    private Connection connection;

    public ImpRepositoryPrograma() throws Exception {
        this.connection = new ConexionMsql().getConnection();
    }


    @Override
    public List<Programa> listar() {
        List<Programa> programas = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Programa", preparedStatement -> {
        }, resultSet -> programas.add(createProgramaFromResultSet(resultSet)));
        return programas;
    }

    @Override
    public Programa programa_id(long id) {
        Programa[] programa = new Programa[1];
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Programa WHERE id = ?", preparedStatement -> preparedStatement.setLong(1, id), resultSet -> programa[0] = createProgramaFromResultSet(resultSet));
        return programa[0];
    }

    @Override
    public void crear(Programa programa) {
        String sql = "INSERT INTO Programa (nombre, nivel) VALUES (?, ?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setString(1, programa.getNombre());
            preparedStatement.setString(2, programa.getNivel());
        });

    }

    @Override
    public void editar(Programa programa) {
        String sql = "UPDATE Programa SET nombre = ?, nivel = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setString(1, programa.getNombre());
            preparedStatement.setString(2, programa.getNivel());
            preparedStatement.setLong(3, programa.getId());
        });

    }

    @Override
    public void eliminar(Programa programa) {
        String sql = "DELETE FROM Programa WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> preparedStatement.setLong(1, programa.getId()));
    }

    private Programa createProgramaFromResultSet(ResultSet resultSet) {
        Programa programa = new Programa();
        try {
            programa.setId(resultSet.getLong("id"));
            programa.setNombre(resultSet.getString("nombre"));
            programa.setNivel(resultSet.getString("nivel"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return programa;
    }
}
