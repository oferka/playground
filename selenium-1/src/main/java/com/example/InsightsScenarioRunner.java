package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.*;
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
    private InsightsPageOpener insightsPageOpener;

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
            insightsPageOpener.open(driver, insightsPage);
        }
    }

    public void closeBrowser(WebDriver driver) {
        driver.close();
        driver.quit();
    }
}
