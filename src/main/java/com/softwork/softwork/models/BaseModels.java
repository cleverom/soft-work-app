package com.softwork.softwork.models;

import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class BaseModels {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    //private List<> country;
    private String bio;
    private String profilePicture;
    private String banner;
    private String linkedIn;
    private String website;
}
