package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;

@Data
@AllArgsConstructor
@Slf4j
public class NavigationElementRetrieverByClassAndText implements NavigationElementRetriever {

    private String className;

    private String text;

    @Override
    public WebElement retrieve(WebDriver driver) {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(getNavigationElement(driver)));
    }

    @Override
    public boolean isDisplayed(WebDriver driver) {
        log.debug("Check if navigation element identified by text {} is displayed started", text);
        boolean result = getNavigationElement(driver).isDisplayed();
        log.debug("Check if navigation element identified by text {} is displayed completed. Result is {}", text, result);
        return result;
    }

    private WebElement getNavigationElement(WebDriver driver) {
        String xpath = format("//*[@class='%s' and text()='%s']", className, text);
        By locator = By.xpath(xpath);
        return driver.findElement(locator);
    }
}
