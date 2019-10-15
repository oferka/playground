package com.example;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Data
public class User {

    @NotNull(message = "name cannot be null")
    private String name;

    @AssertTrue(message = "working must be true")
    private boolean working;

    @Size(min = 10, max = 200, message = "about Me must be between 10 and 200 characters")
    private String aboutMe;

    @Min(value = 18, message = "age should not be less than 18")
    @Max(value = 150, message = "age should not be greater than 150")
    private int age;

    @Email(message = "email should be valid")
    private String email;

    @NotEmpty(message = "preferences must not be empty")
    private List<@NotBlank(message = "preference must not be blank") String> preferences;

    @Past(message = "dateOfBirth must be in the past")
    private LocalDate dateOfBirth;

    private String favoriteColor;

    @DecimalMin(value = "0.0", message = "rating must be greater or equal to 0")
    @DecimalMax(value = "5.0", message = "rating must be less or equal to 5")
    private double rating;

    @Pattern(regexp="\\d{6}", message = "idOrder must consist of exactly 6 digits")
    private String idOrder;

    @Future(message = "nextMeeting must be in the future")
    private LocalDate nextMeeting;

    @PositiveOrZero(message = "numberOfFriends must be zero or more")
    private int numberOfFriends;

    public Optional<@NotBlank(message = "favorite color must not be blank") String> getFavoriteColor() {
        return Optional.of(favoriteColor);
    }
}
