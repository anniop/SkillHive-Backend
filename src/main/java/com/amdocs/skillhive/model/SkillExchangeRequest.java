package com.amdocs.skillhive.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "skill_exchange_requests")
public class SkillExchangeRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;

    // store IDs instead of entity relations to avoid missing-type errors
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

    public SkillExchangeRequest(Integer requestId,
                                Integer senderId,
                                Integer receiverId,
                                Integer skillId,
                                String message,
                                String availability,
                                String sessionDuration,
                                RequestStatus status,
                                Instant requestedAt) {
        this.requestId = requestId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.skillId = skillId;
        this.message = message;
        this.availability = availability;
        this.sessionDuration = sessionDuration;
        this.status = status == null ? RequestStatus.PENDING : status;
        this.requestedAt = requestedAt == null ? Instant.now() : requestedAt;
    }

    public Integer getRequestId() {
        return requestId;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public Integer getSkillId() {
        return skillId;
    }

    public void setSkillId(Integer skillId) {
        this.skillId = skillId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getSessionDuration() {
        return sessionDuration;
    }

    public void setSessionDuration(String sessionDuration) {
        this.sessionDuration = sessionDuration;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public Instant getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Instant requestedAt) {
        this.requestedAt = requestedAt;
    }

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

    @Override
    public String toString() {
        return "SkillExchangeRequest{" +
                "requestId=" + requestId +
                ", senderId=" + senderId +
                ", receiverId=" + receiverId +
                ", skillId=" + skillId +
                ", message='" + (message != null ? (message.length() > 60 ? message.substring(0, 60) + "..." : message) : null) + '\'' +
                ", availability='" + availability + '\'' +
                ", sessionDuration='" + sessionDuration + '\'' +
                ", status=" + status +
                ", requestedAt=" + requestedAt +
                '}';
    }

    // simple enum included here to avoid external dependency
    public enum RequestStatus {
        PENDING,
        ACCEPTED,
        REJECTED,
        CANCELLED
    }
}