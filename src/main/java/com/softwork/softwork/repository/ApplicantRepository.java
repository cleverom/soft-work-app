package com.softwork.softwork.repository;

import com.softwork.softwork.models.Applicant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicantRepository extends JpaRepository<Applicant, Long> {
    Applicant findByEmail(String email);
}
