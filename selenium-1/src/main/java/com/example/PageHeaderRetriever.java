package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface PageHeaderRetriever {

    WebElement retrievePageHeader(WebDriver driver);

    boolean isDisplayed(WebDriver driver);
}
