package com.pryhmez.collabomain.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        Optional<User> userOptional = userRepository.findByUsername(auth.getName().toString());

        if (userOptional.isPresent()) {
            return userOptional.get(); // User found
        } else {
            // Handle the case where the user is not found (e.g., return null or throw an exception)
            return null; // Or throw a custom exception
        }
    }

    public User getUser(String username) {

        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isPresent()) {
            return userOptional.get(); // User found
        } else {
            // Handle the case where the user is not found (e.g., return null or throw an exception)
            return null; // Or throw a custom exception
        }
    }
}
