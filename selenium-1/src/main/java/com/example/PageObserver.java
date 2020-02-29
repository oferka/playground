package com.example;

import org.openqa.selenium.WebDriver;

public interface PageObserver {

    void observe(WebDriver driver, Pages page);
}
