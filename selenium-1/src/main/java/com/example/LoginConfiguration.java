package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:login.properties")
@ConfigurationProperties(prefix = "login")
@Data
public class LoginConfiguration {

    private String username;

    private String password;
}
