package firstConnection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Demo {

    public static void main(String[] args) throws SQLException {
        try (var connection = DriverManager.getConnection("jdbc:postgresql://ifpostgres02:5432/scre_trains", "unterricht", "unterricht")) {
            var statement = connection.createStatement();
            var sql = """
                    select name, age from passengers;
                    """;
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                var name = resultSet.getString("name");
                var age = resultSet.getInt("age");
                System.out.printf("%s %d %n", name, age);
            }
        }
    }
}
