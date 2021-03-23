package com.softwork.softwork.repository;


import com.softwork.softwork.models.RoleEnum;
import com.softwork.softwork.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRoleEnum (RoleEnum name);

}
