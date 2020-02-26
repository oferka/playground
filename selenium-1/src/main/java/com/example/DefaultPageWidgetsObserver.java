package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultPageWidgetsObserver implements PageWidgetsObserver {

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Override
    public void observe(WebDriver driver, Pages insightsPage) {
        log.info("Observe {} insights page started", insightsPage.getName());
        List<ObservedWidgetsGroup> observedWidgetsGroups = insightsPage.getObservedWidgetsGroups();
        for(ObservedWidgetsGroup observedWidgetsGroup : observedWidgetsGroups) {
            observe(driver, observedWidgetsGroup);
        }
        log.info("Observe {} insights page completed", insightsPage.getName());
    }

    private void observe(WebDriver driver, ObservedWidgetsGroup observedWidgetsGroup) {
        log.info("Observe insights widgets group started");
        List<Widget> widgets = observedWidgetsGroup.getWidgets();
        for(Widget widget : widgets) {
            observe(driver, widget);
        }
        PostObservationAction postObservationAction = observedWidgetsGroup.getPostObservationAction();
        if(postObservationAction != null) {
            postObservationAction.execute(driver);
        }
        log.info("Observe insights widgets group completed");
    }

    private void observe(WebDriver driver, Widget widget) {
        log.info("Observe {} insights widget started", widget.getName());
        List<WidgetTitleRetriever> widgetTitleRetrievers = widget.getWidgetTitleRetrievers();
        for(WidgetTitleRetriever widgetTitleRetriever : widgetTitleRetrievers) {
            WebElement widgetTitleElement = widgetTitleRetriever.retrieve(driver);
            log.debug("Found widget title element: {}", widgetTitleElement);
            elementHighlighter.highlight(driver, widgetTitleElement);
        }
        log.info("Observe {} insights widget completed", widget.getName());
    }
}
