package com.softwork.softwork.models;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class WorkHistory {
    private String title;
    private String companyName;
    private String employmentType;
    private String location;
    private LocalDateTime startDate;
    private LocalDateTime finishDate;
    private String description;
}
