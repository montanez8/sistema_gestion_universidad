package org.montanez.sistema_gestion_universidad.repository.imp;

import org.montanez.sistema_gestion_universidad.repository.RepositoryReportes;
import org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql.ConexionMsql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ImpRepositoryReportes implements RepositoryReportes {
    private final Connection connection;

    public ImpRepositoryReportes() throws Exception {
        this.connection = new ConexionMsql().getConnection();
    }

    @Override
    public List<Map<String, Object>> listarEstudiantesPorPrograma(int programaId) {
        List<Map<String, Object>> estudiantes = new ArrayList<>();
        String sql = "SELECT Alumno.id AS id_alumno, Alumno.nombres, Alumno.apellidos, Programa.nombre AS programa " +
                "FROM Alumno " +
                "JOIN Matricula ON Alumno.id = Matricula.alumno_id " +
                "JOIN Asignatura ON Matricula.asignatura_id = Asignatura.id " +
                "JOIN Programa ON Asignatura.programa_id = Programa.id " +
                "WHERE Programa.id = ? " +
                "ORDER BY Programa.nombre, Alumno.apellidos, Alumno.nombres";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, programaId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> estudiantePrograma = new HashMap<>();
                estudiantePrograma.put("idAlumno", resultSet.getLong("id_alumno"));
                estudiantePrograma.put("nombres", resultSet.getString("nombres"));
                estudiantePrograma.put("apellidos", resultSet.getString("apellidos"));
                estudiantePrograma.put("programa", resultSet.getString("programa"));
                estudiantes.add(estudiantePrograma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    @Override
    public List<Map<String, Object>> calcularCostoSemestre(int alumnoId) {
        List<Map<String, Object>> detallesEstudianteList = new ArrayList<>();
        String sql = "SELECT Alumno.id AS id_alumno, Alumno.nombres, Alumno.apellidos, Periodo.codigo AS periodo, SUM(Tarifa.valorCredito * Asignatura.creditos) AS costo_semestre " +
                "FROM Alumno " +
                "JOIN Matricula ON Alumno.id = Matricula.alumno_id " +
                "JOIN Asignatura ON Matricula.asignatura_id = Asignatura.id " +
                "JOIN Periodo ON Matricula.periodo_id = Periodo.id " +
                "JOIN Tarifa ON Periodo.id = Tarifa.periodo_id AND Asignatura.programa_id = Tarifa.programa_id " +
                "WHERE Alumno.id = ? " +
                "GROUP BY Alumno.id, Alumno.nombres, Alumno.apellidos, Periodo.id";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, alumnoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> detallesEstudiante = new HashMap<>();
                detallesEstudiante.put("id_alumno", resultSet.getLong("id_alumno"));
                detallesEstudiante.put("nombres", resultSet.getString("nombres"));
                detallesEstudiante.put("apellidos", resultSet.getString("apellidos"));
                detallesEstudiante.put("periodo", resultSet.getString("periodo"));
                detallesEstudiante.put("costo_semestre", resultSet.getDouble("costo_semestre"));
                detallesEstudianteList.add(detallesEstudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return detallesEstudianteList;
    }

    @Override
    public double calcularIngresosUniversidad(int periodoId) {
        double ingresosUniversidad = 0;
        String sql = "SELECT SUM(Tarifa.valorCredito * Asignatura.creditos) AS ingresos_universidad " +
                "FROM Matricula " +
                "JOIN Asignatura ON Matricula.asignatura_id = Asignatura.id " +
                "JOIN Periodo ON Matricula.periodo_id = Periodo.id " +
                "JOIN Tarifa ON Periodo.id = Tarifa.periodo_id AND Asignatura.programa_id = Tarifa.programa_id " +
                "WHERE Periodo.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, periodoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                ingresosUniversidad = resultSet.getDouble("ingresos_universidad");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ingresosUniversidad;
    }


    @Override
    public List<Map<String, Object>> imprimirHorarioEstudiante(int alumnoId) {
        List<Map<String, Object>> horarioEstudiante = new ArrayList<>();
        String sql = "SELECT A.nombres AS nombre_estudiante, A.apellidos AS apellido_estudiante, ASG.nombre AS asignatura, H.dia AS dia, H.horaInicio AS hora_inicio, H.horaFin AS hora_final " +
                "FROM Alumno A " +
                "JOIN Matricula M ON A.id = M.alumno_id " +
                "JOIN Asignatura ASG ON M.asignatura_id = ASG.id " +
                "JOIN Horario H ON M.horario_id = H.id " +
                "WHERE A.id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, alumnoId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> detalleHorario = new HashMap<>();
                detalleHorario.put("nombre_estudiante", resultSet.getString("nombre_estudiante"));
                detalleHorario.put("apellido_estudiante", resultSet.getString("apellido_estudiante"));
                detalleHorario.put("asignatura", resultSet.getString("asignatura"));
                detalleHorario.put("dia", resultSet.getString("dia"));
                detalleHorario.put("hora_inicio", resultSet.getTime("hora_inicio"));
                detalleHorario.put("hora_final", resultSet.getTime("hora_final"));
                horarioEstudiante.add(detalleHorario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return horarioEstudiante;
    }

    @Override
    public List<Map<String, Object>> imprimirMatriculadosPorPrograma() {
        List<Map<String, Object>> matriculadosPorPrograma = new ArrayList<>();
        String sql = "SELECT P.nombre AS Nombre_programa, COUNT(A.id) AS estudiantes_matriculados " +
                "FROM Programa P " +
                "JOIN Asignatura ASG ON P.id = ASG.programa_id " +
                "JOIN Matricula M ON ASG.id = M.asignatura_id " +
                "JOIN Alumno A ON M.alumno_id = A.id " +
                "GROUP BY P.nombre " +
                "ORDER BY estudiantes_matriculados DESC";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Map<String, Object> detallePrograma = new HashMap<>();
                detallePrograma.put("Nombre_programa", resultSet.getString("Nombre_programa"));
                detallePrograma.put("estudiantes_matriculados", resultSet.getInt("estudiantes_matriculados"));
                matriculadosPorPrograma.add(detallePrograma);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matriculadosPorPrograma;
    }
}