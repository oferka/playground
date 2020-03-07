package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

@Service
@Slf4j
public class DefaultPageObserver implements PageObserver {

    @Autowired
    private WidgetsGroupObserver widgetsGroupObserver;

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private ExecutionPauser executionPauser;

    @Autowired
    private PageFilterController pageFilterController;

    @Override
    public void observe(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.debug("Observe {} page started", pageName);
        List<String> filterValues = pageFilterController.getFilterValues(driver, page);
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
        pageFilterController.setFilterValue(driver, page, filterValue);
        setTimePeriodValue(driver, page, timePeriodValue);
        observerWidgetGroup(driver, page.getWidgetGroups());
        log.debug("Observe {} page with filter {} and time period {} completed", pageName, filterValue, timePeriodValue);
    }

    private void observerWidgetGroup(WebDriver driver, List<WidgetGroups> widgetGroups) {
        for(WidgetGroups widgetsGroup : widgetGroups) {
            widgetsGroupObserver.observe(driver, widgetsGroup);
        }
    }

    private List<String> getTimePeriodValues(WebDriver driver, Pages page) {
        log.debug("Get time period values for page {} started", page.getName());
        openTimePeriodList(driver, page);
        executionPauser.pause();
        List<String> result = new ArrayList<>();
        List<WebElement> valueElements = driver.findElements(By.xpath("//div[@class='dropdown-menu-item']"));
        for(WebElement valueElement : valueElements) {
            if(!result.contains(valueElement.getText())) {
                result.add(valueElement.getText());
            }
        }
        closeTimePeriodList(driver);
        log.debug("Get time period values for page {} completed. Result is: {}", page.getName(), result);
        return result;
    }

    private void closeTimePeriodList(WebDriver driver) {
        log.debug("Close time period list started");
        WebElement timePeriodContainerElement = driver.findElement(By.xpath("//div[@class='dropdown-toggle dropdown-toggle--enabled report-header__datepicker-toggle report-header__datepicker-toggle--selected']"));
        elementHighlighter.highlight(driver, timePeriodContainerElement);
        timePeriodContainerElement.click();
        log.debug("Close time period list completed");
    }

    private void setTimePeriodValue(WebDriver driver, Pages page, String timePeriodValue) {
        log.debug("Set time period value to {} in page {} started", timePeriodValue, page.getName());
        WebElement timePeriodContainerElement = driver.findElement(By.xpath("//div[@class='dropdown-toggle dropdown-toggle--enabled report-header__datepicker-toggle report-header__datepicker-toggle--selected']"));
        elementHighlighter.highlight(driver, timePeriodContainerElement);
        String currentTimePeriodValue = getCurrentTimePeriodValue(driver, page);
        if(currentTimePeriodValue.equals(timePeriodValue)) {
            log.debug("Time period value {} is already selected in page {}", timePeriodValue, page.getName());
        }
        else {
            openTimePeriodList(driver, page);
            executionPauser.pause();
            selectTimePeriodValue(driver, page, timePeriodValue);
            executionPauser.pause();
        }
        log.debug("Set time period value to {} on page {} completed", timePeriodValue, page.getName());
    }

    private String getCurrentTimePeriodValue(WebDriver driver, Pages page) {
        log.debug("Get current time period value in page {} started", page.getName());
        WebElement timePeriodValueElement = driver.findElement(By.xpath("//div[@class='datepicker__dropdown-toggle__title']"));
        String result = timePeriodValueElement.getText();
        log.debug("Get current time period value in page {} completed. Result is {}", page.getName(), result);
        return result;
    }

    private void openTimePeriodList(WebDriver driver, Pages page) {
        log.debug("Open time period list in page {} started", page.getName());
        if(isTimePeriodListDisplayed(driver, page)) {
            log.debug("Time period list is already displayed in page {}", page.getName());
        }
        else {
            WebElement timePeriodTextElement = driver.findElement(By.xpath("//div[@class='datepicker__dropdown-toggle__title']"));
            elementHighlighter.highlight(driver, timePeriodTextElement);
            timePeriodTextElement.click();
        }
        log.debug("Open time period list in page {} completed", page.getName());
    }

    private boolean isTimePeriodListDisplayed(WebDriver driver, Pages page) {
        log.debug("Check if time period list is displayed in page {} started", page.getName());
        boolean result = false;
        List<WebElement> valueElements = driver.findElements(By.xpath("//div[@class='dropdown-menu-item']"));
        if(!valueElements.isEmpty()) {
            result = true;
        }
        log.debug("Check if time period list is displayed in page {} completed. Result is {}", page.getName(), result);
        return result;
    }

    private void selectTimePeriodValue(WebDriver driver, Pages page, String timePeriodValue) {
        log.debug("Select time period value {} in page {} started", timePeriodValue, page.getName());
        String xpath = format("//div[@class='dropdown-menu-item' and text()='%s']", timePeriodValue);
        WebElement timePeriodValueElement = driver.findElement(By.xpath(xpath));
        elementHighlighter.highlight(driver, timePeriodValueElement);
        executionPauser.pause();
        timePeriodValueElement.click();
        log.debug("Select time period value {} in page {} completed", timePeriodValue, page.getName());
    }
}
