package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryEdificio;
import org.montanez.sistema_gestion_universidad.repository.models.Edificio;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryEdificio implements RepositoryEdificio {

    private Connection connection;

    public ImpRepositoryEdificio() throws Exception {
        this.connection = new ConexionMsql().getConnection();
    }


    @Override
    public List<Edificio> listar() {
        List<Edificio> edificios = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Edificio", resultSet -> edificios.add(createEdificioFromResultSet(resultSet)));
        return edificios;
    }

    private Edificio createEdificioFromResultSet(ResultSet resultSet) {
        Edificio edificio = new Edificio();
        try {
            edificio.setId(resultSet.getLong("id"));
            edificio.setNombre(resultSet.getString("nombre"));
        } catch (Exception e) {
            e.printStackTrace();

        }
        return edificio;
    }

    @Override
    public Edificio edificio_id(long id) {
        Edificio[] edificio = new Edificio[1];
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Edificio WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, id),
                resultSet -> edificio[0] = createEdificioFromResultSet(resultSet));
        return edificio[0];
    }

    @Override
    public void crear(Edificio edificio) {
        String sql = "INSERT INTO Edificio (nombre) VALUES (?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setString(1, edificio.getNombre());
        });

    }

    @Override
    public void editar(Edificio edificio) {
        String sql = "UPDATE Edificio SET nombre = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setString(1, edificio.getNombre());
            preparedStatement.setLong(2, edificio.getId());
        });

    }

    @Override
    public void eliminar(Edificio edificio) {
        String sql = "DELETE FROM Edificio WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> preparedStatement.setLong(1, edificio.getId()));
    }
}
