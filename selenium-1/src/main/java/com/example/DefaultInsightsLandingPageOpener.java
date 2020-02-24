package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultInsightsLandingPageOpener implements InsightsLandingPageOpener {

    @Autowired
    private InsightsLandingPageConfiguration insightsLandingPageConfiguration;

    @Autowired
    private LoginConfiguration loginConfiguration;

    @Override
    public void open(WebDriver driver) {
        openInsightsPage(driver);
        login(driver);
        impersonate(driver);
    }

    private void openInsightsPage(WebDriver driver) {
        driver.get(insightsLandingPageConfiguration.getAddress());
    }

    private void login(WebDriver driver) {
        enterText(driver, By.id("username"), loginConfiguration.getUsername());
        enterText(driver, By.id("password"), loginConfiguration.getPassword());
    }

    private void impersonate(WebDriver driver) {
        enterText(driver, By.className("react-autosuggest__input"), "kinnser@walkme.com");
        waitForPageLoad(driver, "Overview | Insights");
    }

    private void waitForPageLoad(WebDriver driver, String titleContains) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleContains(titleContains));
    }

    private void enterText(WebDriver driver, By locator, String text) {
        enterText(driver, waitForAndGetElement(driver, locator), text);
    }

    private void enterText(WebDriver driver, WebElement element, String text) {
        highlightElement(driver, element);
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }

    private WebElement waitForAndGetElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }

    private void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'color: navy; background: silver; border: 2px solid navy;');", element);
    }
}
