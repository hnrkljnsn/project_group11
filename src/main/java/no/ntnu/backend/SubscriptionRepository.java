package no.ntnu.backend;

import org.springframework.data.repository.CrudRepository;

public interface SubscriptionRepository extends CrudRepository <Subscription, Long> {
    Subscription findByEmail(String email);
}
