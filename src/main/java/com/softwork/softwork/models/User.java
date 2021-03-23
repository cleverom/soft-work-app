package com.softwork.softwork.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
    @ManyToMany
    private Set<RoleCategory> roleCategory;
    @OneToOne
    private LevelCategory levelCategory;
    @OneToMany
    private List<WorkHistory> workHistories;
    @OneToMany
    private List<Project> projects;
    @ManyToMany
    private Set<SkillCategory> skillCategories;
    @OneToOne
    private UserInfo userInfo;

    public User(String firstname, String lastname, String username, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User() {
    }

}
