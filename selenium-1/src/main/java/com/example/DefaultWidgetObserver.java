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
public class DefaultWidgetObserver implements WidgetObserver {

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private ExecutionPauser executionPauser;

    @Override
    public void observe(WebDriver driver, Widget widget) {
        log.debug("Observe {} widget started", widget.getName());
        observeWidgetBorder(driver, widget);
        executionPauser.pause(Duration.ofSeconds(1));
        observeWidgetTitles(driver, widget);
        executionPauser.pause(Duration.ofSeconds(1));
        observeWidgetBody(driver, widget);
        executionPauser.pause(Duration.ofSeconds(1));
        executeObservationActions(driver, widget);
        log.debug("Observe {} widget completed", widget.getName());
    }

    private void observeWidgetBorder(WebDriver driver, Widget widget) {
        log.debug("Observe {} widget border started", widget.getName());
        WidgetBorderRetriever widgetBorderRetriever = widget.getWidgetBorderRetriever();
        if(widgetBorderRetriever != null) {
            WebElement widgetBorderElement = widgetBorderRetriever.retrieve(driver);
            log.debug("Found widget border element: {}", widgetBorderElement);
            elementHighlighter.highlight(driver, widgetBorderElement);
        }
        log.debug("Observe {} widget border completed", widget.getName());
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

    private void executeObservationActions(WebDriver driver, Widget widget) {
        log.debug("Execute observation actions for widget {} started", widget.getName());
        List<ObservationAction> observationActions = widget.getObservationActions();
        for(ObservationAction observationAction : observationActions) {
            observationAction.execute(driver);
            executionPauser.pause(Duration.ofSeconds(1));
        }
        log.debug("Execute observation actions for widget {} completed", widget.getName());
    }
}
