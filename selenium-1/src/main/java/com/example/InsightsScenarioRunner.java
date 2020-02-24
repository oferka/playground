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

    @Autowired
    private InsightsLandingPageOpener insightsLandingPageOpener;

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Override
    public void runScenario() {
        log.info("Run scenario started");
        WebDriver driver =  browserProvider.openBrowser();
        insightsLandingPageOpener.open(driver);
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
                        REPORTS_PAGE,
                        OVERVIEW_PAGE
                )
        );
        closeBrowser(driver);
        log.info("Run scenario completed");
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

    private void expandTopLevelNavigationElement(WebDriver driver, InsightsNavigationBarElementGroups insightsNavigationBarElementGroup) {
        log.info("Expand {} insights navigation bar element started", insightsNavigationBarElementGroup.getName());
        WebElement navigationElement = insightsNavigationBarElementGroup.getNavigationElementRetriever().retrieveNavigationElement(driver);
        elementHighlighter.highlight(driver, navigationElement);
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
        elementHighlighter.highlight(driver, navigationElement);
        navigationElement.click();
        new WebDriverWait(driver, 30).until(ExpectedConditions.titleContains(insightsPage.getPageTitleContains()));
        highlightInsightsPageHeader(driver, insightsPage);
        log.info("Open {} insights page completed", insightsPage.getName());
    }

    private void highlightInsightsPageHeader(WebDriver driver, InsightsPages insightsPage) {
        PageHeaderRetriever pageHeaderRetriever = insightsPage.getPageHeaderRetriever();
        WebElement pageHeaderElement = pageHeaderRetriever.retrievePageHeader(driver);
        elementHighlighter.highlight(driver, pageHeaderElement);
    }
}
