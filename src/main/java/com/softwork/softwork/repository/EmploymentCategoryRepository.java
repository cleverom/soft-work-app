package com.softwork.softwork.repository;

import com.softwork.softwork.models.EmploymentCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmploymentCategoryRepository extends JpaRepository<EmploymentCategory, Long> {
}
