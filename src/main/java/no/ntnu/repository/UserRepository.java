package no.ntnu.repository;

import no.ntnu.backend.model.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);
}