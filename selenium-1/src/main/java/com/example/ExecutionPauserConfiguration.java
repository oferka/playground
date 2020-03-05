package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:execution-pause.properties")
@ConfigurationProperties(prefix = "execution.pause")
@Data
public class ExecutionPauserConfiguration {

    private boolean enabled;

    private long defaultDelayInMillis;
}
