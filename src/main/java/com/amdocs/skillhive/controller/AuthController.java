package com.amdocs.skillhive.controller;

import com.amdocs.skillhive.model.User;
import com.amdocs.skillhive.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
		String email = body.get("email");
		String password = body.get("password");
		Optional<String> token = authService.login(email, password);
		if (token.isPresent()) {
			return ResponseEntity.ok(Map.of("token", token.get()));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "invalid_credentials"));
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String auth) {
		String token = extractToken(auth);
		authService.logout(token);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/me")
	public ResponseEntity<?> me(@RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String auth) {
		String token = extractToken(auth);
		Optional<User> u = authService.getUserByToken(token);
		if (u.isEmpty()) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		return ResponseEntity.ok(u.get());
	}

	private String extractToken(String authHeader) {
		if (authHeader == null) return null;
		if (authHeader.toLowerCase().startsWith("bearer ")) {
			return authHeader.substring(7).trim();
		}
		return authHeader.trim();
	}
}
