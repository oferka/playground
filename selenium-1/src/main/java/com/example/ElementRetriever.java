package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ElementRetriever {

    WebElement retrieve(WebDriver webDriver, By locator);

    boolean isDisplayed(WebDriver driver, By locator);
}
