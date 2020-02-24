package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChromeBrowserProvider implements BrowserProvider {

    @Autowired
    private ChromeBrowserConfiguration chromeBrowserConfiguration;

    @Override
    public WebDriver openBrowser() {
        log.info("Open browser started");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        if(chromeBrowserConfiguration.isMaximize()) {
            driver.manage().window().maximize();
        }
        log.info("Open browser completed");
        return driver;
    }
}
