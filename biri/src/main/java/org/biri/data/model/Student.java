package org.biri.data.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=true)
public class Student extends Person {

    private List<Class> classes;

    private List<Course> courses;

}
