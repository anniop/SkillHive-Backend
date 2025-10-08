package com.amdocs.skillhive.controller;

import com.amdocs.skillhive.model.Skill;
import com.amdocs.skillhive.model.SkillExchangeRequest;
import com.amdocs.skillhive.model.RequestStatus;
import com.amdocs.skillhive.model.User;
import com.amdocs.skillhive.repository.SkillRepository;
import com.amdocs.skillhive.repository.UserRepository;
import com.amdocs.skillhive.service.SkillExchangeRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/requests")
public class SkillExchangeRequestController {

	@Autowired
	private SkillExchangeRequestService requestService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SkillRepository skillRepository;

	@PostMapping
	public ResponseEntity<?> create(@RequestBody Map<String, Object> body) {
		// expected fields: senderId, receiverId, skillId, message, availability, sessionDuration
		Integer senderId = toInteger(body.get("senderId"));
		Integer receiverId = toInteger(body.get("receiverId"));
		Integer skillId = toInteger(body.get("skillId"));
		if (senderId == null || receiverId == null || skillId == null) {
			return ResponseEntity.badRequest().body(Map.of("error", "senderId, receiverId and skillId are required"));
		}

		Optional<User> senderOpt = userRepository.findById(senderId);
		Optional<User> receiverOpt = userRepository.findById(receiverId);
		Optional<Skill> skillOpt = skillRepository.findById(skillId);

		if (senderOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "sender_not_found"));
		if (receiverOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "receiver_not_found"));
		if (skillOpt.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "skill_not_found"));

		String message = (String) body.getOrDefault("message", null);
		String availability = (String) body.getOrDefault("availability", null);
		String sessionDuration = (String) body.getOrDefault("sessionDuration", null);

		SkillExchangeRequest req = new SkillExchangeRequest(
				null,
				senderOpt.get(),
				receiverOpt.get(),
				skillOpt.get(),
				message,
				availability,
				sessionDuration,
				RequestStatus.PENDING,
				Instant.now()
		);
		SkillExchangeRequest created = requestService.createRequest(req);
		return ResponseEntity.status(HttpStatus.CREATED).body(created);
	}

	@GetMapping("/{id}")
	public ResponseEntity<SkillExchangeRequest> get(@PathVariable Integer id) {
		try {
			return ResponseEntity.ok(requestService.getRequestById(id));
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@GetMapping("/receiver/{receiverId}")
	public ResponseEntity<List<SkillExchangeRequest>> listForReceiver(@PathVariable Integer receiverId) {
		return ResponseEntity.ok(requestService.getRequestsByReceiverId(receiverId));
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<?> updateStatus(@PathVariable Integer id, @RequestBody Map<String, String> body) {
		String statusStr = body.get("status");
		if (statusStr == null) {
			return ResponseEntity.badRequest().body(Map.of("error", "missing_status"));
		}
		RequestStatus status;
		try {
			status = RequestStatus.valueOf(statusStr.toUpperCase());
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(Map.of("error", "invalid_status"));
		}
		try {
			SkillExchangeRequest updated = requestService.updateRequestStatus(id, status);
			return ResponseEntity.ok(updated);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// helper to handle numbers coming from JSON (Integer/Long/Double/String)
	private Integer toInteger(Object o) {
		if (o == null) return null;
		if (o instanceof Number) return ((Number) o).intValue();
		if (o instanceof String) {
			try { return Integer.parseInt((String) o); } catch (NumberFormatException ignored) {}
		}
		return null;
	}
}
