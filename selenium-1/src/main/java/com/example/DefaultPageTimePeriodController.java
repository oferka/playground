package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;
import static java.util.Arrays.asList;

@Service
@Slf4j
public class DefaultPageTimePeriodController implements PageTimePeriodController {

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private ExecutionPauser executionPauser;

    @Override
    public List<String> getTimePeriodValues(WebDriver driver, Pages page) {
//        log.debug("Get time period values for page {} started", page.getName());
//        openTimePeriodList(driver, page);
//        executionPauser.pause();
//        List<String> result = new ArrayList<>();
//        List<WebElement> valueElements = driver.findElements(By.xpath("//div[@class='dropdown-menu-item']"));
//        for(WebElement valueElement : valueElements) {
//            if(!result.contains(valueElement.getText())) {
//                result.add(valueElement.getText());
//            }
//        }
//        closeTimePeriodList(driver, page);
//        log.debug("Get time period values for page {} completed. Result is: {}", page.getName(), result);
//        return result;

        return asList(
                "Today"
        );
    }

    @Override
    public void setTimePeriodValue(WebDriver driver, Pages page, String timePeriodValue) {
        log.debug("Set time period value to {} in page {} started", timePeriodValue, page.getName());
        WebElement timePeriodContainerElement = waitForAndGetElement(
                driver,
                By.xpath("//div[@class='dropdown-toggle dropdown-toggle--enabled report-header__datepicker-toggle report-header__datepicker-toggle--selected']")
        );
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

    private void closeTimePeriodList(WebDriver driver, Pages page) {
        log.debug("Close time period list in page {} started", page.getName());
        WebElement timePeriodContainerElement = waitForAndGetElement(
                driver,
                By.xpath("//div[@class='dropdown-toggle dropdown-toggle--enabled report-header__datepicker-toggle report-header__datepicker-toggle--selected']")
        );
        elementHighlighter.highlight(driver, timePeriodContainerElement);
        timePeriodContainerElement.click();
        log.debug("Close time period list in page {} completed", page.getName());
    }

    private String getCurrentTimePeriodValue(WebDriver driver, Pages page) {
        log.debug("Get current time period value in page {} started", page.getName());
        WebElement timePeriodValueElement = waitForAndGetElement(
                driver,
                By.xpath("//div[@class='datepicker__dropdown-toggle__title']")
        );
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
            WebElement timePeriodTextElement = waitForAndGetElement(
                    driver,
                    By.xpath("//div[@class='datepicker__dropdown-toggle__title']")
            );
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
        WebElement timePeriodValueElement = waitForAndGetElement(
                driver,
                By.xpath(xpath)
        );
        elementHighlighter.highlight(driver, timePeriodValueElement);
        executionPauser.pause();
        timePeriodValueElement.click();
        if(timePeriodValue.equals("Custom Dates")) {
            WebElement applyButton = waitForAndGetElement(
                    driver,
                    By.xpath("//div[@class='button custom-date-range__submit-button custom-date-range__submit-button_active' and text()='Apply']")
            );
            elementHighlighter.highlight(driver, applyButton);
            executionPauser.pause();
            applyButton.click();
        }
        log.debug("Select time period value {} in page {} completed", timePeriodValue, page.getName());
    }

    private WebElement waitForAndGetElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }
}
