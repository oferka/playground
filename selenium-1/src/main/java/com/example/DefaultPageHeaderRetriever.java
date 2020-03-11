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
        log.debug("Check if page header is currently displayed using locator '{}' started", locator.toString());
        boolean result;
        try {
            WebElement element = driver.findElement(locator);
            log.debug("Found page header element with text '{}'", element.getText());
            result = true;
        }
        catch (Exception e) {
            result = false;
        }
        log.debug("Check if page header is currently displayed using locator '{}' completed. result is: {}", locator.toString(), result);
        return result;
    }
}
