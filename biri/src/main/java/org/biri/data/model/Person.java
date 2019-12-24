package org.biri.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.*;
import java.net.URI;

@Data
public class Person {

    @Id
    private String id;

    @Min(value = 2, message = "firstName should not be shorter than 2 characters")
    @Max(value = 16, message = "firstName should not be longer than 16 characters")
    private String firstName;

    @Min(value = 2, message = "lastName should not be shorter than 2 characters")
    @Max(value = 16, message = "lastName should not be longer than 16 characters")
    private String lastName;

    @NotNull(message = "email should not be null")
    @Email(message = "email should be valid")
    private String email;

    @Pattern(regexp="\\d{10}", message = "phoneNumber must consist of exactly 10 digits")
    private String phoneNumber;

    private URI profilePhoto;
}
