package com.softwork.softwork.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String projectName;
    private String projectDescription;
    private String projectLink;
    private String projectVideoUrl;
}
