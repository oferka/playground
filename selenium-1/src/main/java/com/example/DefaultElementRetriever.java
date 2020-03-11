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
public class DefaultElementRetriever implements ElementRetriever {

    @Override
    public WebElement retrieve(WebDriver driver, By locator) {
        return new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    @Override
    public boolean isDisplayed(WebDriver driver, By locator) {
        log.info("Check if element is currently displayed started");
        boolean result;
        try {
            log.debug("Trying to find element using locator '{}'", locator.toString());
            driver.findElement(locator);
            log.debug("Element found");
            result = true;
        }
        catch (Exception e) {
            log.debug("Element was not found");
            result = false;
        }
        log.info("Check if element is currently displayed completed. result is: {}", result);
        return result;
    }
}
