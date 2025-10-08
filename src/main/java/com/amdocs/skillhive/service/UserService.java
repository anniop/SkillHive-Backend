package com.amdocs.skillhive.service;

import com.amdocs.skillhive.model.User;
import java.util.List;

public interface UserService {

    // Create a new user
    User createUser(User user);

    // Update existing user by ID
    User updateUser(Integer userId, User user);

    // Fetch a user by ID
    User getUserById(Integer userId);

    // Fetch all users
    List<User> getAllUsers();

    // Delete a user by ID
    void deleteUser(Integer userId);
}
