package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:insights-page-opener.properties")
@ConfigurationProperties(prefix = "insights.page.open")
@Data
public class PageOpenerConfiguration {

    private int titleChangeTimeoutInSeconds;
}
