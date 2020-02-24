package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultInsightsLandingPageOpener implements InsightsLandingPageOpener {

    @Override
    public void open(WebDriver driver) {
        openInsightsPage(driver);
        login(driver);
        impersonate(driver);
    }

    private void openInsightsPage(WebDriver driver) {
        log.info("Open DAP home page started");
        goToUrl(driver, "https://insights.walkme.com/", "WalkMe - Log in");
        log.info("Open DAP home page completed");
    }

    private void login(WebDriver driver) {
        log.info("Login started");
        enterText(driver, By.id("username"), "ofer.karp@walkme.com");
        enterText(driver, By.id("password"), "");
        log.info("Login completed");
    }

    private void impersonate(WebDriver driver) {
        log.info("Impersonate started");
        enterText(driver, By.className("react-autosuggest__input"), "kinnser@walkme.com");
        waitForPageLoad(driver, "Overview | Insights");
        log.info("Impersonate completed");
    }

    private void goToUrl(WebDriver driver, String address, String titleContains) {
        driver.get(address);
        waitForPageLoad(driver, titleContains);
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
