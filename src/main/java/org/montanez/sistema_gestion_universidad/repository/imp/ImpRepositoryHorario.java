package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryHorario;
import org.montanez.sistema_gestion_universidad.repository.models.Horario;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryHorario implements RepositoryHorario {
    private Connection connection;

    public ImpRepositoryHorario() throws Exception {
        this.connection = new ConexionMsql().getConnection();
    }

    private static Horario createHorarioFromResultSet(ResultSet resultSet) throws SQLException {
        Horario horario = new Horario();
        horario.setId(resultSet.getLong("id"));
        horario.setDia(DayOfWeek.of(resultSet.getInt("dia")));
        horario.setHoraInicio(resultSet.getTime("horaInicio").toLocalTime());
        horario.setHoraFin(resultSet.getTime("horaFin").toLocalTime());
        return horario;
    }

    @Override
    public List<Horario> listar() {
        List<Horario> horarios = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Horario", preparedStatement -> {
        }, resultSet -> horarios.add(createHorarioFromResultSet(resultSet)));
        return horarios;
    }

    @Override
    public Horario horario_id(long id) {
        Horario[] horario = new Horario[1];
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Horario WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, id), resultSet -> horario[0] = createHorarioFromResultSet(resultSet));
        return horario[0];
    }

    @Override
    public void crear(Horario horario) {
        String sql = "INSERT INTO Horario (dia, horaInicio, horaFin) VALUES (?, ?, ?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setInt(1, horario.getDia().getValue());
            preparedStatement.setTime(2, java.sql.Time.valueOf(horario.getHoraInicio()));
            preparedStatement.setTime(3, java.sql.Time.valueOf(horario.getHoraFin()));
        });

    }

    @Override
    public void editar(Horario horario) {
        String sql = "UPDATE Horario SET dia = ?, horaInicio = ?, horaFin = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setInt(1, horario.getDia().getValue());
            preparedStatement.setTime(2, java.sql.Time.valueOf(horario.getHoraInicio()));
            preparedStatement.setTime(3, java.sql.Time.valueOf(horario.getHoraFin()));
            preparedStatement.setLong(4, horario.getId());
        });

    }

    @Override
    public void eliminar(long id) {
        DbUtilsSql.executeUpdate(connection, "DELETE FROM Horario WHERE id = ?", preparedStatement -> preparedStatement.setLong(1, id));
    }
}
