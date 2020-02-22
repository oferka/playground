package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import static com.example.InsightsNavigationBarElements.*;
import static com.example.InsightsNavigationBarElements.InsightsNavigationBarElementTypes.NAVIGATION_ELEMET;

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
        openOverviewPage(driver);
        openAppsMenu(driver);
        openAppsOverviewPage(driver);
        openSmartWalkThrusPage(driver);
        openWalkThrusPage(driver);
        openOnboardingPage(driver);
        openShoutOutsPage(driver);
        openLaunchersPage(driver);
        openResourcesPage(driver);
        openShuttlesPage(driver);
        openSurveysPage(driver);
        openSmartTipsPage(driver);
        openMenuAndSearchPage(driver);
        openUsersPage(driver);
        openSessionPlaybackPage(driver);
        openFeaturesPage(driver);
        openFunnelsPage(driver);
        openTrackedEventsMenu(driver);
        openTrackedEventsAnalyticsPage(driver);
        openTrackedEventsSetupPage(driver);
        openReportsPage(driver);
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
        enterText(driver, By.id("password"), "*****");
        log.info("Login completed");
    }

    private void impersonate(WebDriver driver) {
        log.info("Impersonate started");
        enterText(driver, By.className("react-autosuggest__input"), "kinnser@walkme.com");
        waitForPageLoad(driver, "Overview | Insights");
        log.info("Impersonate completed");
    }

    private void openOverviewPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, OVERVIEW);
    }

    private void openAppsMenu(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, APPS);
    }

    private void openAppsOverviewPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, APPS_OVERVIEW);
    }

    private void openSmartWalkThrusPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SMART_WALK_THRUS);
    }

    private void openWalkThrusPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, WALK_THRUS);
    }

    private void openOnboardingPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, ONBOARDING);
    }

    private void openShoutOutsPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SHOUT_OUTS);
    }

    private void openLaunchersPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, LAUNCHERS);
    }

    private void openResourcesPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, RESOURCES);
    }

    private void openShuttlesPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SHUTTLES);
    }

    private void openSurveysPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SURVEYS);
    }

    private void openSmartTipsPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SMART_TIPS);
    }

    private void openMenuAndSearchPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, MENU_AND_SEARCH);
    }

    private void openUsersPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, USERS);
    }

    private void openSessionPlaybackPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, SESSION_PLAYBACK);
    }

    private void openFeaturesPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, FEATURES);
    }

    private void openFunnelsPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, FUNNELS);
    }

    private void openTrackedEventsMenu(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, TRACKED_EVENTS);
    }

    private void openTrackedEventsAnalyticsPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, TRACKED_EVENTS_ANALYTICS);
    }

    private void openTrackedEventsSetupPage(WebDriver driver) {
        ClickInsightsNavigationBarElement(driver, TRACKED_EVENTS_SETUP);
    }

    private void openReportsPage(WebDriver driver) {
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
        WebElement navigationElement = insightsNavigationBarElement.getNavigationElementRetriever().retrieveNavigationElement(driver);
        highlightElement(driver, navigationElement);
        navigationElement.click();
        log.info("Expand {} insights navigation bar element completed", insightsNavigationBarElement.getName());
    }

    private void openInsightsPage(WebDriver driver, InsightsNavigationBarElements insightsNavigationBarElement) {
        log.info("Open {} insights page started", insightsNavigationBarElement.getName());
        WebElement navigationElement = insightsNavigationBarElement.getNavigationElementRetriever().retrieveNavigationElement(driver);
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
}
