package no.ntnu.backend.dummy;

import no.ntnu.backend.model.User;
import no.ntnu.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DummyUserDataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger("DummyUserInitializer");

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public DummyUserDataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Importing user test data...");
        if (userRepository.count() == 0) {
            createUser("1", "1", "USER");
            createUser("2", "2", "USER");
            createUser("3", "3", "ADMIN");

            logger.info("DONE importing user test data");
        } else {
            logger.info("All user test data already imported...");
        }
    }

    private void createUser(String username, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        userRepository.save(user);
    }
}
