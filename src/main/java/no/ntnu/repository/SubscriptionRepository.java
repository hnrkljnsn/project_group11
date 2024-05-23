package no.ntnu.repository;

import no.ntnu.backend.model.Subscription;
import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository <Subscription, Integer> {
    Subscription findByEmail(String email);
}
