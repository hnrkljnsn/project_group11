package no.ntnu.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DummyUserDataInitializer implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    private UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger("DummyUserInitializer");

    /**
     * This method is called when the application is ready (loaded).
     *
     * @param event Event which we don't use :)
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Importing user test data...");
        if(userRepository.count()==0) {
            User user1 = new User();
            user1.setUserId(1);
            user1.setUsername("1");
            user1.setPassword("1");

            User user2 = new User();
            user2.setUserId(2);
            user2.setUsername("2");
            user2.setPassword("2");

            User user3 = new User();
            user3.setUserId(3);
            user3.setUsername("3");
            user3.setPassword("3");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            logger.info("DONE importing user test data");
        }
        else
        {
            logger.info("All user test data already imported...");
        }
    }
}
