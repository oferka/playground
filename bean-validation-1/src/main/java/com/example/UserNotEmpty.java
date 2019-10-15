package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class UserNotEmpty {

    @NotEmpty(message = "Name must not be empty")
    private final String name;
}
