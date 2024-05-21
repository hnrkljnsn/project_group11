package no.ntnu.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    private FlightRepository flightRepository;

    @PostMapping("/create-account")
    public ResponseEntity<String> createAccount(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        userRepository.save(user);
        return ResponseEntity.ok("Account created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "Login successful");
            response.put("userId", existingUser.getUserId());
            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "Invalid username or password");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PostMapping("/{userId}/favorite-flights")
    public ResponseEntity<String> addFavoriteFlight(@PathVariable int userId, @RequestBody Flight flight) {
        User user = userRepository.findById((long)userId).orElse(null);
        if (user != null) {
            flightRepository.save(flight);
            user.getFavoriteFlights().add(flight);
            userRepository.save(user);
            return ResponseEntity.ok("Flight added to favorites");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/{userId}/favorite-flights")
    public ResponseEntity<List<Flight>> getFavoriteFlights(@PathVariable int userId) {
        User user = userRepository.findById((long)userId).orElse(null);
        if (user != null) {
            List<Flight> favoriteFlights = user.getFavoriteFlights();
            favoriteFlights.size();
            return ResponseEntity.ok(favoriteFlights);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}