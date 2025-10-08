package com.amdocs.skillhive.service;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class CourseService {
	private final Map<UUID, Map<String, Object>> store = new ConcurrentHashMap<>();

	public Collection<Map<String, Object>> findAll() {
		return store.values();
	}

	public Map<String, Object> findById(UUID id) {
		return store.get(id);
	}

	public Map<String, Object> create(Map<String, Object> payload) {
		UUID id = UUID.randomUUID();
		Map<String, Object> entry = new HashMap<>(payload != null ? payload : Collections.emptyMap());
		entry.put("id", id.toString());
		store.put(id, entry);
		return entry;
	}

	public Map<String, Object> update(UUID id, Map<String, Object> payload) {
		Map<String, Object> existing = store.get(id);
		if (existing == null) return null;
		if (payload != null) {
			payload.forEach((k, v) -> {
				if (!"id".equals(k)) existing.put(k, v);
			});
		}
		return existing;
	}

	public void delete(UUID id) {
		store.remove(id);
	}
}
