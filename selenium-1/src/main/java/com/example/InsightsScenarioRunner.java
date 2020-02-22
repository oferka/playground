package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.InsightsNavigationBarElements.*;
import static com.example.InsightsNavigationBarElements.InsightsNavigationBarElementTypes.NAVIGATION_ELEMET;
import static com.example.InsightsNavigationBarElements.InsightsNavigationBarLevels.FIRST_LEVEL;
import static com.example.InsightsNavigationBarElements.InsightsNavigationBarLevels.SECOND_LEVEL;

@Service
@Slf4j
public class InsightsScenarioRunner implements ScenarioRunner {

    @Override
    public void runScenario() {
        log.info("Run scenario started");
        WebDriver driver = openBrowser();
        openDAPHomePage(driver);
        login(driver);
        impersonate(driver);
        openOverview(driver);
        openApps(driver);
        openAppsOverview(driver);
        openSmartWalkThrus(driver);
        openWalkThrus(driver);
        openOnboarding(driver);
        openShoutOuts(driver);
        openLaunchers(driver);
        openResources(driver);
        openShuttles(driver);
        openSurveys(driver);
        openSmartTips(driver);
        openMenuAndSearch(driver);
        openUsers(driver);
        openSessionPlayback(driver);
        openFeatures(driver);
        openFunnels(driver);
        openTrackedEvents(driver);
        openTrackedEventsAnalytics(driver);
        openTrackedEventsSetup(driver);
        openReports(driver);
        closeBrowser(driver);
        log.info("Run scenario completed");
    }

    private WebDriver openBrowser() {
        log.info("Open browser started");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        log.info("Open browser completed");
        return driver;
    }

    private void openDAPHomePage(WebDriver driver) {
        log.info("Open DAP home page started");
        goToUrl(driver, "https://insights.walkme.com/", "WalkMe - Log in");
        log.info("Open DAP home page completed");
    }

    private void login(WebDriver driver) {
        log.info("Login started");
        enterText(driver, By.id("username"), "ofer.karp@walkme.com");
        enterText(driver, By.id("password"), "********************");
        log.info("Login completed");
    }

    private void impersonate(WebDriver driver) {
        log.info("Impersonate started");
        enterText(driver, By.className("react-autosuggest__input"), "kinnser@walkme.com");
        waitForPageLoad(driver, "Overview | Insights");
        log.info("Impersonate completed");
    }

