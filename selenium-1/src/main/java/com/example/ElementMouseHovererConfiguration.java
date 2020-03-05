package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:ui.properties")
@ConfigurationProperties(prefix = "element.mouse.hover")
@Data
public class ElementMouseHovererConfiguration {

    private boolean enabled;
}
