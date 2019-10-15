package com.example;

import com.github.javafaker.Faker;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import java.util.Set;

import static java.util.Arrays.asList;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserTests {

    private Validator validator;
    private Faker faker;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
        faker = new Faker();
    }

    @Test
    public void shouldCreateUser() {
        User user = new User();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(50);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldValidateUserNameIsNotNull() {
        User user = new User();
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(50);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"name cannot be null");
        }
    }

    @Test
    public void shouldValidateWorkingIsTrue() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(false);
        user.setAboutMe("Its all about me!");
        user.setAge(50);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"working must be true");
        }
    }

    @Test
    public void shouldValidateAboutMeLength() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("too short");
        user.setAge(50);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"about Me must be between 10 and 200 characters");
        }
    }

    @Test
    public void shouldValidateMinimumAge() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(15);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"age should not be less than 18");
        }
    }

    @Test
    public void shouldValidateMaximumAge() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(151);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"age should not be greater than 150");
        }
    }

    @Test
    public void shouldValidateEmail() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(43);
        user.setPreferences(asList("dogs", "cats"));
        user.setEmail("not a valid email");
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"email should be valid");
        }
    }

    @Test
    public void shouldValidatePreferencesNotEmpty() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(43);
        user.setPreferences(asList());
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"preferences must not be empty");
        }
    }

    @Test
    public void shouldValidatePreferencesElementsNotBlank() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(43);
        user.setPreferences(asList("dogs", "cats", "  "));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"preference must not be blank");
        }
    }

    @Test
    public void shouldValidateDateOfBirthInPast() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(43);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().plusYears(1));
        user.setFavoriteColor(faker.color().name());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"dateOfBirth must be in the past");
        }
    }

    @Test
    public void shouldValidateFavoriteColor() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(43);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(EMPTY);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"favorite color must not be blank");
        }
    }

    @Test
    public void shouldValidateRatingMinimum() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(43);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        user.setRating(-1);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"rating must be greater or equal to 0");
        }
    }

    @Test
    public void shouldValidateRatingMaximum() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(43);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        user.setRating(6);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"rating must be less or equal to 5");
        }
    }

    @Test
    public void shouldValidateIdOrder() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(43);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        user.setRating(4);
        user.setIdOrder("1234");
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"idOrder must consist of exactly 6 digits");
        }
    }

    @Test
    public void shouldValidateNextMeeting() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(43);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        user.setRating(4);
        user.setIdOrder("123456");
        user.setNextMeeting(LocalDate.now());
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"nextMeeting must be in the future");
        }
    }

    @Test
    public void shouldValidateNumberOfFriends() {
        User user = new User();
        Faker faker = new Faker();
        user.setName(faker.name().fullName());
        user.setWorking(true);
        user.setAboutMe("Its all about me!");
        user.setAge(43);
        user.setPreferences(asList("dogs", "cats"));
        user.setDateOfBirth(LocalDate.now().minusYears(43));
        user.setFavoriteColor(faker.color().name());
        user.setRating(4);
        user.setIdOrder("123456");
        user.setNextMeeting(LocalDate.now().plusWeeks(1));
        user.setNumberOfFriends(-1);
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        assertEquals(1, violations.size());
        for(ConstraintViolation<User> constraintViolation : violations) {
            assertEquals(constraintViolation.getMessage(),"numberOfFriends must be zero or more");
        }
    }
}
