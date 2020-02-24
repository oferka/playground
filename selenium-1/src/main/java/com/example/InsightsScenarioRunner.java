package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.InsightsPages.*;
import static java.util.Arrays.asList;

@Service
@Slf4j
public class InsightsScenarioRunner implements ScenarioRunner {

    @Autowired
    private BrowserProvider browserProvider;

    @Override
    public void runScenario() {
        log.info("Run scenario started");
        WebDriver driver =  browserProvider.openBrowser();
        openDAPHomePage(driver);
        login(driver);
        impersonate(driver);
        openInsightsPages(
                driver,
                asList(
                        OVERVIEW_PAGE,
                        APPS_OVERVIEW_PAGE,
                        SMART_WALK_THRUS_PAGE,
                        WALK_THRUS_PAGE,
                        ONBOARDING_PAGE,
                        SHOUT_OUTS_PAGE,
                        LAUNCHERS_PAGE,
                        RESOURCES_PAGE,
                        SHUTTLES_PAGE,
                        SURVEYS_PAGE,
                        SMART_TIPS_PAGE,
                        MENU_AND_SEARCH_PAGE,
                        USERS_PAGE,
                        SESSION_PLAYBACK_PAGE,
                        FEATURES_PAGE,
                        FUNNELS_PAGE,
                        TRACKED_EVENTS_ANALYTICS_PAGE,
                        TRACKED_EVENTS_SETUP_PAGE,
                        REPORTS_PAGE
                )
        );
        closeBrowser(driver);
        log.info("Run scenario completed");
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

    private void openInsightsPages(WebDriver driver, List<InsightsPages> insightsPages) {
        for(InsightsPages insightsPage : insightsPages) {
            openInsightsPage(driver, insightsPage);
        }
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
