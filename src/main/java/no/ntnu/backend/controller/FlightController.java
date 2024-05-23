package no.ntnu.backend.controller;

import no.ntnu.backend.model.Flight;
import no.ntnu.repository.FavoriteFlightRepository;
import no.ntnu.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private FavoriteFlightRepository favoriteFlightRepository;

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(
            @RequestParam(required = false) String departureCity,
            @RequestParam(required = false) String returnCity,
            @RequestParam(required = false) LocalDate departureDate,
            @RequestParam(required = false) LocalDate returnDate) {

        if (departureCity == null && returnCity == null && departureDate == null && returnDate == null) {
            return ResponseEntity.ok((List<Flight>) flightRepository.findAll());
        }

        List<Flight> flights = flightRepository.findByDepartureCityAndReturnCityAndDepartureDateAndReturnDate(
                departureCity, returnCity, departureDate, returnDate);

        return ResponseEntity.ok(flights);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/flights/{flightId}")
    @Transactional
    public ResponseEntity<Void> deleteFlight(@PathVariable Integer flightId) {
        favoriteFlightRepository.deleteByFlight_flightId(flightId);
        if (flightRepository.existsById(flightId)) {
            flightRepository.deleteById(flightId);

            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
