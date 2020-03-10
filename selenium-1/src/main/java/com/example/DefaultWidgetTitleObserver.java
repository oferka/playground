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
public class DefaultWidgetTitleObserver implements WidgetTitleObserver {

    @Autowired
    private WidgetTitleRetriever widgetTitleRetriever;

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private ElementMouseHoverer elementMouseHoverer;

    @Autowired
    private ExecutionPauser executionPauser;

    @Override
    public void observe(WebDriver driver, Widgets widget) {
        log.debug("Observe {} widget title started", widget.getName());
        List<By> titleLocators = widget.getTitleLocators();
        for(By titleLocator : titleLocators) {
            try {
                WebElement titleElement = widgetTitleRetriever.retrieve(driver, titleLocator);
                log.debug("Found widget title element");
                elementHighlighter.highlight(driver, titleElement);
                elementMouseHoverer.hover(driver, titleElement);
                executionPauser.pause();
            }
            catch (Exception e) {
                log.debug("Failed to find widget title element. Message is {}", e.getMessage());
            }
        }
        log.debug("Observe {} widget title completed", widget.getName());
    }
}
