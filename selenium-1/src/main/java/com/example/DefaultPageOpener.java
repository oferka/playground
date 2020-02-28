package com.example;

import lombok.extern.slf4j.Slf4j;
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
    private ElementHighlighter elementHighlighter;

    @Override
    public void open(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.debug("Open {} page started", page.getName());
        if(!isPageDisplayed(driver, page)) {
            log.debug("Page {} is currently not displayed. Going to use navigation bar to open it", pageName);
            WebElement navigationElement = getNavigationElement(driver, page);
            elementHighlighter.highlight(driver, navigationElement);
            navigationElement.click();
            new WebDriverWait(driver, pageOpenerConfiguration.getTitleChangeTimeoutInSeconds()).until(ExpectedConditions.titleContains(page.getPageTitleContains()));
            highlightPageHeader(driver, page);
        }
        else {
            log.debug("Page {} is currently displayed", pageName);
            elementHighlighter.highlight(driver, page.getPageHeaderRetriever().retrieve(driver));
        }
        log.debug("Open {} page completed", pageName);
    }

    private WebElement getNavigationElement(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.debug("Get navigation element for page {} started", pageName);
        NavigationBarElements navigationBarElement = page.getNavigationBarElement();
        NavigationElementRetriever navigationElementRetriever = navigationBarElement.getNavigationElementRetriever();
        if (!navigationElementRetriever.isDisplayed(driver)) {
            NavigationBarElementGroups navigationBarElementGroup = page.getNavigationBarElement().getNavigationBarElementGroup();
            log.debug("Navigation element {} is not currently displayed. Going to expand navigation elements group {}", navigationBarElement.getName(), navigationBarElementGroup.getName());
            expandNavigationElement(driver, navigationBarElementGroup);
        }
        WebElement result = navigationElementRetriever.retrieve(driver);
        log.debug("Get navigation element for page {} completed. Element text is {}", pageName, result.getText());
        return result;
    }

    private void expandNavigationElement(WebDriver driver, NavigationBarElementGroups navigationBarElementGroup) {
        String navigationBarElementGroupName = navigationBarElementGroup.getName();
        log.debug("Expand {} navigation element started", navigationBarElementGroupName);
        WebElement navigationElement = navigationBarElementGroup.getNavigationElementRetriever().retrieve(driver);
        elementHighlighter.highlight(driver, navigationElement);
        navigationElement.click();
        log.debug("Expand {} navigation element completed", navigationBarElementGroupName);
    }

    private void highlightPageHeader(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.debug("Highlight page header for page {} started", pageName);
        PageHeaderRetriever pageHeaderRetriever = page.getPageHeaderRetriever();
        WebElement pageHeaderElement = pageHeaderRetriever.retrieve(driver);
        elementHighlighter.highlight(driver, pageHeaderElement);
        log.debug("Highlight page header for page {} completed", pageName);
    }

    private boolean isPageDisplayed(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.debug("Check if page {} is displayed started", pageName);
        boolean result = page.getPageHeaderRetriever().isDisplayed(driver);
        log.debug("Check if page {} is displayed completed. Result is {}", pageName, result);
        return result;
    }
}
