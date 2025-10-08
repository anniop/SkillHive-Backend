package com.amdocs.skillhive.service;

import com.amdocs.skillhive.model.Skill;
import java.util.List;

public interface SkillService {
    Skill createSkill(Skill skill);
    Skill updateSkill(Integer skillId, Skill skill);
    Skill getSkillById(Integer skillId);
    List<Skill> getAllSkills();
    void deleteSkill(Integer skillId);
}
