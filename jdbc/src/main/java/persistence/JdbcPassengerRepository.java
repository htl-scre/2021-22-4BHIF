package persistence;

import domain.Passenger;

import java.sql.*;
import java.util.*;

public record JdbcPassengerRepository(Connection connection) implements PassengerRepository {

    @Override
    public Passenger save(Passenger passenger) throws Exception {
        if (passenger.id() != null)
            throw new IllegalArgumentException("ids are auto generated");
        var sql = """
                insert into passengers (name, age)
                values (?, ?);
                """;
        try (var preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, passenger.name());
            preparedStatement.setInt(2, passenger.age());
            int updated = preparedStatement.executeUpdate();
            if (updated == 0)
                throw new SQLException("Saving failed");

            var generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                var id = generatedKeys.getInt(1);
                return new Passenger(id, passenger.name(), passenger.age());
            } else
                throw new SQLException("Saving failed");
        }
    }

    @Override
    public Optional<Passenger> findById(int id) throws Exception {
        var sql = """
                select name, age from passengers
                where id = ?;
                """;
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                var name = resultSet.getString("name");
                var age = resultSet.getInt("age");
                return Optional.of(new Passenger(id, name, age));
            } else
                return Optional.empty();
        }
    }

    @Override
    public Collection<Passenger> findAllByName(String name) throws Exception {
        var sql = """
                select id, age from passengers
                where name = ?;
                """;
        var result = new ArrayList<Passenger>();
        try (var preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                var id = resultSet.getInt("id");
                var age = resultSet.getInt("age");
                result.add(new Passenger(id, name, age));
            }
            return result;
        }
    }
}
