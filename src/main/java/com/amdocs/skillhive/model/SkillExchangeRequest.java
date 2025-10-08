package com.amdocs.skillhive.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "skill_exchange_requests")
public class SkillExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer requestId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Column(length = 255)
    private String message;

    @Column(length = 100)
    private String availability;

    @Column(name = "session_duration", length = 50)
    private String sessionDuration;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status = RequestStatus.PENDING;

    @Column(name = "requested_at", nullable = false, updatable = false)
    private Instant requestedAt = Instant.now();

    public SkillExchangeRequest() {}

    public SkillExchangeRequest(Integer requestId,
                                User sender,
                                User receiver,
                                Skill skill,
                                String message,
                                String availability,
                                String sessionDuration,
                                RequestStatus status,
                                Instant requestedAt) {
        this.requestId = requestId;
        this.sender = sender;
        this.receiver = receiver;
        this.skill = skill;
        this.message = message;
        this.availability = availability;
        this.sessionDuration = sessionDuration;
        this.status = status == null ? RequestStatus.PENDING : status;
        this.requestedAt = requestedAt == null ? Instant.now() : requestedAt;
    }

    // getters / setters
    public Integer getRequestId() { return requestId; }
    public void setRequestId(Integer requestId) { this.requestId = requestId; }

    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }

    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }

    public Skill getSkill() { return skill; }
    public void setSkill(Skill skill) { this.skill = skill; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }

    public String getSessionDuration() { return sessionDuration; }
    public void setSessionDuration(String sessionDuration) { this.sessionDuration = sessionDuration; }

    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }

    public RequestStatus getRequestStatus() {
		return this.status;
	}

	public void setRequestStatus(RequestStatus requestStatus) {
		this.status = requestStatus;
	}

    public Instant getRequestedAt() { return requestedAt; }
    public void setRequestedAt(Instant requestedAt) { this.requestedAt = requestedAt; }

    // convenience id getters used by services/controllers without initializing proxies
    public Integer getSenderId() { return sender != null ? sender.getUserId() : null; }
    public Integer getReceiverId() { return receiver != null ? receiver.getUserId() : null; }
    public Integer getSkillId() { return skill != null ? skill.getSkillId() : null; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillExchangeRequest)) return false;
        SkillExchangeRequest that = (SkillExchangeRequest) o;
        return Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId);
    }
}

