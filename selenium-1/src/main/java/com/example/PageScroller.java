package com.example;

import org.openqa.selenium.WebDriver;

public interface PageScroller {

    void scroll(WebDriver driver, PageScrollInstructions pageScrollInstructions);
}
