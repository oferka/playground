package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class UserNotNull {

    @NotNull(message = "Name must not be null")
    private final String name;
}
