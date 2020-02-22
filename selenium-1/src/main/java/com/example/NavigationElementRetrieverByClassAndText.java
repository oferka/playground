package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.String.format;

@Data
@AllArgsConstructor
public class NavigationElementRetrieverByClassAndText implements NavigationElementRetriever {

    private String className;

    private String text;

    @Override
    public WebElement retrieveNavigationElement(WebDriver driver) {
        String xpath = format("//*[@class='%s' and text()='%s']", className, text);
        By locator = By.xpath(xpath);
        return new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(locator));
    }
}
