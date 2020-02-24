package com.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public interface ElementHighlighter {

    void highlight(WebDriver driver, WebElement element);
}
