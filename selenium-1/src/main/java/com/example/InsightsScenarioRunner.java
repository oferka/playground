package com.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import static com.example.InsightsPages.*;

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
        enterText(driver, By.id("password"), "");
        log.info("Login completed");
    }

    private void impersonate(WebDriver driver) {
        log.info("Impersonate started");
        enterText(driver, By.className("react-autosuggest__input"), "kinnser@walkme.com");
        waitForPageLoad(driver, "Overview | Insights");
        log.info("Impersonate completed");
    }

    private void openOverviewPage(WebDriver driver) {
        openInsightsPage(driver, OVERVIEW_PAGE);
    }

    private void openAppsOverviewPage(WebDriver driver) {
        openInsightsPage(driver, APPS_OVERVIEW_PAGE);
    }

    private void openSmartWalkThrusPage(WebDriver driver) {
        openInsightsPage(driver, SMART_WALK_THRUS_PAGE);
    }

    private void openWalkThrusPage(WebDriver driver) {
        openInsightsPage(driver, WALK_THRUS_PAGE);
    }

    private void openOnboardingPage(WebDriver driver) {
        openInsightsPage(driver, ONBOARDING_PAGE);
    }

    private void openShoutOutsPage(WebDriver driver) {
        openInsightsPage(driver, SHOUT_OUTS_PAGE);
    }

    private void openLaunchersPage(WebDriver driver) {
        openInsightsPage(driver, LAUNCHERS_PAGE);
    }

    private void openResourcesPage(WebDriver driver) {
        openInsightsPage(driver, RESOURCES_PAGE);
    }

    private void openShuttlesPage(WebDriver driver) {
        openInsightsPage(driver, SHUTTLES_PAGE);
    }

    private void openSurveysPage(WebDriver driver) {
        openInsightsPage(driver, SURVEYS_PAGE);
    }

    private void openSmartTipsPage(WebDriver driver) {
        openInsightsPage(driver, SMART_TIPS_PAGE);
    }

    private void openMenuAndSearchPage(WebDriver driver) {
        openInsightsPage(driver, MENU_AND_SEARCH_PAGE);
    }

    private void openUsersPage(WebDriver driver) {
        openInsightsPage(driver, USERS_PAGE);
    }

    private void openSessionPlaybackPage(WebDriver driver) {
        openInsightsPage(driver, SESSION_PLAYBACK_PAGE);
    }

    private void openFeaturesPage(WebDriver driver) {
        openInsightsPage(driver, FEATURES_PAGE);
    }

    private void openFunnelsPage(WebDriver driver) {
        openInsightsPage(driver, FUNNELS_PAGE);
    }

    private void openTrackedEventsAnalyticsPage(WebDriver driver) {
        openInsightsPage(driver, TRACKED_EVENTS_ANALYTICS_PAGE);
    }

    private void openTrackedEventsSetupPage(WebDriver driver) {
        openInsightsPage(driver, TRACKED_EVENTS_SETUP_PAGE);
    }

    private void openReportsPage(WebDriver driver) {
        openInsightsPage(driver, REPORTS_PAGE);
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

    private void expandTopLevelNavigationElement(WebDriver driver, InsightsNavigationBarElementGroups insightsNavigationBarElementGroup) {
        log.info("Expand {} insights navigation bar element started", insightsNavigationBarElementGroup.getName());
        WebElement navigationElement = insightsNavigationBarElementGroup.getNavigationElementRetriever().retrieveNavigationElement(driver);
        highlightElement(driver, navigationElement);
        navigationElement.click();
        log.info("Expand {} insights navigation bar element completed", insightsNavigationBarElementGroup.getName());
    }

    private void openInsightsPage(WebDriver driver, InsightsPages insightsPage) {
        log.info("Open {} insights page started", insightsPage.getName());
        InsightsNavigationBarElements insightsNavigationBarElement = insightsPage.getNavigationBarElement();
        NavigationElementRetriever navigationElementRetriever = insightsNavigationBarElement.getNavigationElementRetriever();
        if(!navigationElementRetriever.isDisplayed(driver)) {
            InsightsNavigationBarElementGroups insightsNavigationBarElementGroup = insightsPage.getNavigationBarElement().getInsightsNavigationBarElementGroup();
            log.debug("Navigation element {} is not currently displayed. Going to expand navigation elements group {}", insightsNavigationBarElement.getName(), insightsNavigationBarElementGroup.getName());
            expandTopLevelNavigationElement(driver, insightsNavigationBarElementGroup);
        }
        WebElement navigationElement = navigationElementRetriever.retrieveNavigationElement(driver);
        highlightElement(driver, navigationElement);
        navigationElement.click();
        waitForPageLoad(driver, insightsPage.getPageTitleContains());
        highlightInsightsPageHeader(driver, insightsPage);
        log.info("Open {} insights page completed", insightsPage.getName());
    }

    private void highlightInsightsPageHeader(WebDriver driver, InsightsPages insightsPage) {
        PageHeaderRetriever pageHeaderRetriever = insightsPage.getPageHeaderRetriever();
        WebElement pageHeaderElement = pageHeaderRetriever.retrievePageHeader(driver);
        highlightElement(driver, pageHeaderElement);
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
