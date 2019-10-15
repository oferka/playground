package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class Customer {

    private long id;
    private String firstName;
    private String lastName;
}
