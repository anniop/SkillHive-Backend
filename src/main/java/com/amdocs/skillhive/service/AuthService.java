package com.amdocs.skillhive.service;

import com.amdocs.skillhive.model.User;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {
	// token -> userId
	private final Map<String, Integer> tokens = new ConcurrentHashMap<>();

	@Autowired
	private UserService userService;

	// simple login: find user by email and password (plain text) via userService.getAllUsers()
	// (Adjust to your real storage / hashing later)
	public Optional<String> login(String email, String password) {
		if (email == null || password == null) return Optional.empty();
		for (User u : userService.getAllUsers()) {
			if (email.equalsIgnoreCase(u.getEmail()) && matchesPassword(u, password)) {
				String token = UUID.randomUUID().toString();
				tokens.put(token, u.getUserId());
				return Optional.of(token);
			}
		}
		return Optional.empty();
	}

	public void logout(String token) {
		if (token != null) tokens.remove(token);
	}

	public Optional<User> getUserByToken(String token) {
		if (token == null) return Optional.empty();
		Integer userId = tokens.get(token);
		if (userId == null) return Optional.empty();
		try {
			return Optional.of(userService.getUserById(userId));
		} catch (Exception e) {
			return Optional.empty();
		}
	}

	// attempt to read the user's password via a getter or common field names using reflection
	private boolean matchesPassword(User u, String password) {
		if (u == null || password == null) return false;
		try {
			// try a standard getter first
			java.lang.reflect.Method m = u.getClass().getMethod("getPassword");
			Object pwObj = m.invoke(u);
			return password.equals(pwObj);
		} catch (NoSuchMethodException e) {
			// no getPassword(), try common field names
			try {
				java.lang.reflect.Field f = u.getClass().getDeclaredField("password");
				f.setAccessible(true);
				Object pwObj = f.get(u);
				return password.equals(pwObj);
			} catch (NoSuchFieldException | IllegalAccessException ex) {
				try {
					java.lang.reflect.Field f2 = u.getClass().getDeclaredField("pwd");
					f2.setAccessible(true);
					Object pwObj = f2.get(u);
					return password.equals(pwObj);
				} catch (Exception ex2) {
					return false;
				}
			}
		} catch (Exception e) {
			// any other reflection problems -> treat as non-matching
			return false;
		}
	}
}
