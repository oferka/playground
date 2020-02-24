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

    @Autowired
    private ElementHighlighter elementHighlighter;

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
        enterText(driver, By.className("react-autosuggest__input"), loginConfiguration.getImpersonateUsername());
        new WebDriverWait(driver, insightsLandingPageConfiguration.getTimeOutInSeconds()).until(ExpectedConditions.titleContains(insightsLandingPageConfiguration.getTitleContains()));
    }

    private void enterText(WebDriver driver, By locator, String text) {
        enterText(driver, waitForAndGetElement(driver, locator), text);
    }

    private void enterText(WebDriver driver, WebElement element, String text) {
        elementHighlighter.highlight(driver, element);
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }

    private WebElement waitForAndGetElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, loginConfiguration.getEnterTextTimeoutInSeconds());
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }
}
