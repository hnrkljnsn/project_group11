package no.ntnu.repository;

import no.ntnu.backend.model.UserFlight;
import org.springframework.data.repository.CrudRepository;

public interface UserFlightRepository extends CrudRepository<UserFlight, Long> {
}