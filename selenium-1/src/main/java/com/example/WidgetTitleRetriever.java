package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface WidgetTitleRetriever {

    WebElement retrieve(WebDriver driver, By locator);
}
