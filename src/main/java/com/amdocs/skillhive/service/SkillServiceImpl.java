package com.amdocs.skillhive.service;

import com.amdocs.skillhive.model.Skill;
import com.amdocs.skillhive.repository.SkillRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public Skill createSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public Skill updateSkill(Integer skillId, Skill skill) {
        if (!skillRepository.existsById(skillId)) {
            throw new EntityNotFoundException("Skill not found with id: " + skillId);
        }
        skill.setSkillId(skillId);
        return skillRepository.save(skill);
    }

    @Override
    public Skill getSkillById(Integer skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(() -> new EntityNotFoundException("Skill not found with id: " + skillId));
    }

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public void deleteSkill(Integer skillId) {
        if (!skillRepository.existsById(skillId)) {
            throw new EntityNotFoundException("Skill not found with id: " + skillId);
        }
        skillRepository.deleteById(skillId);
    }
}
