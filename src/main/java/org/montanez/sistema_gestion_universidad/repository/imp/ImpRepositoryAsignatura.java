package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryAsignatura;
import org.montanez.sistema_gestion_universidad.repository.models.Asignatura;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryAsignatura implements RepositoryAsignatura {
    private Connection connection;

    public ImpRepositoryAsignatura() throws Exception {
        this.connection = ConexionMsql.getInstance().getConnection();
    }

    @Override
    public List<Asignatura> listar() {
        List<Asignatura> asignaturas = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Asignatura", resultSet -> asignaturas.add(createAsignaturaFromResultSet(resultSet)));
        return asignaturas;
    }

    @Override
    public Asignatura asignatura_id(long id) {
        Asignatura[] asignatura = new Asignatura[1];
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Asignatura WHERE id = ? ", preparedStatement -> preparedStatement.setLong(1, id),
                resultSet -> asignatura[0] = createAsignaturaFromResultSet(resultSet));
        return asignatura[0];
    }

    @Override
    public void crear(Asignatura asignatura) {
        String sql = "INSERT Into  Asignatura(nombre, codigo, creditos ,cupoDisponible) values('?', '?', ?, ?);";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setString(1, asignatura.getNombre());
            preparedStatement.setString(2, asignatura.getCodigo());
            preparedStatement.setInt(3, asignatura.getCreditos());
            preparedStatement.setInt(4, asignatura.getCupo());
        });

    }

    @Override
    public void editar(Asignatura asignatura) {
        String sql = "INSERT Into  Asignatura(nombre, codigo, creditos ,cupoDisponible) values('?', '?', ?, ?);";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setString(1, asignatura.getNombre());
            preparedStatement.setString(2, asignatura.getCodigo());
            preparedStatement.setInt(3, asignatura.getCreditos());
            preparedStatement.setInt(4, asignatura.getCupo());
        });

    }

    @Override
    public void eliminar(long id) {
        DbUtilsSql.executeUpdate(connection, "DELETE FROM Asignatura WHERE id = ?", preparedStatement -> preparedStatement.setLong(1, id));

    }

    private Asignatura createAsignaturaFromResultSet(ResultSet resultSet) throws Exception {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(resultSet.getLong("id"));
        asignatura.setNombre(resultSet.getString("nombre"));
        asignatura.setCreditos(resultSet.getInt("creditos"));
        asignatura.setCupo(resultSet.getInt("cupoDisponible"));
        return asignatura;
    }
}
