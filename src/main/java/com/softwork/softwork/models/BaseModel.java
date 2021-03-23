package com.softwork.softwork.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phoneNumber;
    private String address;
    //private List<> country;
    private String bio;
    private String profilePicture;
    private String banner;
    private String linkedIn;
    private String website;


}
