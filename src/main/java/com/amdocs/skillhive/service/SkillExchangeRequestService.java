package com.amdocs.skillhive.service;

import com.amdocs.skillhive.model.SkillExchangeRequest;
import com.amdocs.skillhive.model.RequestStatus;
import java.util.List;

public interface SkillExchangeRequestService {
    SkillExchangeRequest createRequest(SkillExchangeRequest request);
    SkillExchangeRequest updateRequest(Integer requestId, SkillExchangeRequest request);
    SkillExchangeRequest getRequestById(Integer requestId);
    List<SkillExchangeRequest> getRequestsBySenderId(Integer senderId);
    List<SkillExchangeRequest> getRequestsByReceiverId(Integer receiverId);
    List<SkillExchangeRequest> getRequestsByStatus(RequestStatus status);
    void deleteRequest(Integer requestId);
    SkillExchangeRequest updateRequestStatus(Integer requestId, RequestStatus status);
}
