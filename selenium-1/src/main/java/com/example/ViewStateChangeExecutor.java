package com.example;

import org.openqa.selenium.WebDriver;

public interface ViewStateChangeExecutor {

    void execute(WebDriver driver, ViewStateChangeInstructions viewStateChangeInstructions);
}
