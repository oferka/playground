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
public class DefaultWidgetBodyRetriever implements WidgetBodyRetriever {

    @Override
    public WebElement retrieve(WebDriver driver, By locator) {
        return new WebDriverWait(driver, 120).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public boolean isDisplayed(WebDriver driver, By locator) {
        log.debug("Check if widget body is currently displayed started");
        boolean result;
        try {
            WebElement element = driver.findElement(locator);
            log.debug("Found widget body element");
            result = true;
        }
        catch (Exception e) {
            result = false;
        }
        log.debug("Check if widget body is currently displayed completed. result is: {}", result);
        return result;
    }
}
