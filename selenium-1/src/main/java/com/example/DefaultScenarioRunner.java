package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.Pages.*;
import static java.util.Arrays.asList;

@Service
@Slf4j
public class DefaultScenarioRunner implements ScenarioRunner {

    @Autowired
    private BrowserOpener browserProvider;

    @Autowired
    private LandingPageOpener landingPageOpener;

    @Autowired
    private PageTester pageTester;

    @Override
    public void runScenario() {
        log.info("Run scenario started");
        WebDriver driver =  browserProvider.open();
        landingPageOpener.open(driver);
        testPages(
                driver,
                asList(
//                        OVERVIEW_PAGE,
                        APPS_OVERVIEW_PAGE//,
//                        SMART_WALK_THRUS_PAGE,
//                        WALK_THRUS_PAGE,
//                        ONBOARDING_PAGE,
//                        SHOUT_OUTS_PAGE,
//                        LAUNCHERS_PAGE,
//                        RESOURCES_PAGE,
//                        SHUTTLES_PAGE,
//                        SURVEYS_PAGE,
//                        SMART_TIPS_PAGE,
//                        MENU_AND_SEARCH_PAGE,
//                        USERS_PAGE,
//                        SESSION_PLAYBACK_PAGE,
//                        FEATURES_PAGE,
//                        FUNNELS_PAGE,
//                        TRACKED_EVENTS_ANALYTICS_PAGE,
//                        TRACKED_EVENTS_SETUP_PAGE,
//                        REPORTS_PAGE
                )
        );
        closeBrowser(driver);
        log.info("Run scenario completed");
    }

    private void testPages(WebDriver driver, List<Pages> pages) {
        log.info("Test '{}' pages started", pages.size());
        for(Pages page : pages) {
            pageTester.test(driver, page);
        }
        log.info("Test '{}' pages completed", pages.size());
    }

    public void closeBrowser(WebDriver driver) {
        log.info("Close browser started");
        driver.close();
        driver.quit();
        log.info("Close browser started");
    }
}
