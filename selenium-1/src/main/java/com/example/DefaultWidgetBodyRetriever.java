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
        log.info("Check if widget body is currently displayed started");
        boolean result;
        try {
            log.debug("Trying to find element using locator '{}'", locator.toString());
            driver.findElement(locator);
            log.debug("Element found");
            result = true;
        }
        catch (Exception e) {
            log.debug("Element not found. Error message is: '{}'", e.getMessage()   );
            result = false;
        }
        log.info("Check if widget body is currently displayed completed. result is: {}", result);
        return result;
    }
}
