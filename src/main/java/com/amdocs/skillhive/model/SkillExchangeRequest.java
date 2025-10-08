package com.amdocs.skillhive.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "skill_exchange_requests")
public class SkillExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    @Column(name = "sender_id", nullable = false)
    private Integer senderId;

    @Column(name = "receiver_id", nullable = false)
    private Integer receiverId;

    @Column(name = "skill_id", nullable = false)
    private Integer skillId;

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

    public SkillExchangeRequest() { }

    // Getters and Setters
    public Integer getRequestId() { return requestId; }
    public void setRequestId(Integer requestId) { this.requestId = requestId; }
    public Integer getSenderId() { return senderId; }
    public void setSenderId(Integer senderId) { this.senderId = senderId; }
    public Integer getReceiverId() { return receiverId; }
    public void setReceiverId(Integer receiverId) { this.receiverId = receiverId; }
    public Integer getSkillId() { return skillId; }
    public void setSkillId(Integer skillId) { this.skillId = skillId; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public String getAvailability() { return availability; }
    public void setAvailability(String availability) { this.availability = availability; }
    public String getSessionDuration() { return sessionDuration; }
    public void setSessionDuration(String sessionDuration) { this.sessionDuration = sessionDuration; }
    public RequestStatus getStatus() { return status; }
    public void setStatus(RequestStatus status) { this.status = status; }
    public Instant getRequestedAt() { return requestedAt; }
    public void setRequestedAt(Instant requestedAt) { this.requestedAt = requestedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SkillExchangeRequest)) return false;
        SkillExchangeRequest that = (SkillExchangeRequest) o;
        return Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() { return Objects.hash(requestId); }

    @Override
    public String toString() {
        return "SkillExchangeRequest{" +
                "requestId=" + requestId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", skillId=" + skillId +
                ", message='" + message + '\'' +
                ", availability='" + availability + '\'' +
                ", sessionDuration='" + sessionDuration + '\'' +
                ", status=" + status +
                ", requestedAt=" + requestedAt +
                '}';
    }

    public enum RequestStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        COMPLETED
    }
}
