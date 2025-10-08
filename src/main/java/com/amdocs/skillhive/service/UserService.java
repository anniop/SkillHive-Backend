package com.amdocs.skillhive.service;

import com.amdocs.skillhive.model.User;
import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(Integer userId, User user);
    User getUserById(Integer userId);
    List<User> getAllUsers();
    void deleteUser(Integer userId);
}