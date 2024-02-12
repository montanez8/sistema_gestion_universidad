package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryAsignatura;
import org.montanez.sistema_gestion_universidad.repository.models.*;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.montanez.sistema_gestion_universidad.views.ViewMain.*;

public class ImpRepositoryAsignatura implements RepositoryAsignatura {
    private Connection connection;

    public ImpRepositoryAsignatura() throws Exception {
        this.connection = new ConexionMsql().getConnection();
    }

    public static void main(String[] args) {
        try {
            ImpRepositoryAsignatura impRepositoryAsignatura = new ImpRepositoryAsignatura();
            System.out.println(impRepositoryAsignatura.asignatura_id(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        String sql = "INSERT INTO Asignatura(nombre, creditos, cupoDisponible, profesor_id, programa_id, curso_id, periodo_id) VALUES( ?, ?, ?, ?, ?, ?, ?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setString(1, asignatura.getNombre());
            preparedStatement.setInt(2, asignatura.getCreditos());
            preparedStatement.setInt(3, asignatura.getCupo());
            preparedStatement.setLong(4, asignatura.getProfesor().getId());
            preparedStatement.setLong(5, asignatura.getPrograma().getId());
            preparedStatement.setLong(6, asignatura.getCurso().getId());
            preparedStatement.setLong(7, asignatura.getPeriodo().getId());
        });
    }

    @Override
    public void editar(Asignatura asignatura) {
        String sql = "UPDATE Asignatura SET nombre = ?, creditos = ?, cupoDisponible = ?, profesor_id = ?, programa_id = ?, curso_id = ?, periodo_id = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setString(1, asignatura.getNombre());
            preparedStatement.setInt(2, asignatura.getCreditos());
            preparedStatement.setInt(3, asignatura.getCupo());
            preparedStatement.setLong(4, asignatura.getProfesor().getId());
            preparedStatement.setLong(5, asignatura.getPrograma().getId());
            preparedStatement.setLong(6, asignatura.getCurso().getId());
            preparedStatement.setLong(7, asignatura.getPeriodo().getId());
            preparedStatement.setLong(8, asignatura.getId());
        });
    }


    @Override
    public void eliminar(long id) {
        String sql = "DELETE FROM Asignatura WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> preparedStatement.setLong(1, id));
    }

    private Asignatura createAsignaturaFromResultSet(ResultSet resultSet) throws Exception {
        Asignatura asignatura = new Asignatura();
        asignatura.setId(resultSet.getLong("id"));
        asignatura.setNombre(resultSet.getString("nombre"));
        asignatura.setCodigo(resultSet.getString("codigo"));
        asignatura.setCreditos(resultSet.getInt("creditos"));
        asignatura.setCupo(resultSet.getInt("cupoDisponible"));

        long profesorId = resultSet.getLong("profesor_id");
        Profesor profesor = serviceProfesor.profesor_id(profesorId);
        asignatura.setProfesor(profesor);

        long programaId = resultSet.getLong("programa_id");
        Programa programa = servicePrograma.programa_id(programaId);
        asignatura.setPrograma(programa);

        long cursoId = resultSet.getLong("curso_id");
        Curso curso = serviceCurso.curso_id(cursoId);
        asignatura.setCurso(curso);

        long periodoId = resultSet.getLong("periodo_id");
        Periodo periodo = servicePeriodo.buscarId(periodoId);
        asignatura.setPeriodo(periodo);

        return asignatura;
    }
}
