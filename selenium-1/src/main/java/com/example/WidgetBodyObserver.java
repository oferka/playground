package com.example;

import org.openqa.selenium.WebDriver;

public interface WidgetBodyObserver {

    WidgetBodyState observe(WebDriver driver, Widgets widget);
}
