package com.amdocs.skillhive.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_skills",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "skill_id", "skill_type"}))
public class UserSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userSkillId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "skill_id", nullable = false)
    private Skill skill;

    @Enumerated(EnumType.STRING)
    @Column(name = "skill_type", nullable = false)
    private SkillType skillType;

    @Enumerated(EnumType.STRING)
    @Column(name = "proficiency_level")
    private ProficiencyLevel proficiencyLevel;

    public UserSkill() { }

    public UserSkill(Integer userSkillId,
                     User user,
                     Skill skill,
                     SkillType skillType,
                     ProficiencyLevel proficiencyLevel) {
        this.userSkillId = userSkillId;
        this.user = user;
        this.skill = skill;
        this.skillType = skillType;
        this.proficiencyLevel = proficiencyLevel;
    }

    public Integer getUserSkillId() {
        return userSkillId;
    }

    public void setUserSkillId(Integer userSkillId) {
        this.userSkillId = userSkillId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public SkillType getSkillType() {
        return skillType;
    }

    public void setSkillType(SkillType skillType) {
        this.skillType = skillType;
    }

    public ProficiencyLevel getProficiencyLevel() {
        return proficiencyLevel;
    }

    public void setProficiencyLevel(ProficiencyLevel proficiencyLevel) {
        this.proficiencyLevel = proficiencyLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSkill)) return false;
        UserSkill other = (UserSkill) o;
        return Objects.equals(userSkillId, other.userSkillId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userSkillId);
    }

    @Override
    public String toString() {
        Integer userId = user != null ? user.getUserId() : null;
        Integer skillId = skill != null ? skill.getSkillId() : null;
        return "UserSkill{" +
                "userSkillId=" + userSkillId +
                ", userId=" + userId +
                ", skillId=" + skillId +
                ", skillType=" + skillType +
                ", proficiencyLevel=" + proficiencyLevel +
                '}';
    }
}