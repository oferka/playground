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
public class DefaultWidgetBodyObserver implements WidgetBodyObserver {

    @Autowired
    private WidgetBodyRetriever widgetBodyRetriever;

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private ElementMouseHoverer elementMouseHoverer;

    @Autowired
    private ExecutionPauser executionPauser;

    @Override
    public WidgetBodyState observe(WebDriver driver, Widgets widget) {
        log.info("Observe widget body for widget '{}' started", widget.getName());
        try {
            widgetBodyRetriever.retrieve(driver, generateMultiStateBodyLocator(widget));
            log.info("Body element for widget '{}' was retrieved", widget.getName());
        }
        catch (Exception e) {
            log.info("Failed to retrieve body element for widget '{}'", widget.getName());
        }
        WidgetBodyState widgetBodyState = getDisplayedState(driver, widget);
        List<By> bodyLocators = widgetBodyState.getBodyElementLocators();
        for(By bodyLocator : bodyLocators) {
            if(widgetBodyRetriever.isDisplayed(driver, bodyLocator)) {
                WebElement bodyElement = widgetBodyRetriever.retrieve(driver, bodyLocator);
                elementHighlighter.highlight(driver, bodyElement);
                elementMouseHoverer.hover(driver, bodyElement);
                executionPauser.pause();
            }
        }
        log.info("Observe widget body for widget '{}' completed. Result is: '{}'", widget.getName(), widgetBodyState.getName());
        return widgetBodyState;
    }

    private By generateMultiStateBodyLocator(Widgets widget) {
        log.info("Generate multi state body locator for widget '{}' started", widget.getName());
        WidgetBodyInstructions widgetBodyInstructions = widget.getWidgetBodyInstructions();
        List<WidgetBodyState> widgetBodyStateInstructions = widgetBodyInstructions.getWidgetBodyStateInstructions();
        String xpath = "";
        for(WidgetBodyState instructions : widgetBodyStateInstructions) {
            String indicatorXPath = instructions.getIndicatorXPath();
            if((!instructions.isUnexpected()) && (indicatorXPath != null)) {
                xpath = xpath.concat(indicatorXPath);
                xpath = xpath.concat(" | ");
            }
        }
        if(xpath.endsWith(" | ")) {
            xpath = xpath.substring(0, xpath.lastIndexOf( " | "));
        }
        log.info("Generate multi state body locator for widget '{}' completed. Result is '{}'", widget.getName(), xpath);
        return By.xpath(xpath);
    }

    private WidgetBodyState getDisplayedState(WebDriver driver, Widgets widget) {
        log.info("Get displayed state for widget '{}' started", widget.getName());
        WidgetBodyState result = null;
        WidgetBodyInstructions widgetBodyInstructions = widget.getWidgetBodyInstructions();
        List<WidgetBodyState> widgetBodyStateInstructions = widgetBodyInstructions.getWidgetBodyStateInstructions();
        for(WidgetBodyState instructions : widgetBodyStateInstructions) {
            String indicatorXPath = instructions.getIndicatorXPath();
            if(indicatorXPath != null) {
                By locator = By.xpath(indicatorXPath);
                if (widgetBodyRetriever.isDisplayed(driver, locator)) {
                    result = instructions;
                    break;
                }
            }
        }
        log.info("Get displayed state for widget '{}' completed. Result is '{}'", widget.getName(), result.getName());
        return result;
    }
}
