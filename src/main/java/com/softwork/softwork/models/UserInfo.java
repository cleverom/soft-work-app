package com.softwork.softwork.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Entity
public class UserInfo extends BaseModel{

    private String githubLink;
    private String behanceLink;
    private EmploymentEnum employmentEnum;


    private String companyName;
    private String contactName;
}
