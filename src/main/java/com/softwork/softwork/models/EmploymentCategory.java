package com.softwork.softwork.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EmploymentCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private EmploymentEnum name;
//    @ManyToMany
//    private List<Transaction> transaction;
}
