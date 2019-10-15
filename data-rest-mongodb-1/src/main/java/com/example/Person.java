package com.example;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
class Person {

    @Id private String id;

    private String firstName;
    private String lastName;
}
