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
public class DefaultInsightsPageOpener implements InsightsPageOpener {

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Override
    public void open(WebDriver driver, InsightsPages insightsPage) {
        log.info("Open {} insights page started", insightsPage.getName());
        if(!isPageDisplayed(driver, insightsPage)) {
            log.debug("Insights page {} is currently not displayed. Going to use navigation bar to open it", insightsPage.getName());
            InsightsNavigationBarElements insightsNavigationBarElement = insightsPage.getNavigationBarElement();
            NavigationElementRetriever navigationElementRetriever = insightsNavigationBarElement.getNavigationElementRetriever();
            if (!navigationElementRetriever.isDisplayed(driver)) {
                InsightsNavigationBarElementGroups insightsNavigationBarElementGroup = insightsPage.getNavigationBarElement().getInsightsNavigationBarElementGroup();
                log.debug("Navigation element {} is not currently displayed. Going to expand navigation elements group {}", insightsNavigationBarElement.getName(), insightsNavigationBarElementGroup.getName());
                expandTopLevelNavigationElement(driver, insightsNavigationBarElementGroup);
            }
            WebElement navigationElement = navigationElementRetriever.retrieveNavigationElement(driver);
            elementHighlighter.highlight(driver, navigationElement);
            navigationElement.click();
            new WebDriverWait(driver, 30).until(ExpectedConditions.titleContains(insightsPage.getPageTitleContains()));
            highlightInsightsPageHeader(driver, insightsPage);
        }
        else {
            log.debug("Insights page {} is currently displayed", insightsPage.getName());
        }
        log.info("Open {} insights page completed", insightsPage.getName());
    }

    private void expandTopLevelNavigationElement(WebDriver driver, InsightsNavigationBarElementGroups insightsNavigationBarElementGroup) {
        log.info("Expand {} insights navigation bar element started", insightsNavigationBarElementGroup.getName());
        WebElement navigationElement = insightsNavigationBarElementGroup.getNavigationElementRetriever().retrieveNavigationElement(driver);
        elementHighlighter.highlight(driver, navigationElement);
        navigationElement.click();
        log.info("Expand {} insights navigation bar element completed", insightsNavigationBarElementGroup.getName());
    }

    private void highlightInsightsPageHeader(WebDriver driver, InsightsPages insightsPage) {
        PageHeaderRetriever pageHeaderRetriever = insightsPage.getPageHeaderRetriever();
        WebElement pageHeaderElement = pageHeaderRetriever.retrievePageHeader(driver);
        elementHighlighter.highlight(driver, pageHeaderElement);
    }

    private boolean isPageDisplayed(WebDriver driver, InsightsPages insightsPage) {
        return insightsPage.getPageHeaderRetriever().isDisplayed(driver);
    }
}
