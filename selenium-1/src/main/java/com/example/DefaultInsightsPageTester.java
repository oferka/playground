package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultInsightsPageTester implements InsightsPageTester {

    @Autowired
    private InsightsPageOpener insightsPageOpener;

    @Override
    public void test(WebDriver driver, InsightsPages insightsPage) {
        log.info("Test {} insights page started", insightsPage.getName());
        insightsPageOpener.open(driver, insightsPage);
        log.info("Test {} insights page completed", insightsPage.getName());
    }
}
