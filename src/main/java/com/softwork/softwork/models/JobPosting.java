package com.softwork.softwork.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class JobPosting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String requirements;
    @OneToMany
    private List<SkillCategory> skillCategories;
    private Long salary;
    private LocalDateTime deadline;
    private EmploymentEnum employmentEnum;
    @ManyToOne
    private RoleCategory category;
    @OneToMany
    private List<LevelCategory> elevel;
    private Integer position; //number of positions to be filled
}
