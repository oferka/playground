package com.example;

import org.openqa.selenium.WebDriver;

public interface WidgetObserver {

    void observe(WebDriver driver, Widgets widget);
}
