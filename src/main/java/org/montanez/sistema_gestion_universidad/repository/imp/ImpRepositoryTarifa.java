package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryTarifa;
import org.montanez.sistema_gestion_universidad.repository.models.Tarifa;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryTarifa implements RepositoryTarifa {
    private Connection connection;
    private ImpRepositoryPeriodo repoPeriodo;
    private ImpRepositoryPrograma repoPrograma;


    public ImpRepositoryTarifa() throws Exception {
        this.connection = new ConexionMsql().getConnection();
        this.repoPeriodo = new ImpRepositoryPeriodo();
        this.repoPrograma = new ImpRepositoryPrograma();
    }

    public static void main(String[] args) {
        try {
            ImpRepositoryTarifa impRepositoryTarifa = new ImpRepositoryTarifa();
            System.out.println(impRepositoryTarifa.listar());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Tarifa> listar() {
        List<Tarifa> tarifas = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Tarifa", resultSet -> tarifas.add(createTarifaFromResultSet(resultSet)));
        return tarifas;
    }

    private Tarifa createTarifaFromResultSet(ResultSet resultSet) {
        Tarifa tarifa = new Tarifa();
        try {
            tarifa.setId(resultSet.getLong("id"));
            tarifa.setCostoCredito(resultSet.getDouble("valorCredito"));
            long periodoId = resultSet.getLong("periodo_id");
            long programaId = resultSet.getLong("programa_id");
            tarifa.setPeriodo(repoPeriodo.buscarId(periodoId));
            tarifa.setPrograma(repoPrograma.programa_id(programaId));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tarifa;
    }


    @Override
    public Tarifa buscarId(long id) {
        Tarifa[] tarifa = new Tarifa[1];
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Tarifa WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, id),
                resultSet -> tarifa[0] = createTarifaFromResultSet(resultSet));
        return tarifa[0];
    }


    @Override
    public void crear(Tarifa tarifa) {
        String sql = "INSERT INTO Tarifa (valorCredito, programa_id, periodo_id) VALUES (?, ?, ?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setDouble(1, tarifa.getCostoCredito());
            preparedStatement.setLong(2, tarifa.getPrograma().getId());
            preparedStatement.setLong(3, tarifa.getPeriodo().getId());
        });
    }

    @Override
    public void editar(Tarifa tarifa) {
        String sql = "UPDATE Tarifa SET valorCredito = ?, programa_id = ?, periodo_id = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setDouble(1, tarifa.getCostoCredito());
            preparedStatement.setLong(2, tarifa.getPrograma().getId());
            preparedStatement.setLong(3, tarifa.getPeriodo().getId());
            preparedStatement.setLong(4, tarifa.getId());
        });
    }


    public void eliminar(Tarifa tarifa) {
        DbUtilsSql.executeUpdate(connection, "DELETE FROM Tarifa WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, tarifa.getId()));
    }
}
