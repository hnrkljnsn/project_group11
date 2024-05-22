package no.ntnu.backend.controller;

import lombok.AllArgsConstructor;
import no.ntnu.backend.model.FavoriteFlight;
import no.ntnu.backend.model.User;
import no.ntnu.backend.service.FavoriteFlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/favoriteflights")
@RestController
@AllArgsConstructor
public class FavoriteFlightController {

    private final FavoriteFlightService favoriteFlightService;

    @GetMapping
    public List<FavoriteFlight> getFavoriteFlights(@AuthenticationPrincipal User user) {
        if (user == null) {
            System.out.println("Authenticated user is null");
            throw new IllegalArgumentException("User must be authenticated");
        }
        return this.favoriteFlightService.getFavoriteFlights(user);
    }
}
