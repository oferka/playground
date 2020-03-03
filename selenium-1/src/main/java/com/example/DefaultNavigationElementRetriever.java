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
public class DefaultNavigationElementRetriever implements NavigationElementRetriever {

    @Override
    public WebElement retrieve(WebDriver driver, By locator) {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(getElement(driver, locator)));
    }

    @Override
    public boolean isDisplayed(WebDriver driver, By locator) {
        log.debug("Check if navigation element identified by locator {} is displayed started", locator);
        boolean result = getElement(driver, locator).isDisplayed();
        log.debug("Check if navigation element identified by locator {} is displayed completed. Result is {}", locator, result);
        return result;
    }

    private WebElement getElement(WebDriver driver, By locator) {
        return driver.findElement(locator);
    }
}
