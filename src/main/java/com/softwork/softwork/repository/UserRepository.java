package com.softwork.softwork.repository;

import com.softwork.softwork.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    List<User> findAllByRoles(Role role);
    List<User> findAllByRoleCategory(RoleCategory roleCategory);
    List<User> findAllByLevelCategory(LevelCategory levelCategory);
    Optional<User> findByEmail(String email);
}
