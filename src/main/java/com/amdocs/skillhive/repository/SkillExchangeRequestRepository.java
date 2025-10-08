package com.amdocs.skillhive.repository;

import com.amdocs.skillhive.model.RequestStatus;
import com.amdocs.skillhive.model.SkillExchangeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillExchangeRequestRepository extends JpaRepository<SkillExchangeRequest, Integer> {
    List<SkillExchangeRequest> findBySender_UserId(Integer senderId);
    List<SkillExchangeRequest> findByReceiver_UserId(Integer receiverId);
    List<SkillExchangeRequest> findByStatus(RequestStatus status);
}
