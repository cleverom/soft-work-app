package com.softwork.softwork.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Data
@Entity
public class Applicant extends BaseModels{
    private String firstName;
    private String lastName;
    private String title;
    @OneToMany
    private List<WorkHistory> workHistories;
    private String githubLink;
    private String behanceLink;
    @OneToMany
    private List<Project> projects;
    @ManyToMany
    private List<Skill> skills;
    private Role role;
    private EnumEmploymentType enumEmploymentType;
}
