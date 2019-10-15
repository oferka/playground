package com.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Data
@NoArgsConstructor
public class Line implements Serializable {

    private String name;
    private LocalDate dob;
    private Long age;

    public Line(String name, LocalDate dob) {
        this.name = name;
        this.dob = dob;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(this.name);
        sb.append(",");
        sb.append(this.dob.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        if (this.age != null) {
            sb.append(",");
            sb.append(this.age);
        }
        sb.append("]");
        return sb.toString();
    }
}
