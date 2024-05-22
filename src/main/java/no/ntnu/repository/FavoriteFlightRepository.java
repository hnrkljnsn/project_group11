package no.ntnu.repository;

import no.ntnu.backend.model.FavoriteFlight;
import no.ntnu.backend.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteFlightRepository extends ListCrudRepository<FavoriteFlight, Integer> {

    List<FavoriteFlight> findByUser(User user);

}
