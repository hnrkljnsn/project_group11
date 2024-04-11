package no.ntnu.backend;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Flight data in the database.
 * Spring will auto-generate necessary methods.
 */

public interface FlightRepository extends CrudRepository<Flight, Integer> {
}
