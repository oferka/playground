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
public class WidgetBodyRetrieverByClass implements WidgetBodyRetriever {

    private String className;

    @Override
    public WebElement retrieve(WebDriver driver) {
        return new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(getLocator()));
    }

    private By getLocator() {
        String xpath = format("//*[@class='%s']", className);
        return By.xpath(xpath);
    }
}
