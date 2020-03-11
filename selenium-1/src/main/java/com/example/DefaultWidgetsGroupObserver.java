package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultWidgetsGroupObserver implements WidgetsGroupObserver {

    @Autowired
    private WidgetObserver widgetObserver;

    @Autowired
    private ExecutionPauser executionPauser;

    @Autowired
    private PageScroller pageScroller;

    @Override
    public void observe(WebDriver driver, WidgetGroups widgetGroup) {
        log.debug("Observe widgets group '{}' started", widgetGroup.getName());
        List<Widgets> widgets = widgetGroup.getWidgets();
        for(Widgets widget : widgets) {
            widgetObserver.observe(driver, widget);
            executionPauser.pause();
        }
        PageScrollInstructions pageScrollInstructions = widgetGroup.getPageScrollInstructions();
        if(pageScrollInstructions != null) {
            pageScroller.scroll(driver, pageScrollInstructions);
        }
        log.debug("Observe widgets group '{}' completed", widgetGroup.getName());
    }
}
