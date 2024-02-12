package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryMatricula;
import org.montanez.sistema_gestion_universidad.repository.models.*;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.DbUtilsSql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ImpRepositoryMatricula implements RepositoryMatricula {
    private Connection connection;
    private ImpRepositoryAlumno alumnoRepository;
    private ImpRepositoryAsignatura asignaturaRepository;
    private ImpRepositoryPeriodo periodoRepository;
    private ImpRepositorySalon salonRepository;
    private ImpRepositoryHorario horarioRepository;


    public ImpRepositoryMatricula() throws Exception {
        this.connection = new ConexionMsql().getConnection();
        this.alumnoRepository = new ImpRepositoryAlumno();
        this.asignaturaRepository = new ImpRepositoryAsignatura();
        this.periodoRepository = new ImpRepositoryPeriodo();
        this.salonRepository = new ImpRepositorySalon();
        this.horarioRepository = new ImpRepositoryHorario();
    }

    public static void main(String[] args) {
        try {
            ImpRepositoryMatricula impRepositoryMatricula = new ImpRepositoryMatricula();
            System.out.println(impRepositoryMatricula.matricula_id(5));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Matricula> listar() {
        List<Matricula> matriculas = new ArrayList<>();
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Matricula", resultSet -> matriculas.add(createMatriculaFromResultSet(resultSet)));
        return matriculas;
    }

    private Matricula createMatriculaFromResultSet(ResultSet resultSet) {
        Matricula matricula = new Matricula();
        try {
            long id = resultSet.getLong("id");
            long alumnoId = resultSet.getLong("alumno_id");
            long asignaturaId = resultSet.getLong("asignatura_id");
            long periodoId = resultSet.getLong("periodo_id");
            long salonId = resultSet.getLong("salon_id");
            long horarioId = resultSet.getLong("horario_id");


            Alumno alumno = alumnoRepository.alumno_id(alumnoId);
            Asignatura asignatura = asignaturaRepository.asignatura_id(asignaturaId);
            Periodo periodo = periodoRepository.buscarId(periodoId);
            Salon salon = salonRepository.salon_id(salonId);
            Horario horario = horarioRepository.horario_id(horarioId);


            matricula.setId(id);
            matricula.setAlumno(alumno);
            matricula.setAsignatura(asignatura);
            matricula.setPeriodo(periodo);
            matricula.setSalon(salon);
            matricula.setHorario(horario);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return matricula;
    }

    @Override
    public Matricula matricula_id(long id) {
        Matricula[] matricula = new Matricula[1];
        DbUtilsSql.executeQuery(connection, "SELECT * FROM Matricula WHERE id = ?",
                preparedStatement -> preparedStatement.setLong(1, id),
                resultSet -> matricula[0] = createMatriculaFromResultSet(resultSet));
        if (matricula[0] == null) {
            throw new RuntimeException("No se encontró ninguna matrícula con el ID " + id);
        }
        return matricula[0];
    }

    @Override
    public void crear(Matricula matricula) {
        String sql = "INSERT INTO Matricula (alumno_id, asignatura_id, periodo_id, salon_id, horario_id) VALUES (?, ?, ?, ?, ?)";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setLong(1, matricula.getAlumno().getId());
            preparedStatement.setLong(2, matricula.getAsignatura().getId());
            preparedStatement.setLong(3, matricula.getPeriodo().getId());
            preparedStatement.setLong(4, matricula.getSalon().getId());
            preparedStatement.setLong(5, matricula.getHorario().getId());
        });
    }

    @Override
    public void editar(Matricula matricula) {
        String sql = "UPDATE Matricula SET alumno_id = ?, asignatura_id = ?, periodo_id = ?, salon_id = ?, horario_id = ? WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> {
            preparedStatement.setLong(1, matricula.getAlumno().getId());
            preparedStatement.setLong(2, matricula.getAsignatura().getId());
            preparedStatement.setLong(3, matricula.getPeriodo().getId());
            preparedStatement.setLong(4, matricula.getSalon().getId());
            preparedStatement.setLong(5, matricula.getHorario().getId());
            preparedStatement.setLong(6, matricula.getId());
        });
    }

    @Override
    public void eliminar(long id) {
        String sql = "DELETE FROM Matricula WHERE id = ?";
        DbUtilsSql.executeUpdate(connection, sql, preparedStatement -> preparedStatement.setLong(1, id));
    }
}
