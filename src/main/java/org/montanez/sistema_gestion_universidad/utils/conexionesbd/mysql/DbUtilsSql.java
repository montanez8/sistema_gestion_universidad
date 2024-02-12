package org.montanez.sistema_gestion_universidad.utils.conexionesbd.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbUtilsSql {
    public static void executeQuery(Connection connection, String sql, PreparedStatementConsumer preparedStatementConsumer, ResultSetConsumer resultSetConsumer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (preparedStatementConsumer != null) {
                preparedStatementConsumer.accept(preparedStatement);
            }
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    resultSetConsumer.accept(resultSet);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public static void executeUpdate(Connection connection, String sql, PreparedStatementConsumer preparedStatementConsumer) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatementConsumer.accept(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void executeQuery(Connection connection, String sql, ResultSetConsumer resultSetConsumer) {
        executeQuery(connection, sql, null, resultSetConsumer);
    }

    @FunctionalInterface
    public interface PreparedStatementConsumer {
        void accept(PreparedStatement preparedStatement) throws SQLException;
    }

    @FunctionalInterface
    public interface ResultSetConsumer {
        void accept(ResultSet resultSet) throws Exception;
    }
}
