package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultPageHeaderRetriever implements PageHeaderRetriever {

    @Override
    public WebElement retrieve(WebDriver driver, By locator) {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public boolean isDisplayed(WebDriver driver, By locator) {
        log.info("Check if page header is currently displayed started");
        boolean result;
        try {
            log.debug("Using locator '{}'", locator.toString());
            WebElement element = driver.findElement(locator);
            log.debug("Found page header element with text '{}'", element.getText());
            result = true;
        }
        catch (Exception e) {
            log.debug("Failed to find page header element. Error message was '{}'", e.getMessage());
            result = false;
        }
        log.info("Check if page header is currently displayed completed. result is: {}", result);
        return result;
    }
}
