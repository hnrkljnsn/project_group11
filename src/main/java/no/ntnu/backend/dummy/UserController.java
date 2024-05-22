package no.ntnu.backend.dummy;

import no.ntnu.backend.model.Flight;
import no.ntnu.backend.model.User;
import no.ntnu.backend.model.UserFlight;
import no.ntnu.backend.security.JwtUtil;
import no.ntnu.repository.FlightRepository;
import no.ntnu.repository.UserFlightRepository;
import no.ntnu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final UserFlightRepository userFlightRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserRepository userRepository,
                          FlightRepository flightRepository,
                          UserFlightRepository userFlightRepository,
                          JwtUtil jwtUtil,
                          PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.flightRepository = flightRepository;
        this.userFlightRepository = userFlightRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/create-account")
    public ResponseEntity<String> createAccount(@RequestBody User user) {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok("Account created successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User loginDetails) {
        User userInDb = userRepository.findByUsername(loginDetails.getUsername());
        if (userInDb != null && passwordEncoder.matches(loginDetails.getPassword(), userInDb.getPassword())) {
            String token = jwtUtil.generateToken(userInDb.getUsername(), userInDb.getRole()); // Pass both username and role
            Map<String, Object> response = new HashMap<>();
            response.put("status", "Login successful");
            response.put("token", token);
            response.put("userId", userInDb.getUserId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("status", "Invalid username or password"));
        }
    }

    @GetMapping("/account/{id}")
    public ResponseEntity<?> getAccount(@PathVariable("id") Long id) {
        ResponseEntity response;
        Optional<User> userOptional = this.userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new ResponseEntity(user, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping("/{userId}/favorite-flights")
    public ResponseEntity<String> addFavoriteFlight(@PathVariable int userId, @RequestBody Flight flight) {
        User user = userRepository.findById((long) userId).orElse(null);
        if (user != null) {
            flightRepository.save(flight);

            UserFlight userFlight = new UserFlight();
            userFlight.setUser(user);
            userFlight.setFlight(flight);

            userFlightRepository.save(userFlight);

            return ResponseEntity.ok("Flight added to favorites");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/{userId}/favorite-flights")
    public ResponseEntity<Set<Flight>> getFavoriteFlights(@PathVariable int userId) {
        User user = userRepository.findById((long) userId).orElse(null);
        if (user != null) {
            Set<UserFlight> userFlights = user.getFavoriteFlights();
            Set<Flight> favoriteFlights = userFlights.stream()
                    .map(UserFlight::getFlight)
                    .collect(Collectors.toSet());
            return ResponseEntity.ok(favoriteFlights);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
