package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryCurso;
import org.montanez.sistema_gestion_universidad.repository.models.Curso;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryCurso implements RepositoryCurso {
    private Connection connection;

    public ImpRepositoryCurso() throws Exception {
        this.connection = new ConexionMsql().getConnection();
    }


    @Override
    public List<Curso> listar() {
        List Curso = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Curso", resultSet -> Curso.add(createCursoFromResultSet(resultSet)));
        return Curso;
    }

    @Override
    public Curso curso_id(long id) {
        Curso[] Curso = new Curso[1];
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Curso WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, id),
                resultSet -> Curso[0] = createCursoFromResultSet(resultSet));
        return Curso[0];
    }

    @Override
    public void crear(Curso curso) {
        String sql = "INSERT INTO Curso (nombre, guiaCatedra) VALUES (?, ?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> setPreparedStatementForCurso(preparedStatement, curso));
    }

    @Override
    public void editar(Curso curso) {
        String sql = "UPDATE Curso SET nombre = ?, guiaCatedra = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            setPreparedStatementForCurso(preparedStatement, curso);
            preparedStatement.setLong(3, curso.getId());
        });

    }

    @Override
    public void eliminar(long id) {
        String sql = "DELETE FROM Curso WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> preparedStatement.setLong(1, id));
    }

    private Curso createCursoFromResultSet(ResultSet resultSet) throws Exception {
        Curso curso = new Curso();
        curso.setId(resultSet.getLong("id"));
        curso.setNombre(resultSet.getString("nombre"));
        curso.setGuiaCatedra(resultSet.getString("guiaCatedra"));
        return curso;
    }

    private void setPreparedStatementForCurso(PreparedStatement preparedStatement, Curso curso) {
        try {
            preparedStatement.setString(1, curso.getNombre());
            preparedStatement.setString(2, curso.getGuiaCatedra());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
