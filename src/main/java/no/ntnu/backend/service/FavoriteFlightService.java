package no.ntnu.backend.service;

import lombok.AllArgsConstructor;
import no.ntnu.backend.model.FavoriteFlight;
import no.ntnu.backend.model.User;
import no.ntnu.repository.FavoriteFlightRepository;
import no.ntnu.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteFlightService {

    private final FavoriteFlightRepository favoriteFlightRepository;
    private final UserRepository userRepository;

    public List<FavoriteFlight> getFavoriteFlights(User user) {
        return this.favoriteFlightRepository.findByUser(user);
    }

    public void addFavoriteFlight(FavoriteFlight favoriteFlight, String username) {
        User user = userRepository.findByUsername(username);
        favoriteFlight.setUser(user);
        favoriteFlightRepository.save(favoriteFlight);
    }
}
