package com.softwork.softwork.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Applicant extends BaseModels{
    private String firstName;
    private String lastName;
    private String email;
    private String title;
    private String githubLink;
    private String behanceLink;
    private EmploymentEnum employmentEnum;

    @ManyToMany
    private Set<RoleCategory> roleCategory;
    @OneToMany
    private List<WorkHistory> workHistories;
    @OneToMany
    private List<Project> projects;
    @ManyToMany
    private List<SkillCategory> skillCategories;
}
