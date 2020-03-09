package com.example;

import org.openqa.selenium.WebDriver;

public interface WidgetBodyObserver {

    WidgetBodyStateInstructions observe(WebDriver driver, Widgets widget);
}
