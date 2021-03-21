package com.softwork.softwork.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
@Entity
public class EmploymentType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private EnumEmploymentType name;
//    @ManyToMany
//    private List<Transaction> transaction;
}
