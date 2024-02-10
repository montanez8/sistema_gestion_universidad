package org.montanez.sistema_gestion_universidad.repository.imp.imp_tarifa;

import org.montanez.sistema_gestion_universidad.repository.RepositoryTarifa;
import org.montanez.sistema_gestion_universidad.repository.imp.imp_periodo.ImpRepositoryPeriodo;
import org.montanez.sistema_gestion_universidad.repository.imp.imp_programa.ImpRepositoryPrograma;
import org.montanez.sistema_gestion_universidad.repository.models.Periodo;
import org.montanez.sistema_gestion_universidad.repository.models.Programa;
import org.montanez.sistema_gestion_universidad.repository.models.Tarifa;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryTarifa implements RepositoryTarifa {
    private Connection connection;

    public ImpRepositoryTarifa() throws Exception {
        this.connection = ConexionMsql.getInstance().getConnection();
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

            long programaId = resultSet.getLong("programa_id");
            Programa programa = new ImpRepositoryPrograma().programa_id(programaId);
            tarifa.setPrograma(programa);

            long periodoId = resultSet.getLong("periodo_id");
            Periodo periodo = new ImpRepositoryPeriodo().buscarId(periodoId);
            tarifa.setPeriodo(periodo);

            return tarifa;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
