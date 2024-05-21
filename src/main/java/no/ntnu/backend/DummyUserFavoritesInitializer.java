package no.ntnu.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class DummyUserFavoritesInitializer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private UserFlightRepository userFlightRepository;

    private final Logger logger = LoggerFactory.getLogger("DummyUserFavorites");

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Setting favorite flights for users...");

        List<User> users = (List<User>) userRepository.findAll();
        List<Flight> flights = (List<Flight>) flightRepository.findAll();

        if (users.size() >= 3 && flights.size() >= 3) {
            User user1 = users.get(0);
            User user2 = users.get(1);
            User user3 = users.get(2);

            // Create and save UserFlight relationships
            createAndSaveUserFlight(user1, flights.get(0));
            createAndSaveUserFlight(user1, flights.get(1));
            createAndSaveUserFlight(user2, flights.get(2));
            createAndSaveUserFlight(user3, flights.get(1));
            createAndSaveUserFlight(user3, flights.get(2));

            logger.info("Favorite flights set for users.");
        } else {
            logger.warn("Not enough users or flights to set favorite flights.");
        }
    }

    private void createAndSaveUserFlight(User user, Flight flight) {
        UserFlight userFlight = new UserFlight();
        userFlight.setUser(user);
        userFlight.setFlight(flight);
        userFlightRepository.save(userFlight);
    }
}