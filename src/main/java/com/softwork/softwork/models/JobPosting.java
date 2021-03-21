package com.softwork.softwork.models;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;

public class JobPosting {
    private String title;
    private String description;
    private String requirements;
    private List<Skill> skills;
    private Long salary;
    private LocalDateTime deadline;
    private EnumEmploymentType enumEmploymentType;
    @OneToMany
    private List<Category> categories;
    private ELevel elevel;
}
