package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultPageWigetsObserver implements PageWigetsObserver {

    @Autowired
    private WidgetObserver widgetObserver;

    @Override
    public void observe(WebDriver driver, ObservedWidgetsGroup observedWidgetsGroup) {
        log.debug("Observe widgets group started");
        List<Widget> widgets = observedWidgetsGroup.getWidgets();
        for(Widget widget : widgets) {
            widgetObserver.observe(driver, widget);
        }
        PostObservationAction postObservationAction = observedWidgetsGroup.getPostObservationAction();
        if(postObservationAction != null) {
            postObservationAction.execute(driver);
        }
        log.debug("Observe widgets group completed");
    }
}
