package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultWidgetObserver implements WidgetObserver {

    @Autowired
    private WidgetBorderRetriever widgetBorderRetriever;

    @Autowired
    private WidgetTitleRetriever widgetTitleRetriever;

    @Autowired
    private WidgetBodyRetriever widgetBodyRetriever;

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private ExecutionPauser executionPauser;

    @Autowired
    private ViewStateChangeExecutor viewStateChangeExecutor;

    @Override
    public void observe(WebDriver driver, Widgets widget) {
        log.debug("Observe {} widget started", widget.getName());
        observeWidgetBorder(driver, widget);
        executionPauser.pause();
        observeWidgetTitles(driver, widget);
        executionPauser.pause();
        observeWidgetBody(driver, widget);
        executionPauser.pause();
        executeViewStateChangeInstructions(driver, widget);
        log.debug("Observe {} widget completed", widget.getName());
    }

    private void observeWidgetBorder(WebDriver driver, Widgets widget) {
        log.debug("Observe {} widget border started", widget.getName());
        By borderLocator = widget.getBorderLocator();
        if(borderLocator != null) {
            WebElement borderElement = widgetBorderRetriever.retrieve(driver, borderLocator);
            log.debug("Found widget border element: {}", borderElement);
            elementHighlighter.highlight(driver, borderElement);
        }
        log.debug("Observe {} widget border completed", widget.getName());
    }

    private void observeWidgetTitles(WebDriver driver, Widgets widget) {
        log.debug("Observe {} widget title started", widget.getName());
        List<By> titleLocators = widget.getTitleLocators();
        for(By titleLocator : titleLocators) {
            WebElement titleElement = widgetTitleRetriever.retrieve(driver, titleLocator);
            log.debug("Found widget title element: {}", titleElement);
            elementHighlighter.highlight(driver, titleElement);
        }
        log.debug("Observe {} widget title completed", widget.getName());
    }

    private void observeWidgetBody(WebDriver driver, Widgets widget) {
        log.debug("Observe {} widget body started", widget.getName());
        List<By> bodyLocators = widget.getBodyLocators();
        for(By bodyLocator : bodyLocators) {
            WebElement bodyElement = widgetBodyRetriever.retrieve(driver, bodyLocator);
            log.debug("Found widget body element: {}", bodyElement);
            elementHighlighter.highlight(driver, bodyElement);
        }
        log.debug("Observe {} widget body completed", widget.getName());
    }

    private void executeViewStateChangeInstructions(WebDriver driver, Widgets widget) {
        log.debug("Execute view state change instructions for widget {} started", widget.getName());
        ViewStateChangeInstructions viewStateChangeInstructions = widget.getViewStateChangeInstructions();
        if(viewStateChangeInstructions != null) {
            viewStateChangeExecutor.execute(driver, viewStateChangeInstructions);
        }
        log.debug("Execute view state change instructions for widget {} completed", widget.getName());
    }
}
