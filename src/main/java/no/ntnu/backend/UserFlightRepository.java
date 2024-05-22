package no.ntnu.backend;

import org.springframework.data.repository.CrudRepository;

public interface UserFlightRepository extends CrudRepository<UserFlight, Integer> {
    void deleteByFlight_flightId(Integer flightId);
}