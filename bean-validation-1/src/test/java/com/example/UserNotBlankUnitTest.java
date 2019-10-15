package com.example;

import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.assertj.core.api.Assertions.assertThat;

public class UserNotBlankUnitTest {

    private static Validator validator;

    @BeforeClass
    public static void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void whenNotBlankName_thenNoConstraintViolations() {
        UserNotBlank user = new UserNotBlank("John");

        Set<ConstraintViolation<UserNotBlank>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(0);
    }

    @Test
    public void whenBlankName_thenOneConstraintViolation() {
        UserNotBlank user = new UserNotBlank(" ");

        Set<ConstraintViolation<UserNotBlank>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenEmptyName_thenOneConstraintViolation() {
        UserNotBlank user = new UserNotBlank(EMPTY);

        Set<ConstraintViolation<UserNotBlank>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenNullName_thenOneConstraintViolation() {
        UserNotBlank user = new UserNotBlank(null);

        Set<ConstraintViolation<UserNotBlank>> violations = validator.validate(user);

        assertThat(violations.size()).isEqualTo(1);
    }

    @Test
    public void whenToString_thenCorrect() {
        UserNotBlank user = new UserNotBlank("John");

        assertThat(user.toString()).isEqualTo("UserNotBlank(name=John)");
    }
}
