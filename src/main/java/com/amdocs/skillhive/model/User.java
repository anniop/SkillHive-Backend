package com.amdocs.skillhive.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.Instant;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "full_name", nullable = false, length = 100)
    @NotBlank
    @Size(max = 100)
    @Pattern(regexp = "^[A-Za-z0-9 .,'-]+$", message = "fullName contains invalid characters")
    private String fullName;

    @Column(nullable = false, unique = true, length = 100)
    @NotBlank
    @Size(max = 100)
    @Email(message = "must be a valid email address")
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    @NotBlank
    @Size(min = 8, max = 255)
    private String passwordHash;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(length = 100)
    private String location;

    @Column(name = "date_joined", nullable = false, updatable = false)
    private Instant dateJoined = Instant.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.USER;

    // Optional: User skills relation
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserSkill> skills;

    public User() {}

    public User(Integer userId, String fullName, String email, String passwordHash,
                String bio, String location, Instant dateJoined, Role role, Set<UserSkill> skills) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.passwordHash = passwordHash;
        this.bio = bio;
        this.location = location;
        this.dateJoined = dateJoined != null ? dateJoined : Instant.now();
        this.role = role != null ? role : Role.USER;
        this.skills = skills;
    }

    // Getters and setters
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Instant getDateJoined() { return dateJoined; }
    public void setDateJoined(Instant dateJoined) { this.dateJoined = dateJoined; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public Set<UserSkill> getSkills() { return skills; }
    public void setSkills(Set<UserSkill> skills) { this.skills = skills; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        return Objects.equals(userId, other.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", location='" + location + '\'' +
                ", dateJoined=" + dateJoined +
                ", role=" + role +
                '}';
    }
}
