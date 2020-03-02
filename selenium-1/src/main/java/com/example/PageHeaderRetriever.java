package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface PageHeaderRetriever {

    WebElement retrieve(WebDriver driver, By locator);

    boolean isDisplayed(WebDriver driver, By locator);
}
