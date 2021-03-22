package com.softwork.softwork.repository;

import com.softwork.softwork.models.RoleCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleCategoryRepository extends JpaRepository<RoleCategory, Long> {

    Boolean existsByName(String name);
}
