package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

@Service
@Slf4j
public class DefaultPageObserver implements PageObserver {

    @Autowired
    private WidgetsGroupObserver widgetsGroupObserver;

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private ExecutionPauser executionPauser;

    @Override
    public void observe(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.debug("Observe {} page started", pageName);
        List<String> filterValues = getFilterValues(driver, page);
        List<String> timePeriodValues = getTimePeriodValues(driver, page);
        for(String filterValue : filterValues) {
            for(String timePeriodValue : timePeriodValues) {
                observe(driver, page, filterValue, timePeriodValue);
            }
        }
        log.debug("Observe {} page completed", pageName);
    }

    private void observe(WebDriver driver, Pages page, String filterValue, String timePeriodValue) {
        String pageName = page.getName();
        log.debug("Observe {} page with filter {} and time period {} started", pageName, filterValue, timePeriodValue);
        setFilterValue(driver, filterValue);
        setTimePeriodValue(driver, timePeriodValue);
        observerWidgetGroup(driver, page.getWidgetGroups());
        log.debug("Observe {} page with filter {} and time period {} completed", pageName, filterValue, timePeriodValue);
    }

    private void observerWidgetGroup(WebDriver driver, List<WidgetGroups> widgetGroups) {
        for(WidgetGroups widgetsGroup : widgetGroups) {
            widgetsGroupObserver.observe(driver, widgetsGroup);
        }
    }

    private List<String> getFilterValues(WebDriver driver, Pages page) {
        return asList(
                "All Sessions",
                "need training visits",
                "Chrome users",
                "Accessed PPS+ Article"
        );
    }

    private List<String> getTimePeriodValues(WebDriver driver, Pages page) {
        return asList(
                "Today",
                "Yesterday",
                "Last Week",
                "Last Month",
                "Last 3 Months",
                "Last 7 Days",
                "Last 30 Days",
                "Last 90 Days"
        );
    }

    private void setFilterValue(WebDriver driver, String filterValue) {
        log.debug("Set filter value to {} started", filterValue);
        WebElement filterContainerElement = driver.findElement(By.xpath("//div[@class='dropdown-toggle dropdown-toggle--enabled report-header__dropdown-toggle report-header__dropdown-toggle--selected']"));
        elementHighlighter.highlight(driver, filterContainerElement);
        String currentFilterValue = getCurrentFilterValue(driver);
        if(currentFilterValue.equals(filterValue)) {
            log.debug("Filter value {} is already selected", filterValue);
        }
        else {
            openFilterList(driver);
            executionPauser.pause(Duration.ofSeconds(1));
            selectFilterValue(driver, filterValue);
            executionPauser.pause(Duration.ofSeconds(1));
        }
        log.debug("Set filter value to {} completed", filterValue);
    }

    private String getCurrentFilterValue(WebDriver driver) {
        log.debug("Get current filter value started");
        WebElement filterValueElement = driver.findElement(By.xpath("//span[@class='suggestion-highlight-container']"));
        String result = filterValueElement.getText();
        log.debug("Get current filter value completed. Result is {}", result);
        return result;
    }

    private void openFilterList(WebDriver driver) {
        log.debug("Open filter list started");
        WebElement filterTextElement = driver.findElement(By.xpath("//span[@class='suggestion-highlight-container']/ancestor::div[@class='overflow-tooltip']"));
        elementHighlighter.highlight(driver, filterTextElement);
        filterTextElement.click();
        log.debug("Open filter list completed");
    }

    private void selectFilterValue(WebDriver driver, String filterValue) {
        log.debug("Select filter value {} started", filterValue);
        String xpath = format("//span[@class='suggestion-highlight-container' and text()='%s']", filterValue);
        WebElement filterValueElement = driver.findElement(By.xpath(xpath));
        elementHighlighter.highlight(driver, filterValueElement);
        executionPauser.pause();
        filterValueElement.click();
        log.debug("Select filter value {} completed", filterValue);
    }

    private void setTimePeriodValue(WebDriver driver, String timePeriodValue) {
        log.debug("Set time period value to {} started", timePeriodValue);
        WebElement timePeriodContainerElement = driver.findElement(By.xpath("//div[@class='dropdown-toggle dropdown-toggle--enabled report-header__datepicker-toggle report-header__datepicker-toggle--selected']"));
        elementHighlighter.highlight(driver, timePeriodContainerElement);
        String currentTimePeriodValue = getCurrentTimePeriodValue(driver);
        if(currentTimePeriodValue.equals(timePeriodValue)) {
            log.debug("Time period value {} is already selected", timePeriodValue);
        }
        else {
            openTimePeriodList(driver);
            executionPauser.pause(Duration.ofSeconds(1));
            selectTimePeriodValue(driver, timePeriodValue);
            executionPauser.pause(Duration.ofSeconds(1));
        }
        log.debug("Set time period value to {} completed", timePeriodValue);
    }

    private String getCurrentTimePeriodValue(WebDriver driver) {
        log.debug("Get current time period value started");
        WebElement timePeriodValueElement = driver.findElement(By.xpath("//div[@class='datepicker__dropdown-toggle__title']"));
        String result = timePeriodValueElement.getText();
        log.debug("Get current time period value completed. Result is {}", result);
        return result;
    }

    private void openTimePeriodList(WebDriver driver) {
        log.debug("Open time period list started");
        WebElement timePeriodTextElement = driver.findElement(By.xpath("//div[@class='datepicker__dropdown-toggle__title']"));
        elementHighlighter.highlight(driver, timePeriodTextElement);
        timePeriodTextElement.click();
        log.debug("Open time period list completed");
    }

    private void selectTimePeriodValue(WebDriver driver, String timePeriodValue) {
        log.debug("Select time period value {} started", timePeriodValue);
        String xpath = format("//div[@class='dropdown-menu-item' and text()='%s']", timePeriodValue);
        WebElement timePeriodValueElement = driver.findElement(By.xpath(xpath));
        elementHighlighter.highlight(driver, timePeriodValueElement);
        executionPauser.pause();
        timePeriodValueElement.click();
        log.debug("Select time period value {} completed", timePeriodValue);
    }
}
