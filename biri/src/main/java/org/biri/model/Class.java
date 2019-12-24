package org.biri.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class Class {

    @Id
    private String id;

    private String name;

    private String description;

    private Teacher teacher;

    private List<Course> courses;
}
