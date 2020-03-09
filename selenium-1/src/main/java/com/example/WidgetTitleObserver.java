package com.example;

import org.openqa.selenium.WebDriver;

public interface WidgetTitleObserver {

    void observe(WebDriver driver, Widgets widget);
}
