package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultPageOpener implements PageOpener {

    @Autowired
    private PageOpenerConfiguration pageOpenerConfiguration;

    @Autowired
    private PageHeaderRetriever pageHeaderRetriever;

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private NavigationElementRetriever navigationElementRetriever;

    @Override
    public void open(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.info("Open page '{}' started", page.getName());
        if(!isPageDisplayed(driver, page)) {
            log.info("Page '{}' is currently not displayed. Going to use navigation bar to open it", pageName);
            WebElement navigationElement = getNavigationElement(driver, page);
            elementHighlighter.highlight(driver, navigationElement);
            navigationElement.click();
            new WebDriverWait(driver, pageOpenerConfiguration.getTitleChangeTimeoutInSeconds()).until(ExpectedConditions.titleContains(page.getPageTitleContains()));
            highlightPageHeader(driver, page);
        }
        else {
            log.info("Page '{}' is currently displayed", pageName);
            elementHighlighter.highlight(driver, pageHeaderRetriever.retrieve(driver, page.getPageHeaderLocator()));
        }
        log.info("Open page '{}' completed", pageName);
    }

    private WebElement getNavigationElement(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.info("Get navigation element for page '{}' started", pageName);
        NavigationBarElements navigationBarElement = page.getNavigationBarElement();
        By navigationElementLocator = page.getNavigationBarElement().getNavigationElementLocator();
        if (!navigationElementRetriever.isDisplayed(driver, navigationElementLocator)) {
            NavigationBarElementGroups navigationBarElementGroup = page.getNavigationBarElement().getNavigationBarElementGroup();
            log.info("Navigation element '{}' is not currently displayed. Going to expand navigation elements group '{}'", navigationBarElement.getName(), navigationBarElementGroup.getName());
            expandNavigationElement(driver, navigationBarElementGroup);
        }
        WebElement result = navigationElementRetriever.retrieve(driver, navigationElementLocator);
        log.info("Get navigation element for page '{}' completed. Element text is '{}'", pageName, result.getText());
        return result;
    }

    private void expandNavigationElement(WebDriver driver, NavigationBarElementGroups navigationBarElementGroup) {
        String navigationBarElementGroupName = navigationBarElementGroup.getName();
        log.info("Expand navigation element '{}' started", navigationBarElementGroupName);
        By navigationElementLocator = navigationBarElementGroup.getNavigationElementLocator();
        WebElement navigationElement = navigationElementRetriever.retrieve(driver, navigationElementLocator);
        elementHighlighter.highlight(driver, navigationElement);
        navigationElement.click();
        log.info("Expand navigation element '{}' completed", navigationBarElementGroupName);
    }

    private void highlightPageHeader(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.info("Highlight page header for page '{}' started", pageName);
        WebElement pageHeaderElement = pageHeaderRetriever.retrieve(driver, page.getPageHeaderLocator());
        elementHighlighter.highlight(driver, pageHeaderElement);
        log.info("Highlight page header for page '{}' completed", pageName);
    }

    private boolean isPageDisplayed(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.info("Check if page '{}' is displayed started", pageName);
        boolean result = pageHeaderRetriever.isDisplayed(driver, page.getPageHeaderLocator());
        log.info("Check if page '{}' is displayed completed. Result is '{}'", pageName, result);
        return result;
    }
}
