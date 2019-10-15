package com.example.model;

import lombok.Data;

import java.util.Collection;
import java.util.Map;

@Data
public class Employee {

    private long id;
    private String firstName;
    private String lastName;
    private Department department;
    private Collection<Employee> coworkers;
    private Map<YearQuarter, Grade> quarterGrades;
}
