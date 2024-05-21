package no.ntnu.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

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
    public ResponseEntity<String> login(@RequestBody User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
        }
    }

    @PostMapping("/{userId}/favorite-flights")
    public ResponseEntity<String> addFavoriteFlight(@PathVariable int userId, @RequestBody Flight flight) {
        User user = userRepository.findById((long) userId).orElse(null);
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
        User user = userRepository.findById((long) userId).orElse(null);
        if (user != null) {
            return ResponseEntity.ok(user.getFavoriteFlights());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}