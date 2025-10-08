package com.amdocs.skillhive.service;

import com.amdocs.skillhive.model.SkillExchangeRequest;
import com.amdocs.skillhive.model.RequestStatus;
import com.amdocs.skillhive.repository.SkillExchangeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SkillExchangeRequestServiceImpl implements SkillExchangeRequestService {

    @Autowired
    private SkillExchangeRequestRepository requestRepository;

    @Override
    public SkillExchangeRequest createRequest(SkillExchangeRequest request) {
        return requestRepository.save(request);
    }

    @Override
    public SkillExchangeRequest updateRequest(Integer requestId, SkillExchangeRequest request) {
        if (!requestRepository.existsById(requestId)) {
            throw new EntityNotFoundException("Request not found with id: " + requestId);
        }
        request.setRequestId(requestId);
        return requestRepository.save(request);
    }

    @Override
    public SkillExchangeRequest getRequestById(Integer requestId) {
        return requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Request not found with id: " + requestId));
    }

    @Override
    public List<SkillExchangeRequest> getRequestsBySenderId(Integer senderId) {
        // use senderId field on the entity
        return requestRepository.findAll().stream()
                .filter(r -> r.getSenderId() != null && Objects.equals(r.getSenderId(), senderId))
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillExchangeRequest> getRequestsByReceiverId(Integer receiverId) {
        // use receiverId field on the entity
        return requestRepository.findAll().stream()
                .filter(r -> r.getReceiverId() != null && Objects.equals(r.getReceiverId(), receiverId))
                .collect(Collectors.toList());
    }

    @Override
    public List<SkillExchangeRequest> getRequestsByStatus(RequestStatus status) {
        // filter by enum status
        return requestRepository.findAll().stream()
                .filter(r -> r.getStatus() != null && r.getStatus().name().equals(status.name()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRequest(Integer requestId) {
        if (!requestRepository.existsById(requestId)) {
            throw new EntityNotFoundException("Request not found with id: " + requestId);
        }
        requestRepository.deleteById(requestId);
    }

    @Override
    public SkillExchangeRequest updateRequestStatus(Integer requestId, RequestStatus status) {
        SkillExchangeRequest request = requestRepository.findById(requestId)
                .orElseThrow(() -> new EntityNotFoundException("Request not found with id: " + requestId));
        // convert external RequestStatus to the enum declared inside SkillExchangeRequest
        request.setStatus(status == null ? null : SkillExchangeRequest.RequestStatus.valueOf(status.name()));
        return requestRepository.save(request);
    }
}
