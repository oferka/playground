package com.example;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:browser-chrome.properties")
@ConfigurationProperties(prefix = "browser.chrome")
@Data
public class ChromeBrowserConfiguration {

    private boolean maximize;
}
