package com.softwork.softwork.repository;

import com.softwork.softwork.models.SkillCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillCategoryRepository extends JpaRepository<SkillCategory, Long> {
    Boolean existsByName(String name);
}
