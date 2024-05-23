package no.ntnu.repository;

import no.ntnu.backend.model.Flight;
import org.springframework.data.repository.CrudRepository;
import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for accessing Flight data in the database.
 * Spring will auto-generate necessary methods.
 */

public interface FlightRepository extends CrudRepository<Flight, Integer> {
    List<Flight> findByDepartureCityAndReturnCityAndDepartureDateAndReturnDate(
            String departureCity, String returnCity, LocalDate departureDate, LocalDate returnDate);
}
