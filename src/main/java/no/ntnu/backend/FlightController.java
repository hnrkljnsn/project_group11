package no.ntnu.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

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
}
