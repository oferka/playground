package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DefaultWidgetBorderObserver implements WidgetBorderObserver {

    @Autowired
    private WidgetBorderRetriever widgetBorderRetriever;

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Override
    public void observe(WebDriver driver, Widgets widget) {
        log.debug("Observe '{}' widget border started", widget.getName());
        By borderLocator = widget.getBorderLocator();
        if(borderLocator != null) {
            WebElement borderElement = widgetBorderRetriever.retrieve(driver, borderLocator);
            elementHighlighter.highlight(driver, borderElement);
        }
        log.debug("Observe '{}' widget border completed", widget.getName());
    }
}
