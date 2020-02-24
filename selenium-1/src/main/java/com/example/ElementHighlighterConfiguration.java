package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:ui.properties")
@ConfigurationProperties(prefix = "element.highlighter")
@Data
public class ElementHighlighterConfiguration {

    private boolean enabled;

    private String color;

    private String background;

    private String border;
}
