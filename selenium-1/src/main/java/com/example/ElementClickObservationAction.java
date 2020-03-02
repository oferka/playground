package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Slf4j
public abstract class ElementClickObservationAction extends ObservationAction {

    @Override
    void execute(WebDriver driver) {
        log.debug("Element click observation action started");
        WebElement element = getElement(driver);
        element.click();
        log.debug("Element click observation action completed");
    }

    abstract WebElement getElement(WebDriver driver);
}
