package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryPeriodo;
import org.montanez.sistema_gestion_universidad.repository.models.Periodo;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryPeriodo implements RepositoryPeriodo {
    private Connection connection;

    public ImpRepositoryPeriodo() throws Exception {
        this.connection = ConexionMsql.getInstance().getConnection();
    }

    @Override
    public List<Periodo> listar() {
        List<Periodo> periodos = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Periodo", resultSet -> periodos.add(createPeriodoFromResultSet(resultSet)));
        return periodos;
    }

    @Override
    public Periodo buscarId(long id) {
        Periodo[] periodo = new Periodo[1];
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Periodo WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, id),
                resultSet -> periodo[0] = createPeriodoFromResultSet(resultSet));
        return periodo[0];
    }

    @Override
    public void crear(Periodo periodo) {
        String sql = "INSERT INTO Periodo (codigo, anio, semestre) VALUES (?, ?, ?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> setPreparedStatementForPeriodo(preparedStatement, periodo));

    }

    @Override
    public void editar(Periodo periodo) {
        String sql = "UPDATE Periodo SET codigo = ?, anio = ?, semestre = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            setPreparedStatementForPeriodo(preparedStatement, periodo);
            preparedStatement.setLong(4, periodo.getId());
        });

    }

    public void eliminar(long id) {
        String sql = "DELETE FROM Periodo WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> preparedStatement.setLong(1, id));
    }

    private void setPreparedStatementForPeriodo(PreparedStatement preparedStatement, Periodo periodo) {
        try {
            preparedStatement.setString(1, periodo.getCodigo());
            preparedStatement.setInt(2, periodo.getAnio());
            preparedStatement.setInt(3, periodo.getSemestre());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Periodo createPeriodoFromResultSet(ResultSet resultSet) {
        Periodo periodo = new Periodo();
        try {
            periodo.setId(resultSet.getLong("id"));
            periodo.setCodigo(resultSet.getString("codigo"));
            periodo.setAnio(resultSet.getInt("anio"));
            periodo.setSemestre(resultSet.getInt("semestre"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return periodo;

    }
}
