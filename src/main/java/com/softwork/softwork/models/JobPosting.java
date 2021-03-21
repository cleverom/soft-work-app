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
    private List<Skill> skills;
    private Long salary;
    private LocalDateTime deadline;
    private EnumEmploymentType enumEmploymentType;
    @OneToMany
    private List<Category> categories;
    @OneToMany
    private List<ELevel> elevel;
    private Integer position; //number of positions to be filled
}
