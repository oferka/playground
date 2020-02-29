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
    private PageObserver pageObserver;

    @Override
    public void test(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.debug("Test {} page started", pageName);
        pageOpener.open(driver, page);
        pageObserver.observe(driver, page);
        log.debug("Test {} page completed", page.getName());
    }
}
