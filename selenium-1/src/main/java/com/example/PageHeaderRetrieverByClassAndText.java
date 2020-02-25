package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;

@Data
@AllArgsConstructor
@Slf4j
public class PageHeaderRetrieverByClassAndText implements PageHeaderRetriever {

    private String className;

    private String text;

    @Override
    public WebElement retrievePageHeader(WebDriver driver) {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(getLocator()));
    }

    @Override
    public boolean isDisplayed(WebDriver driver) {
        log.info("Check if insights page identified by text: {} is currently displayed started", text);
        boolean result;
        try {
            driver.findElement(getLocator());
            result = true;
        }
        catch (Exception e) {
            result = false;
        }
        log.info("Check if insights page identified by text: {} is currently displayed completed. result is: {}", text, result);
        return result;
    }

    private By getLocator() {
        String xpath = format("//*[@class='%s' and text()='%s']", className, text);
        return By.xpath(xpath);
    }
}
