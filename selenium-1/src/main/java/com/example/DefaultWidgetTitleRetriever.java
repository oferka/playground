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
public class DefaultWidgetTitleRetriever implements WidgetTitleRetriever {

     @Override
    public WebElement retrieve(WebDriver driver, By locator) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
