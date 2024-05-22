package no.ntnu.backend.controller;

import lombok.AllArgsConstructor;
import no.ntnu.backend.model.FavoriteFlight;
import no.ntnu.backend.model.User;
import no.ntnu.backend.service.FavoriteFlightService;
import no.ntnu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/favoriteflights")
@AllArgsConstructor
public class FavoriteFlightController {

    private final FavoriteFlightService favoriteFlightService;
    private final UserRepository userRepository;

    @GetMapping
    public List<FavoriteFlight> getFavoriteFlights(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            throw new IllegalArgumentException("User must be authenticated");
        }

        User user = userRepository.findByUsername(userDetails.getUsername());
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return this.favoriteFlightService.getFavoriteFlights(user);
    }
}
