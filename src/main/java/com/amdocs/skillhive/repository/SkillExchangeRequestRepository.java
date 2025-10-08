package com.amdocs.skillhive.repository;

import com.amdocs.skillhive.model.SkillExchangeRequest;
import com.amdocs.skillhive.model.SkillExchangeRequest.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SkillExchangeRequestRepository extends JpaRepository<SkillExchangeRequest, Integer> {
    List<SkillExchangeRequest> findBySenderId(Integer senderId);
    List<SkillExchangeRequest> findByReceiverId(Integer receiverId);
    List<SkillExchangeRequest> findByStatus(RequestStatus status);
}
