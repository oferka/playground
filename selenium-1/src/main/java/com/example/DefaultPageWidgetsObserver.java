package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class DefaultPageWidgetsObserver implements PageWidgetsObserver {

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private ExecutionPauser executionPauser;

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
        observeWidgetTitles(driver, widget);
        observeWidgetBody(driver, widget);
        executionPauser.pause(Duration.ofSeconds(2));
        log.debug("Observe {} widget completed", widget.getName());
    }

    private void observeWidgetTitles(WebDriver driver, Widget widget) {
        log.debug("Observe {} widget title started", widget.getName());
        List<WidgetTitleRetriever> widgetTitleRetrievers = widget.getWidgetTitleRetrievers();
        for(WidgetTitleRetriever widgetTitleRetriever : widgetTitleRetrievers) {
            WebElement widgetTitleElement = widgetTitleRetriever.retrieve(driver);
            log.debug("Found widget title element: {}", widgetTitleElement);
            elementHighlighter.highlight(driver, widgetTitleElement);
        }
        log.debug("Observe {} widget title completed", widget.getName());
    }

    private void observeWidgetBody(WebDriver driver, Widget widget) {
        log.debug("Observe {} widget body started", widget.getName());
        List<WidgetBodyRetriever> widgetBodyRetrievers = widget.getWidgetBodyRetrievers();
        for(WidgetBodyRetriever widgetBodyRetriever : widgetBodyRetrievers) {
            WebElement widgetBodyElement = widgetBodyRetriever.retrieve(driver);
            log.debug("Found widget body element: {}", widgetBodyElement);
            elementHighlighter.highlight(driver, widgetBodyElement);
        }
        log.debug("Observe {} widget body completed", widget.getName());
    }
}
