package org.biri.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Course {

    @Id
    private String id;

    private String name;

    private String description;

    private Teacher teacher;

    private Schedule schedule;
}
