package com.softwork.softwork.models;

import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

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
    @OneToOne
    private Role role;
    private EnumEmploymentType enumEmploymentType;
}
