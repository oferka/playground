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
    public void observe(WebDriver driver, Pages page) {
        String pageName = page.getName();
        log.debug("Observe {} page started", pageName);
        List<ObservedWidgetsGroup> observedWidgetsGroups = page.getObservedWidgetsGroups();
        for(ObservedWidgetsGroup observedWidgetsGroup : observedWidgetsGroups) {
            observe(driver, observedWidgetsGroup);
        }
        log.debug("Observe {} page completed", pageName);
    }

    private void observe(WebDriver driver, ObservedWidgetsGroup observedWidgetsGroup) {
        log.debug("Observe widgets group started");
        List<Widget> widgets = observedWidgetsGroup.getWidgets();
        for(Widget widget : widgets) {
            observe(driver, widget);
        }
        PostObservationAction postObservationAction = observedWidgetsGroup.getPostObservationAction();
        if(postObservationAction != null) {
            postObservationAction.execute(driver);
        }
        log.debug("Observe widgets group completed");
    }

    private void observe(WebDriver driver, Widget widget) {
        log.debug("Observe {} widget started", widget.getName());
        List<WidgetTitleRetriever> widgetTitleRetrievers = widget.getWidgetTitleRetrievers();
        for(WidgetTitleRetriever widgetTitleRetriever : widgetTitleRetrievers) {
            WebElement widgetTitleElement = widgetTitleRetriever.retrieve(driver);
            log.debug("Found widget title element: {}", widgetTitleElement);
            elementHighlighter.highlight(driver, widgetTitleElement);
        }
        log.debug("Observe {} widget completed", widget.getName());
    }
}
