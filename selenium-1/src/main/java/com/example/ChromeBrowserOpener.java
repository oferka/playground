package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChromeBrowserOpener implements BrowserOpener {

    @Autowired
    private ChromeBrowserConfiguration chromeBrowserConfiguration;

    @Override
    public WebDriver open() {
        log.info("Open chrome browser started");
        WebDriver driver = createDriver();
        if(chromeBrowserConfiguration.isMaximize()) {
            maximizeWindow(driver);
        }
        log.info("Open chrome browser completed");
        return driver;
    }

    private WebDriver createDriver() {
        log.info("Chrome driver creation started");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        log.info("Chrome driver creation completed");
        return driver;
    }

    private void maximizeWindow(WebDriver driver) {
        log.info("Browser window maximize started");
        driver.manage().window().maximize();
        log.info("Browser window maximize completed");
    }
}
