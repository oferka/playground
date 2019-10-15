package com.example;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.apache.commons.lang3.StringUtils.EMPTY;

@SpringBootApplication
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        UserNotBlank userNotBlank = new UserNotBlank(" ");
        validator.validate(userNotBlank).stream().forEach(violation -> log.info(violation.getMessage()));

        UserNotEmpty userNotEmpty = new UserNotEmpty(EMPTY);
        validator.validate(userNotEmpty).stream().forEach(violation -> log.info(violation.getMessage()));

        UserNotNull userNotNull = new UserNotNull(null);
        validator.validate(userNotNull).stream().forEach(violation -> log.info(violation.getMessage()));
    }
}
