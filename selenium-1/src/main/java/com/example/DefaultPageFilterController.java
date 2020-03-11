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

@Service
@Slf4j
public class DefaultPageFilterController implements PageFilterController {

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private ExecutionPauser executionPauser;

    @Override
    public List<String> getFilterValues(WebDriver driver, Pages page) {
        log.info("Get filter values for page '{}' started", page.getName());
        List<String> result = getFilterValuesFromPage(driver, page);
        retainOnlyIncludedFilters(result, page);
        removeAllExcludedFilters(result, page);
        log.info("Get filter values for page '{}' completed. Result is: '{}'", page.getName(), result);
        return result;
    }

    private List<String> getFilterValuesFromPage(WebDriver driver, Pages page) {
        log.info("Get filter values from page '{}' started", page.getName());
        openFilterList(driver, page);
        executionPauser.pause();
        List<String> result = new ArrayList<>();
        List<WebElement> valueElements = driver.findElements(By.xpath("//span[@class='suggestion-highlight-container']"));
        for(WebElement valueElement : valueElements) {
            if(!result.contains(valueElement.getText())) {
                result.add(valueElement.getText());
            }
        }
        closeFilterList(driver, page);
        log.info("Get filter values from page '{}' completed. Result is: '{}'", page.getName(), result);
        return result;
    }

    private void retainOnlyIncludedFilters(List<String> filters, Pages page) {
        log.info("Retain only included filter values for page '{}' started", page.getName());
        List<String> include = page.getFilterInstructions().getInclude();
        if((include != null) && (!include.isEmpty())) {
            filters.retainAll(include);
        }
        log.info("Retain only included filter values for page '{}' completed. Result is: '{}'", page.getName(), filters);
    }

    private void removeAllExcludedFilters(List<String> filters, Pages page) {
        log.info("Remove all excluded filter values for page '{}' started", page.getName());
        List<String> exclude = page.getFilterInstructions().getExclude();
        if((exclude != null) && (!exclude.isEmpty())) {
            filters.removeAll(exclude);
        }
        log.info("Remove all excluded filter values for page '{}' completed. Result is: '{}'", page.getName(), filters);
    }

    @Override
    public void setFilterValue(WebDriver driver, Pages page, String filterValue) {
        log.info("Set filter value to '{}' on page '{}' started", filterValue, page.getName());
        WebElement filterContainerElement = waitForAndGetElement(
                driver,
                By.xpath("//div[@class='dropdown-toggle dropdown-toggle--enabled report-header__dropdown-toggle report-header__dropdown-toggle--selected']")
        );
        elementHighlighter.highlight(driver, filterContainerElement);
        String currentFilterValue = getCurrentFilterValue(driver, page);
        if(currentFilterValue.equals(filterValue)) {
            log.info("Filter value '{}' is already selected on page '{}'", filterValue, page.getName());
        }
        else {
            openFilterList(driver, page);
            executionPauser.pause();
            selectFilterValue(driver, page, filterValue);
            executionPauser.pause();
        }
        log.info("Set filter value to '{}' on page '{}' completed", filterValue, page.getName());
    }

    private void closeFilterList(WebDriver driver, Pages page) {
        log.info("Close filter list in page '{}' started", page.getName());
        WebElement filterContainerElement = waitForAndGetElement(
                driver,
                By.xpath("//div[@class='dropdown-toggle dropdown-toggle--enabled report-header__dropdown-toggle report-header__dropdown-toggle--selected']")
        );
        elementHighlighter.highlight(driver, filterContainerElement);
        filterContainerElement.click();
        log.info("Close filter list in page '{}' completed", page.getName());
    }

    private String getCurrentFilterValue(WebDriver driver, Pages page) {
        log.info("Get current filter value in page '{}' started", page.getName());
        WebElement filterValueElement = waitForAndGetElement(
                driver,
                By.xpath("//span[@class='suggestion-highlight-container']")
        );
        String result = filterValueElement.getText();
        log.info("Get current filter value in page '{}' completed. Result is '{}'", page.getName(), result);
        return result;
    }

    private void openFilterList(WebDriver driver, Pages page) {
        log.info("Open filter list in page '{}' started", page.getName());
        if(isFilterListDisplayed(driver, page)) {
            log.debug("Filter list in page '{}' is already displayed", page.getName());
        }
        else {
            WebElement filterTextElement = waitForAndGetElement(
                    driver,
                    By.xpath("//span[@class='suggestion-highlight-container']/ancestor::div[@class='overflow-tooltip']")
            );
            elementHighlighter.highlight(driver, filterTextElement);
            filterTextElement.click();
        }
        log.info("Open filter list in page '{}' completed", page.getName());
    }

    private boolean isFilterListDisplayed(WebDriver driver, Pages page) {
        log.info("Check if filter list is displayed in page '{}' started", page.getName());
        boolean result = false;
        List<WebElement> valueElements = driver.findElements(By.xpath("//span[@class='suggestion-highlight-container']"));
        if(valueElements.size() > 1) {
            result = true;
        }
        log.info("Check if filter list is displayed in page '{}' completed. Result is '{}'", page.getName(), result);
        return result;
    }

    private void selectFilterValue(WebDriver driver, Pages page, String filterValue) {
        log.info("Select filter value '{}' in page '{}' started", filterValue, page.getName());
        String xpath = format("//span[@class='suggestion-highlight-container' and text()='%s']", filterValue);
        WebElement filterValueElement = waitForAndGetElement(
                driver,
                By.xpath(xpath)
        );
        elementHighlighter.highlight(driver, filterValueElement);
        executionPauser.pause();
        filterValueElement.click();
        log.info("Select filter value '{}' in page '{}' completed", filterValue, page.getName());
    }

    private WebElement waitForAndGetElement(WebDriver driver, By locator) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        return driver.findElement(locator);
    }
}
