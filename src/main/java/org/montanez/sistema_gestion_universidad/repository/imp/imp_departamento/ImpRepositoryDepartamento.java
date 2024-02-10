package org.montanez.sistema_gestion_universidad.repository.imp.imp_departamento;

import org.montanez.sistema_gestion_universidad.repository.RepositoryDepartemento;
import org.montanez.sistema_gestion_universidad.repository.models.Departamento;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryDepartamento implements RepositoryDepartemento {
    private Connection connection;

    public ImpRepositoryDepartamento() {
        try {
            this.connection = ConexionMsql.getInstance().getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Departamento createDepartamentoFromResultSet(ResultSet resultSet) throws Exception {
        Departamento departamento = new Departamento();
        departamento.setId(resultSet.getLong("id"));
        departamento.setNombre(resultSet.getString("nombre"));
        return departamento;
    }

    @Override
    public List<Departamento> listar() {
        List<Departamento> departamentos = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Departamento");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                departamentos.add(createDepartamentoFromResultSet(resultSet));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return departamentos;
    }

    @Override
    public Departamento buscarId(long id) {
        Departamento departamento = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM Departamento WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                departamento = createDepartamentoFromResultSet(resultSet);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return departamento;
    }

    @Override
    public void crear(Departamento departamento) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Departamento (nombre) VALUES (?)")) {
            preparedStatement.setString(1, departamento.getNombre());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editar(Departamento departamento) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Departamento SET nombre = ? WHERE id = ?")) {
            preparedStatement.setString(1, departamento.getNombre());
            preparedStatement.setLong(2, departamento.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Departamento departamento) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Departamento WHERE id = ?")) {
            preparedStatement.setLong(1, departamento.getId());
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}