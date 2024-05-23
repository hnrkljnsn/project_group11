package no.ntnu.backend.controller;

import no.ntnu.backend.model.Subscription;
import no.ntnu.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class SubscriptionController {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @PostMapping("/subscribe-newsletter")
    public ResponseEntity<String> subscribeNewsletter(@RequestBody Subscription subscription) {
        try {
            if (subscriptionRepository.findByEmail(subscription.getEmail()) != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already subscribed");
            }
            subscriptionRepository.save(subscription);
            return ResponseEntity.ok("Subscribed successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
        }
    }
}
