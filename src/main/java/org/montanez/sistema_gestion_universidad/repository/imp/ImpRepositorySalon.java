package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositorySalon;
import org.montanez.sistema_gestion_universidad.repository.models.Edificio;
import org.montanez.sistema_gestion_universidad.repository.models.Salon;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositorySalon implements RepositorySalon {
    private Connection connection;

    public ImpRepositorySalon() throws Exception {
        this.connection = new ConexionMsql().getConnection();
    }

    public static void main(String[] args) {
        try {
            ImpRepositorySalon impRepositorySalon = new ImpRepositorySalon();
            List<Salon> salones = impRepositorySalon.listar();
            for (Salon salon : salones) {
                System.out.println(salon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Salon> listar() {
        List<Salon> salones = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Salon", resultSet -> salones.add(createSalonFromResultSet(resultSet)));
        return salones;
    }

    @Override
    public Salon salon_id(long id) {
        Salon[] salon = new Salon[1];
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Salon WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, id),
                resultSet -> salon[0] = createSalonFromResultSet(resultSet));
        return salon[0];
    }

    @Override
    public void crear(Salon salon) {
        String sql = "INSERT INTO Salon (capacidad, piso, numeroIdentificacion, edificio_id) VALUES (?, ?, ?, ?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> setPreparedStatementForSalon(preparedStatement, salon));
    }

    @Override
    public void editar(Salon salon) {
        String sql = "UPDATE Salon SET capacidad = ?, piso = ?, numeroIdentificacion = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            setPreparedStatementForSalon(preparedStatement, salon);
            preparedStatement.setLong(4, salon.getId());
        });

    }

    @Override
    public void eliminar(Salon salon) {
        String sql = "DELETE FROM Salon WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> preparedStatement.setLong(1, salon.getId()));

    }

    private void setPreparedStatementForSalon(PreparedStatement preparedStatement, Salon salon) {
        try {
            preparedStatement.setInt(1, salon.getCapacidad());
            preparedStatement.setInt(2, salon.getPiso());
            preparedStatement.setString(3, salon.getNumero());
            preparedStatement.setLong(4, salon.getEdificio().getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Salon createSalonFromResultSet(ResultSet resultSet) {
        Salon salon = new Salon();
        try {
            salon.setId(resultSet.getLong("id"));
            salon.setCapacidad(resultSet.getInt("capacidad"));
            salon.setPiso(resultSet.getInt("piso"));
            salon.setNumero(resultSet.getString("numeroIdentificacion"));

            long edificioId = resultSet.getLong("edificio_id");
            ImpRepositoryEdificio impRepositoryEdificio = new ImpRepositoryEdificio();
            Edificio edificio = impRepositoryEdificio.edificio_id(edificioId);
            salon.setEdificio(edificio);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return salon;
    }
}
