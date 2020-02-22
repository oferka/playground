package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

@Data
@AllArgsConstructor
public class PageHeaderRetrieverByClassAndText implements PageHeaderRetriever {

    private String className;

    private String text;

    @Override
    public WebElement retrievePageHeader(WebDriver driver) {
        return waitForAndGetElement(driver, By.className(className), text);
    }

    private WebElement waitForAndGetElement(WebDriver driver, By locator, String expectedElementText) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        WebElement pageHeaderElement = driver.findElement(locator);
        if(pageHeaderElement.getText().equals(expectedElementText)) {
            return pageHeaderElement;
        }
        return null;
    }
}
