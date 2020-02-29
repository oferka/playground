package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class DefaultPageWigetsObserver implements PageWigetsObserver {

    @Autowired
    private WidgetObserver widgetObserver;

    @Autowired
    private ExecutionPauser executionPauser;

    @Override
    public void observe(WebDriver driver, ObservedWidgetsGroup observedWidgetsGroup) {
        log.debug("Observe widgets group started");
        List<Widget> widgets = observedWidgetsGroup.getWidgets();
        for(Widget widget : widgets) {
            widgetObserver.observe(driver, widget);
            executionPauser.pause(Duration.ofSeconds(2));
        }
        PostObservationAction postObservationAction = observedWidgetsGroup.getPostObservationAction();
        if(postObservationAction != null) {
            postObservationAction.execute(driver);
        }
        log.debug("Observe widgets group completed");
    }
}
