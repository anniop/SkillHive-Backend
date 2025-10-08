package com.amdocs.skillhive.service;

import com.amdocs.skillhive.model.RequestStatus;
import com.amdocs.skillhive.model.SkillExchangeRequest;
import com.amdocs.skillhive.repository.SkillExchangeRequestRepository;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SkillExchangeRequestServiceImpl implements SkillExchangeRequestService {

	private final SkillExchangeRequestRepository repository;

	public SkillExchangeRequestServiceImpl(SkillExchangeRequestRepository repository) {
		this.repository = repository;
	}

	@Override
	public SkillExchangeRequest createRequest(SkillExchangeRequest request) {
		if (request == null) throw new IllegalArgumentException("request must not be null");
		if (request.getStatus() == null) request.setStatus(RequestStatus.PENDING);
		if (request.getRequestedAt() == null) request.setRequestedAt(java.time.Instant.now());
		return repository.save(request);
	}

	@Override
	public SkillExchangeRequest updateRequest(Integer requestId, SkillExchangeRequest request) {
		if (!repository.existsById(requestId))
			throw new EntityNotFoundException("Request not found: " + requestId);
		request.setRequestId(requestId);
		return repository.save(request);
	}

	@Override
	public SkillExchangeRequest getRequestById(Integer requestId) {
		return repository.findById(requestId)
				.orElseThrow(() -> new EntityNotFoundException("Request not found: " + requestId));
	}

	@Override
	public List<SkillExchangeRequest> getRequestsBySenderId(Integer senderId) {
		return repository.findAll().stream()
				.filter(r -> r.getSenderId() != null && Objects.equals(r.getSenderId(), senderId))
				.collect(Collectors.toList());
	}

	@Override
	public List<SkillExchangeRequest> getRequestsByReceiverId(Integer receiverId) {
		return repository.findAll().stream()
				.filter(r -> r.getReceiverId() != null && Objects.equals(r.getReceiverId(), receiverId))
				.collect(Collectors.toList());
	}

	@Override
	public List<SkillExchangeRequest> getRequestsByStatus(RequestStatus status) {
		if (status == null) return List.of();
		return repository.findAll().stream()
				.filter(r -> r.getStatus() == status)
				.collect(Collectors.toList());
	}

	@Override
	public void deleteRequest(Integer requestId) {
		if (!repository.existsById(requestId))
			throw new EntityNotFoundException("Request not found: " + requestId);
		repository.deleteById(requestId);
	}

	@Override
	public SkillExchangeRequest updateRequestStatus(Integer requestId, RequestStatus status) {
		SkillExchangeRequest r = repository.findById(requestId)
				.orElseThrow(() -> new EntityNotFoundException("Request not found: " + requestId));
		r.setStatus(status);
		return repository.save(r);
	}
}
