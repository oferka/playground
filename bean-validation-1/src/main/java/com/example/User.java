package com.example;

import lombok.Getter;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class User {

    @NotNull(message = "Name cannot be null")
    @Getter
    private String name;

    @AssertTrue
    @Getter
    private boolean working;

    @Size(min = 10, max = 200, message = "Number of characters should be between 10 and 200 inclusive")
    @Getter
    private String aboutMe;

    @Min(value = 18, message = "Age should not be less than 18")
    @Max(value = 150, message = "Age should not be more than 150")
    @Getter
    private int age;

    @Email(message = "Email should be valid")
    @Getter
    private String email;

    @Getter
    List<@NotBlank String> preferences;

    private LocalDate dateOfBirth;

    public Optional<@Past LocalDate> getDateOfBirth() {
        return Optional.ofNullable(dateOfBirth);
    }
}
