package com.amdocs.skillhive.service;

import com.amdocs.skillhive.model.User;
import com.amdocs.skillhive.repository.UserRepository;
import com.amdocs.skillhive.util.PasswordUtils;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        // validate payload to avoid NPEs (line ~22)
        if (user == null) {
            throw new IllegalArgumentException("User payload must not be null");
        }

        String hashedPassword = PasswordUtils.hashPassword(user.getPasswordHash());
        user.setPasswordHash(hashedPassword);

        // Set default role if not provided
        if (user.getRole() == null) {
            // TODO: assign a concrete default role here, e.g.:
            // user.setRole(Role.USER);
            // leaving role as null for now to avoid compile error
        }
        // Set default dateJoined if not provided
        if (user.getDateJoined() == null) {
            user.setDateJoined(Instant.now());
        }
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Integer userId, User user) {
        if (user == null) {
            throw new IllegalArgumentException("User payload must not be null for update");
        }
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
        // Ensure the ID is set correctly for update
        user.setUserId(userId);
        // If role is null during update, keep the existing role
        User existing = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User not found with id: " + userId));
        if (user.getRole() == null) {
            user.setRole(existing.getRole());
        }
        // If dateJoined is null during update, keep existing
        if (user.getDateJoined() == null) {
            user.setDateJoined(existing.getDateJoined());
        }
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Integer userId) {
        if (!userRepository.existsById(userId)) {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
        userRepository.deleteById(userId);
    }
}
