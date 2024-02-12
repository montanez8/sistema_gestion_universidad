package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryAlumno;
import org.montanez.sistema_gestion_universidad.repository.models.Alumno;
import org.montanez.sistema_gestion_universidad.repository.models.TipoDocumento;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryAlumno implements RepositoryAlumno {
    private Connection connection;

    public ImpRepositoryAlumno() throws SQLException {
        this.connection = new ConexionMsql().getConnection();
    }

    @Override
    public List<Alumno> listar() {
        List<Alumno> alumnos = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Alumno", resultSet -> alumnos.add(createAlumnoFromResultSet(resultSet)));
        return alumnos;
    }

    @Override
    public Alumno alumno_id(long id) {
        Alumno[] alumno = new Alumno[1];
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Alumno WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, id),
                resultSet -> alumno[0] = createAlumnoFromResultSet(resultSet));
        return alumno[0];
    }

    @Override
    public void crear(Alumno alumno) {
        String sql = "INSERT INTO Alumno (tipoDocumento, numeroDocumento, nombres, apellidos, ciudadResidencia," +
                " direccion, numeroTelefono, fechaNacimiento, genero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> setPreparedStatementForAlumno(preparedStatement, alumno));
    }

    @Override
    public void editar(Alumno alumno) {
        String sql = "UPDATE Alumno SET tipoDocumento = ?, numeroDocumento = ?, nombres = ?, apellidos = ?, ciudadResidencia = ?, " +
                "direccion = ?, numeroTelefono = ?, fechaNacimiento = ?, genero = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            setPreparedStatementForAlumno(preparedStatement, alumno);
            preparedStatement.setLong(10, alumno.getId());
        });
    }


    @Override
    public void eliminar(long id) {
        DbUtilsSql.executeUpdate(connection, "DELETE FROM Alumno WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, id));
    }

    private Alumno createAlumnoFromResultSet(ResultSet resultSet) throws Exception {
        Alumno alumno = new Alumno();
        alumno.setId(resultSet.getLong("id"));
        alumno.setTipoDocumento(TipoDocumento.setEnumTipo(resultSet.getString("tipoDocumento")));
        alumno.setNumeroDocumento(resultSet.getString("numeroDocumento"));
        alumno.setNombre(resultSet.getString("nombres"));
        alumno.setApellido(resultSet.getString("apellidos"));
        alumno.setCiudad(resultSet.getString("ciudadResidencia"));
        alumno.setDireccion(resultSet.getString("direccion"));
        alumno.setTelefono(resultSet.getString("numeroTelefono"));
        alumno.setFechaNacimiento(resultSet.getDate("fechaNacimiento"));
        alumno.setGenero(resultSet.getString("genero"));
        return alumno;
    }

    // metodos para ejecutar las consultas

    private void setPreparedStatementForAlumno(PreparedStatement preparedStatement, Alumno alumno) throws SQLException {
        preparedStatement.setString(1, alumno.getTipoDocumento().name());
        preparedStatement.setString(2, alumno.getNumeroDocumento());
        preparedStatement.setString(3, alumno.getNombre());
        preparedStatement.setString(4, alumno.getApellido());
        preparedStatement.setString(5, alumno.getCiudad());
        preparedStatement.setString(6, alumno.getDireccion());
        preparedStatement.setString(7, alumno.getTelefono());
        java.sql.Date sqlFechaNacimiento = new java.sql.Date(alumno.getFechaNacimiento().getTime());
        preparedStatement.setDate(8, sqlFechaNacimiento);
        preparedStatement.setString(9, alumno.getGenero());
    }


}