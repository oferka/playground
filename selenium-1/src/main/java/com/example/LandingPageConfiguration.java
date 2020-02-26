package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:insights-landing-page.properties")
@ConfigurationProperties(prefix = "landing.page")
@Data
public class LandingPageConfiguration {

    private String address;

    private String titleContains;

    private int timeOutInSeconds;
}
