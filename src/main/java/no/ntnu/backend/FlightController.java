package no.ntnu.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @GetMapping("/search")
    public ResponseEntity<List<Flight>> searchFlights(@RequestParam String query) {
        List<Flight> flights = flightRepository.findByFlightNameContaining(query);
        return ResponseEntity.ok(flights);
    }
}
