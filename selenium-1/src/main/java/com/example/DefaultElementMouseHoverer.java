package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultElementMouseHoverer implements ElementMouseHoverer {

    @Autowired
    private ElementMouseHovererConfiguration elementMouseHovererConfiguration;

    @Override
    public void hover(WebDriver driver, WebElement element) {
        log.debug("Element {} mouse hover started", element.getText());
        if(elementMouseHovererConfiguration.isEnabled()) {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).perform();
        }
        log.debug("Element {} mouse hover completed", element.getText());
    }
}
