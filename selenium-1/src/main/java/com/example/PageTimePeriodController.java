package com.example;

import org.openqa.selenium.WebDriver;

import java.util.List;

public interface PageTimePeriodController {

    List<String> getTimePeriodValues(WebDriver driver, Pages page);

    void setTimePeriodValue(WebDriver driver, Pages page, String timePeriodValue);
}
