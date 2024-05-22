package no.ntnu.backend.controller;

import lombok.AllArgsConstructor;
import no.ntnu.backend.model.FavoriteFlight;
import no.ntnu.backend.model.User;
import no.ntnu.backend.security.JwtUtil;
import no.ntnu.backend.service.FavoriteFlightService;
import no.ntnu.backend.service.UserService;
import no.ntnu.repository.FavoriteFlightRepository;
import no.ntnu.repository.FlightRepository;
import no.ntnu.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final FlightRepository flightRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final FavoriteFlightService favoriteFlightService; // Add this line

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
            String token = jwtUtil.generateToken(userInDb.getUsername(), userInDb.getRole());
            Map<String, Object> response = new HashMap<>();
            response.put("status", "Login successful");
            response.put("token", token);
            response.put("userId", userInDb.getUserId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("status", "Invalid username or password"));
        }
    }

    @GetMapping("/{userId}/favorite-flights")
    public ResponseEntity<List<FavoriteFlight>> getFavoriteFlights(@PathVariable int userId) {
        Optional<User> userOpt = userRepository.findById(userId);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            List<FavoriteFlight> favoriteFlights = favoriteFlightService.getFavoriteFlights(user);
            return ResponseEntity.ok(favoriteFlights);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
    }
}
