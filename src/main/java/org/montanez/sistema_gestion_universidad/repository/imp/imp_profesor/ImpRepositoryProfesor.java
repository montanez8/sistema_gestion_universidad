package org.montanez.sistema_gestion_universidad.repository.imp.imp_profesor;

import org.montanez.sistema_gestion_universidad.repository.RepositoryProfesor;
import org.montanez.sistema_gestion_universidad.repository.models.Profesor;
import org.montanez.sistema_gestion_universidad.repository.models.TipoDocumento;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ImpRepositoryProfesor implements RepositoryProfesor {

    private Connection connection;

    public ImpRepositoryProfesor() throws SQLException {
        this.connection = ConexionMsql.getInstance().getConnection();
    }


    @Override
    public List<Profesor> listar() {
        List<Profesor> profesores = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Profesor", resultSet -> profesores.add(createProfesorFromResultSet(resultSet)));
        return profesores;
    }


    @Override
    public Profesor profesor_id(long id) {
        Profesor[] profesor = new Profesor[1];
        String sql = "SELECT * FROM Profesor WHERE id = ?";
        DbUtilsSql.executeQuery(connection, sql, preparedStatement -> preparedStatement.setLong(1, id), resultSet -> {
                    try {
                        profesor[0] = createProfesorFromResultSet(resultSet);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        );
        return profesor[0];
    }

    @Override
    public void crear(Profesor profesor) {
        String sql = "INSERT INTO Profesor (tipoDocumento, numeroDocumento, nombres, " +
                "apellidos, ciudadResidencia, direccion, numeroTelefono, fechaNacimiento, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> setPreparedStatementForProfesor(preparedStatement, profesor));
    }

    @Override
    public void editar(Profesor profesor) {
        String sql = "UPDATE Profesor SET tipoDocumento = ?, numeroDocumento = ?, nombres = ?, apellidos = ?, ciudadResidencia =" +
                " ?, direccion = ?, numeroTelefono = ?, fechaNacimiento = ?, genero = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            setPreparedStatementForProfesor(preparedStatement, profesor);
            preparedStatement.setLong(10, profesor.getId());
        });
    }

    @Override
    public void eliminar(long id) {
        DbUtilsSql.executeUpdate(connection, "DELETE FROM Profesor WHERE id = ?", preparedStatement -> preparedStatement.setLong(1, id));
    }


    private void setPreparedStatementForProfesor(PreparedStatement preparedStatement, Profesor profesor) {
        try {
            preparedStatement.setString(1, profesor.getTipoDocumento().name());
            preparedStatement.setString(2, profesor.getNumeroDocumento());
            preparedStatement.setString(3, profesor.getNombre());
            preparedStatement.setString(4, profesor.getApellido());
            preparedStatement.setString(5, profesor.getCiudad());
            preparedStatement.setString(6, profesor.getDireccion());
            preparedStatement.setString(7, profesor.getTelefono());
            preparedStatement.setDate(8, new java.sql.Date(profesor.getFechaNacimiento().getTime()));
            preparedStatement.setString(9, profesor.getGenero());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Profesor createProfesorFromResultSet(ResultSet resultSet) throws Exception {
        Profesor profesor = new Profesor();
        profesor.setId(resultSet.getLong("id"));
        profesor.setTipoDocumento(TipoDocumento.setEnumTipo(resultSet.getString("tipoDocumento")));
        profesor.setNumeroDocumento(resultSet.getString("numeroDocumento"));
        profesor.setNombre(resultSet.getString("nombres"));
        profesor.setApellido(resultSet.getString("apellidos"));
        profesor.setCiudad(resultSet.getString("ciudadResidencia"));
        profesor.setDireccion(resultSet.getString("direccion"));
        profesor.setTelefono(resultSet.getString("numeroTelefono"));
        profesor.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
        profesor.setGenero(resultSet.getString("genero"));
        return profesor;
    }

}



