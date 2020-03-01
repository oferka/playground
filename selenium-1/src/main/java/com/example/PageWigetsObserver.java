package com.example;

import org.openqa.selenium.WebDriver;

public interface PageWigetsObserver {

    void observe(WebDriver driver, WidgetsGroup widgetsGroup);
}
