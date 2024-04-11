package no.ntnu.backend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import no.ntnu.backend.Flight;

import java.time.LocalDate;

@Component
public class DummyDataInitializer implements ApplicationListener<ApplicationReadyEvent>{
    @Autowired
    private FlightRepository flightRepository;
    private final Logger logger = LoggerFactory.getLogger("DummyInitializer");

    /**
     * This method is called when the application is ready (loaded).
     *
     * @param event Event which we don't use :)
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        logger.info("Importing test data...");
        if(flightRepository.count()==0) {
            Flight flight1 = new Flight();
            flight1.flightId=1;
            flight1.flightName="Flight1";
            flight1.roundTrip=true;
            flight1.departureDate= LocalDate.of(2001, 1, 1);
            flight1.returnDate= LocalDate.of(2001, 1, 1);

            Flight flight2 = new Flight();
            flight2.flightId=2;
            flight2.flightName="Flight2";
            flight2.roundTrip=true;
            flight2.departureDate= LocalDate.of(2002, 2, 2);
            flight2.returnDate= LocalDate.of(2002, 2, 2);

            Flight flight3 = new Flight();
            flight3.flightId=3;
            flight3.flightName="Flight3";
            flight3.roundTrip=true;
            flight3.departureDate= LocalDate.of(2003, 3, 3);
            flight3.returnDate= LocalDate.of(2003, 3, 3);

            flightRepository.save(flight1);
            flightRepository.save(flight2);
            flightRepository.save(flight3);

            logger.info("DONE importing test data");
        }
        else
        {
            logger.info("All test data already imported...");
        }
    }
}
