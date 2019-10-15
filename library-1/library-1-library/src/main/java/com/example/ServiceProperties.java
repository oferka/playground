package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("service")
@Data
public class ServiceProperties {

    private String message;
}
