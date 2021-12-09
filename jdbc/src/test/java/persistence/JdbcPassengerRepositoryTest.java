package persistence;

import domain.Passenger;
import org.junit.jupiter.api.*;

import java.sql.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class JdbcPassengerRepositoryTest {

    public static final String JDBC_URL = "jdbc:h2:mem:passenger-test;INIT=RUNSCRIPT FROM 'classpath:schema.sql'";
    private Connection connection;
    private PassengerRepository repository;

    @BeforeEach
    void createRepository() throws SQLException {
        connection = DriverManager.getConnection(JDBC_URL);
        repository = new JdbcPassengerRepository(connection);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        connection.close();
    }

    @Test
    void saving_works() throws Exception {
        var passenger = new Passenger("götz", 3);

        var saved = repository.save(passenger);

        assertThat(repository.findById(saved.id()))
                .contains(saved);
    }

    @Test
    void saving_fails_if_id_not_null() throws Exception {
        var passenger = new Passenger(42, "götz", 3);

        assertThatThrownBy(() -> repository.save(passenger));
    }

}