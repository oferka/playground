package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
@AllArgsConstructor
@Slf4j
public class PageHeaderRetrieverByXPath implements PageHeaderRetriever {

    private String xpath;

    @Override
    public WebElement retrieve(WebDriver driver) {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(getLocator()));
    }

    @Override
    public boolean isDisplayed(WebDriver driver) {
        log.debug("Check if page header identified by xpath: {} is currently displayed started", xpath);
        boolean result;
        try {
            driver.findElement(getLocator());
            result = true;
        }
        catch (Exception e) {
            result = false;
        }
        log.debug("Check if page header identified by xpath: {} is currently displayed completed. result is: {}", xpath, result);
        return result;
    }

    private By getLocator() {
        return By.xpath(xpath);
    }
}
