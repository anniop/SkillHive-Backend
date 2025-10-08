package com.amdocs.skillhive.repository;

import com.amdocs.skillhive.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Add custom queries if needed
}
