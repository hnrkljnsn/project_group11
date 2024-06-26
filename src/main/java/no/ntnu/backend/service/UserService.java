package no.ntnu.backend.service;

import no.ntnu.backend.model.User;
import no.ntnu.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getUsers() {
        return this.userRepository.findAll();
    }

    public Optional<User> getUserById(int id) {
        return this.userRepository.findById(id);
    }
}