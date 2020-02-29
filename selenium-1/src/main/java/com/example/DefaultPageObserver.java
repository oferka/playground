package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultPageObserver implements PageObserver {

    @Autowired
    private PageWigetsObserver pageWigetsObserver;

    @Override
    public void observe(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.debug("Observe {} page started", pageName);
        List<ObservedWidgetsGroup> observedWidgetsGroups = page.getObservedWidgetsGroups();
        for(ObservedWidgetsGroup observedWidgetsGroup : observedWidgetsGroups) {
            pageWigetsObserver.observe(driver, observedWidgetsGroup);
        }
        log.debug("Observe {} page completed", pageName);
    }
}
