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
    public WebElement retrieveNavigationElement(WebDriver driver) {
        return new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeClickable(getNavigationElement(driver)));
    }

    @Override
    public boolean isDisplayed(WebDriver driver) {
        return getNavigationElement(driver).isDisplayed();
    }

    private WebElement getNavigationElement(WebDriver driver) {
        String xpath = format("//*[@class='%s' and text()='%s']", className, text);
        By locator = By.xpath(xpath);
        return driver.findElement(locator);
    }
}
