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
    private WidgetBorderObserver widgetBorderObserver;

    @Autowired
    private WidgetTitleObserver widgetTitleObserver;

    @Autowired
    private WidgetBodyObserver widgetBodyObserver;

    @Autowired
    private ExecutionPauser executionPauser;

    @Autowired
    private ViewStateChangeExecutor viewStateChangeExecutor;

    @Override
    public void observe(WebDriver driver, Widgets widget) {
        log.debug("Observe {} widget started", widget.getName());
        widgetBorderObserver.observe(driver, widget);
        executionPauser.pause();
        widgetTitleObserver.observe(driver, widget);
        executionPauser.pause();
        WidgetBodyStateInstructions widgetBodyStateInstructions = widgetBodyObserver.observe(driver, widget);
        if(widgetBodyStateInstructions.isSuccess()) {
            executionPauser.pause();
            executeViewStateChangeInstructions(driver, widget);
        }
        log.debug("Observe {} widget completed", widget.getName());
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
