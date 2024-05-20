package no.ntnu.backend;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository interface for accessing Flight data in the database.
 * Spring will auto-generate necessary methods.
 */

public interface FlightRepository extends CrudRepository<Flight, Integer> {
    List<Flight> findByAirlineContaining(String airline);
}
