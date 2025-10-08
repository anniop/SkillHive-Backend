package com.amdocs.skillhive.repository;

import com.amdocs.skillhive.model.SkillExchangeRequest;
import com.amdocs.skillhive.model.RequestStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SkillExchangeRequestRepository extends JpaRepository<SkillExchangeRequest, Integer> {
	// keep simple; add custom queries if needed later

    List<SkillExchangeRequest> findBySender_UserId(Integer senderId);
    List<SkillExchangeRequest> findByReceiver_UserId(Integer receiverId);
    List<SkillExchangeRequest> findByStatus(RequestStatus status);
}
