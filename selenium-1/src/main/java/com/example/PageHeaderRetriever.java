package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface PageHeaderRetriever {

    WebElement retrieve(WebDriver driver);

    boolean isDisplayed(WebDriver driver);
}
