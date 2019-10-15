package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

@Configuration
@PropertySource("classpath:email.properties")
@ConfigurationProperties(prefix = "email")
@Data
public class EmailConfiguration {

    private String hostName;

    private int port;

    private String from;

    private List<String> defaultRecipients;

    private Map<String, String> additionalHeaders;

    private Credentials credentials;
}