    private void openOverview(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, OVERVIEW);
    }

    private void openApps(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, APPS);
    }

    private void openAppsOverview(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, APPS_OVERVIEW);
    }

    private void openSmartWalkThrus(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SMART_WALK_THRUS);
    }

    private void openWalkThrus(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, WALK_THRUS);
    }

    private void openOnboarding(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, ONBOARDING);
    }

    private void openShoutOuts(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SHOUT_OUTS);
    }

    private void openLaunchers(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, LAUNCHERS);
    }

    private void openResources(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, RESOURCES);
    }

    private void openShuttles(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SHUTTLES);
    }

    private void openSurveys(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SURVEYS);
    }

    private void openSmartTips(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SMART_TIPS);
    }

    private void openMenuAndSearch(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, MENU_AND_SEARCH);
    }

    private void openUsers(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, USERS);
    }

    private void openSessionPlayback(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SESSION_PLAYBACK);
    }

    private void openFeatures(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, FEATURES);
    }

    private void openFunnels(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, FUNNELS);
    }

    private void openTrackedEvents(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, TRACKED_EVENTS);
    }

    private void openTrackedEventsAnalytics(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, TRACKED_EVENTS_ANALYTICS);
    }

    private void openTrackedEventsSetup(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, TRACKED_EVENTS_SETUP);
    }

    private void openReports(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, REPORTS);
    }

    public void closeBrowser(WebDriver driver) {
        driver.close();
        driver.quit();
    }

    private void goToUrl(WebDriver driver, String address, String titleContains) {
        driver.get(address);
        waitForPageLoad(driver, titleContains);
    }

    private void enterText(WebDriver driver, By locator, String text) {
        enterText(driver, waitForAndGetElement(driver, locator), text);
    }

    private void waitForPageLoad(WebDriver driver, String titleContains) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.titleContains(titleContains));
    }

    private void enterText(WebDriver driver, WebElement element, String text) {
        highlightElement(driver, element);
        element.sendKeys(text);
        element.sendKeys(Keys.ENTER);
    }

    private void ClickInsightsNavigationBarElement(WebDriver driver, InsightsNavigationBarElements insightsNavigationBarElement) {
        log.info("Click {} insights navigation bar element started", insightsNavigationBarElement.getName());
        switch (insightsNavigationBarElement.getType()) {
            case NAVIGATION_ELEMET_GROUP:
                expandTopLevelNavigationElement(driver, insightsNavigationBarElement);
                break;
            case NAVIGATION_ELEMET:
                openInsightsPage(driver, insightsNavigationBarElement);
                break;
        }
        log.info("Click {} insights navigation bar element completed", insightsNavigationBarElement.getName());
    }

    private void expandTopLevelNavigationElement(WebDriver driver, InsightsNavigationBarElements insightsNavigationBarElement) {
        log.info("Expand {} insights navigation bar element started", insightsNavigationBarElement.getName());
        WebElement navigationElement = waitForAndGetInsightsNavigationElement(driver, insightsNavigationBarElement);
        highlightElement(driver, navigationElement);
        navigationElement.click();
        log.info("Expand {} insights navigation bar element completed", insightsNavigationBarElement.getName());
    }

    private void openInsightsPage(WebDriver driver, InsightsNavigationBarElements insightsNavigationBarElement) {
        log.info("Open {} insights page started", insightsNavigationBarElement.getName());
        WebElement navigationElement = waitForAndGetInsightsNavigationElement(driver, insightsNavigationBarElement);
        highlightElement(driver, navigationElement);
        navigationElement.click();
        waitForPageLoad(driver, insightsNavigationBarElement.getPageTitleContains());
        highlightInsightsPageHeader(driver, insightsNavigationBarElement);
        log.info("Open {} insights page completed", insightsNavigationBarElement.getName());
    }

    private void highlightInsightsPageHeader(WebDriver driver, InsightsNavigationBarElements insightsNavigationBarElement) {
        if (insightsNavigationBarElement.getType() == NAVIGATION_ELEMET) {
            PageHeaderRetriever pageHeaderRetriever = insightsNavigationBarElement.getPageHeaderRetriever();
            if(pageHeaderRetriever != null) {
                WebElement pageHeaderElement = pageHeaderRetriever.retrievePageHeader(driver);
                if(pageHeaderElement != null) {
                    highlightElement(driver, pageHeaderElement);
                }
            }
        }
    }

    private void highlightElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'color: navy; background: silver; border: 2px solid navy;');", element);
    }

    private WebElement waitForAndGetElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }

    private WebElement waitForAndGetInsightsNavigationElement(WebDriver driver, InsightsNavigationBarElements insightsNavigationBarElement) {
        WebElement result = null;
        if(insightsNavigationBarElement.getLevel() == FIRST_LEVEL) {
            result = waitForAndGetElementFromList(driver, By.className("side-navbar__button__text"), insightsNavigationBarElement.getItemIndex());
        }
        else {
            if(insightsNavigationBarElement.getLevel() == SECOND_LEVEL) {
                result = waitForAndGetElementFromList(driver, By.className("side-navbar__sub-item-button__text"), insightsNavigationBarElement.getItemIndex());
            }
        }
        return result;
    }

    private WebElement waitForAndGetElementFromList(WebDriver driver, By elementListLocator, int index) {
        List<WebElement> elements = driver.findElements(elementListLocator);
        WebElement element = elements.get(index);
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        return element;
    }
}
