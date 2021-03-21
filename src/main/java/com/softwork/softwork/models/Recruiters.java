package com.softwork.softwork.models;

import lombok.Data;

import javax.persistence.Entity;

@Data
@Entity
public class Recruiters extends BaseModels {
    private String companyName;
    private String contactName; //person registering on behalf of the company
    //private String employeeNumber;
}
