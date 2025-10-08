package com.amdocs.skillhive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/users")
public class UserController {
	// simple in-memory store: UUID -> arbitrary JSON object
	private final Map<UUID, Map<String, Object>> store = new ConcurrentHashMap<>();

	@GetMapping
	public Collection<Map<String, Object>> list() {
		return store.values();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Map<String, Object>> get(@PathVariable UUID id) {
		Map<String, Object> v = store.get(id);
		if (v == null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(v);
	}

	@PostMapping
	public ResponseEntity<Map<String, Object>> create(@RequestBody Map<String, Object> payload) {
		UUID id = UUID.randomUUID();
		Map<String, Object> entry = new HashMap<>(payload != null ? payload : Collections.emptyMap());
		entry.put("id", id.toString());
		store.put(id, entry);
		return ResponseEntity.status(HttpStatus.CREATED).body(entry);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> update(@PathVariable UUID id, @RequestBody Map<String, Object> payload) {
		Map<String, Object> existing = store.get(id);
		if (existing == null) return ResponseEntity.notFound().build();
		if (payload != null) {
			payload.forEach((k, v) -> {
				if (!"id".equals(k)) existing.put(k, v);
			});
		}
		return ResponseEntity.ok(existing);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable UUID id) {
		store.remove(id);
		return ResponseEntity.noContent().build();
	}
}
