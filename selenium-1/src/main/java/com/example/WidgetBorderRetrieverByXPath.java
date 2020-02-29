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
public class WidgetBorderRetrieverByXPath implements WidgetBorderRetriever {

    private String xpath;

    @Override
    public WebElement retrieve(WebDriver driver) {
        return new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(getLocator()));
    }

    private By getLocator() {
        return By.xpath(xpath);
    }
}
