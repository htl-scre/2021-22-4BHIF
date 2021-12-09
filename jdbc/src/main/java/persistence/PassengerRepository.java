package persistence;

import domain.Passenger;

import java.util.Collection;
import java.util.Optional;

public interface PassengerRepository {

    Passenger save(Passenger passenger) throws Exception;

    Optional<Passenger> findById(int id) throws Exception;

    Collection<Passenger> findAllByName(String name) throws Exception;
}
