package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultPageTester implements PageTester {

    @Autowired
    private PageOpener pageOpener;

    @Autowired
    private PageWidgetsObserver pageWidgetsObserver;

    @Override
    public void test(WebDriver driver, Pages insightsPage) {
        log.info("Test {} insights page started", insightsPage.getName());
        pageOpener.open(driver, insightsPage);
        pageWidgetsObserver.observe(driver, insightsPage);
        log.info("Test {} insights page completed", insightsPage.getName());
    }
}
