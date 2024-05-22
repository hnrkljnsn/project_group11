package no.ntnu.repository;

import no.ntnu.backend.model.FavoriteFlight;
import org.springframework.data.repository.CrudRepository;

public interface UserFlightRepository extends CrudRepository<FavoriteFlight, Long> {
}