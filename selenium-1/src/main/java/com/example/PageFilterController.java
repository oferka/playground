package com.example;

import org.openqa.selenium.WebDriver;

import java.util.List;

public interface PageFilterController {

    List<String> getFilterValues(WebDriver driver, Pages page);

    void setFilterValue(WebDriver driver, Pages page, String filterValue);
}
