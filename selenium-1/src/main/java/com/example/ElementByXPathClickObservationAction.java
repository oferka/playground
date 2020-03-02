package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ElementByXPathClickObservationAction extends ElementClickObservationAction {

    private String xpath;

    @Override
    WebElement getElement(WebDriver driver) {
        log.debug("Get element in element click by xpath started. xpath is {}", xpath);
        WebElement result = driver.findElement(By.xpath(xpath));
        log.debug("Get element in element click by xpath completed. xpath is {} element is {}", xpath, result);
        return result;
    }
}
