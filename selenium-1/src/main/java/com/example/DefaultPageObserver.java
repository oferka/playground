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
    private WidgetsGroupObserver widgetsGroupObserver;

    @Autowired
    private PageFilterController pageFilterController;

    @Autowired
    private PageTimePeriodController pageTimePeriodController;

    @Override
    public void observe(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.debug("Observe '{}' page started", pageName);
        List<String> filterValues = pageFilterController.getFilterValues(driver, page);
        List<String> timePeriodValues = pageTimePeriodController.getTimePeriodValues(driver, page);
        for(String filterValue : filterValues) {
            for(String timePeriodValue : timePeriodValues) {
                observe(driver, page, filterValue, timePeriodValue);
            }
        }
        log.debug("Observe '{}' page completed", pageName);
    }

    private void observe(WebDriver driver, Pages page, String filterValue, String timePeriodValue) {
        String pageName = page.getName();
        log.debug("Observe '{}' page with filter '{}' and time period '{}' started", pageName, filterValue, timePeriodValue);
        pageFilterController.setFilterValue(driver, page, filterValue);
        pageTimePeriodController.setTimePeriodValue(driver, page, timePeriodValue);
        observerWidgetGroup(driver, page.getWidgetGroups());
        log.debug("Observe '{}' page with filter '{}' and time period '{}' completed", pageName, filterValue, timePeriodValue);
    }

    private void observerWidgetGroup(WebDriver driver, List<WidgetGroups> widgetGroups) {
        for(WidgetGroups widgetsGroup : widgetGroups) {
            widgetsGroupObserver.observe(driver, widgetsGroup);
        }
    }
}
