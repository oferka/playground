package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultLandingPageOpener implements LandingPageOpener {

    @Autowired
    private LandingPageConfiguration landingPageConfiguration;

    @Autowired
    private LoginConfiguration loginConfiguration;

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Override
    public void open(WebDriver driver) {
        log.debug("Open landing page started");
        navigateToPage(driver);
        login(driver);
        impersonate(driver);
        log.debug("Open landing page completed");
    }

    private void navigateToPage(WebDriver driver) {
        String address = landingPageConfiguration.getAddress();
        log.debug("Navigate to page {} started", address);
        driver.get(address);
        log.debug("Navigate to page {} completed", address);
    }

    private void login(WebDriver driver) {
        log.debug("Login started");
        enterText(driver, By.id("username"), loginConfiguration.getUsername());
        enterText(driver, By.id("password"), loginConfiguration.getPassword());
        log.debug("Login completed");
    }

    private void impersonate(WebDriver driver) {
        String impersonateUserName = loginConfiguration.getImpersonateUsername();
        log.debug("Impersonate to user {} started", impersonateUserName);
        enterText(driver, By.className("react-autosuggest__input"), impersonateUserName);
        new WebDriverWait(driver, landingPageConfiguration.getTimeOutInSeconds()).until(ExpectedConditions.titleContains(landingPageConfiguration.getTitleContains()));
        log.debug("Impersonate to user {} completed", impersonateUserName);
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
