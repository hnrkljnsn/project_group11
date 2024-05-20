package no.ntnu.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DummyFlightDataInitializer implements ApplicationListener<ApplicationReadyEvent>{
    @Autowired
    private FlightRepository flightRepository;
    private final Logger logger = LoggerFactory.getLogger("DummyFlightInitializer");

    /**
     * This method is called when the application is ready (loaded).
     *
     * @param event Event which we don't use :)
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Importing flight test data...");
        if(flightRepository.count()==0) {
            Flight flight1 = new Flight();
            flight1.flightId=1;
            flight1.airline ="Norwegian";
            flight1.roundTrip=true;
            flight1.departureDate= LocalDate.of(2024, 1, 1);
            flight1.returnDate= LocalDate.of(2024, 1, 2);
            flight1.departureCity="Ålesund";
            flight1.returnCity="Oslo";
            flight1.price=1000;

            Flight flight2 = new Flight();
            flight2.flightId=2;
            flight2.airline ="SAS";
            flight2.roundTrip=true;
            flight2.departureDate= LocalDate.of(2024, 2, 1);
            flight2.returnDate= LocalDate.of(2024, 2, 2);
            flight2.departureCity="Oslo";
            flight2.returnCity="Paris";
            flight2.price=2000;

            Flight flight3 = new Flight();
            flight3.flightId=3;
            flight3.airline ="Emirates";
            flight3.roundTrip=true;
            flight3.departureDate= LocalDate.of(2024, 3, 1);
            flight3.returnDate= LocalDate.of(2024, 3, 2);
            flight3.departureCity="Trondheim";
            flight3.returnCity="Istanbul";
            flight3.price=3000;

            flightRepository.save(flight1);
            flightRepository.save(flight2);
            flightRepository.save(flight3);

            logger.info("DONE importing flight test data");
        }
        else
        {
            logger.info("All flight test data already imported...");
        }
    }
}
