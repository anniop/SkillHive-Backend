package com.amdocs.skillhive.service;

import com.amdocs.skillhive.model.SkillExchangeRequest;
import com.amdocs.skillhive.model.RequestStatus;
import com.amdocs.skillhive.repository.SkillExchangeRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

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
        return requestRepository.findByReceiver_UserId(senderId);
    }

    @Override
    public List<SkillExchangeRequest> getRequestsByReceiverId(Integer receiverId) {
        return requestRepository.findByReceiver_UserId(receiverId);
    }

    @Override
    public List<SkillExchangeRequest> getRequestsByStatus(RequestStatus status) {
        return requestRepository.findByStatus(status);
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
        SkillExchangeRequest request = requestRepository.getReferenceById(requestId);
        request.setStatus(status);
        return requestRepository.save(request);
    }
}
