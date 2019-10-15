package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
public class UserNotBlank {

    @NotBlank(message = "Name must not be blank")
    private final String name;
}
