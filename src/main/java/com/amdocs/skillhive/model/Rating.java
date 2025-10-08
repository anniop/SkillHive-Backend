package com.amdocs.skillhive.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "ratings")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ratingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false)
    private SkillExchangeRequest request;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rated_by", nullable = false)
    private User ratedBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rated_user", nullable = false)
    private User ratedUser;

    @Column(name = "rating_value")
    @Min(1)
    @Max(5)
    private Integer ratingValue;

    @Column(columnDefinition = "TEXT")
    private String feedback;

    @Column(name = "rated_at", nullable = false, updatable = false)
    private Instant ratedAt = Instant.now();

    public Rating() { }

    public Rating(Integer ratingId,
                  SkillExchangeRequest request,
                  User ratedBy,
                  User ratedUser,
                  Integer ratingValue,
                  String feedback,
                  Instant ratedAt) {
        this.ratingId = ratingId;
        this.request = request;
        this.ratedBy = ratedBy;
        this.ratedUser = ratedUser;
        this.ratingValue = ratingValue;
        this.feedback = feedback;
        this.ratedAt = ratedAt == null ? Instant.now() : ratedAt;
    }

    public Integer getRatingId() {
        return ratingId;
    }

    public void setRatingId(Integer ratingId) {
        this.ratingId = ratingId;
    }

    public SkillExchangeRequest getRequest() {
        return request;
    }

    public void setRequest(SkillExchangeRequest request) {
        this.request = request;
    }

    public User getRatedBy() {
        return ratedBy;
    }

    public void setRatedBy(User ratedBy) {
        this.ratedBy = ratedBy;
    }

    public User getRatedUser() {
        return ratedUser;
    }

    public void setRatedUser(User ratedUser) {
        this.ratedUser = ratedUser;
    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Instant getRatedAt() {
        return ratedAt;
    }

    public void setRatedAt(Instant ratedAt) {
        this.ratedAt = ratedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rating)) return false;
        Rating other = (Rating) o;
        return Objects.equals(ratingId, other.ratingId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ratingId);
    }

    @Override
    public String toString() {
        return "Rating{" +
                "ratingId=" + ratingId +
                ", ratingValue=" + ratingValue +
                ", feedback='" + (feedback != null ? (feedback.length() > 60 ? feedback.substring(0, 60) + "..." : feedback) : null) + '\'' +
                ", ratedAt=" + ratedAt +
                '}';
    }
}