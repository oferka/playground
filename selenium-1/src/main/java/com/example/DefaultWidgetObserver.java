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
    private ElementMouseHoverer elementMouseHoverer;

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
        WidgetBodyStateInstructions widgetBodyStateInstructions = observeWidgetBody(driver, widget);
        if(widgetBodyStateInstructions.isSuccess()) {
            executionPauser.pause();
            executeViewStateChangeInstructions(driver, widget);
        }
        log.debug("Observe {} widget completed", widget.getName());
    }

    private void observeWidgetBorder(WebDriver driver, Widgets widget) {
        log.debug("Observe {} widget border started", widget.getName());
        By borderLocator = widget.getBorderLocator();
        if(borderLocator != null) {
            WebElement borderElement = widgetBorderRetriever.retrieve(driver, borderLocator);
            log.debug("Found widget border element");
            elementHighlighter.highlight(driver, borderElement);
        }
        log.debug("Observe {} widget border completed", widget.getName());
    }

    private void observeWidgetTitles(WebDriver driver, Widgets widget) {
        log.debug("Observe {} widget title started", widget.getName());
        List<By> titleLocators = widget.getTitleLocators();
        for(By titleLocator : titleLocators) {
            WebElement titleElement = widgetTitleRetriever.retrieve(driver, titleLocator);
            log.debug("Found widget title element");
            elementHighlighter.highlight(driver, titleElement);
            elementMouseHoverer.hover(driver, titleElement);
            executionPauser.pause();
        }
        log.debug("Observe {} widget title completed", widget.getName());
    }

    private WidgetBodyStateInstructions observeWidgetBody(WebDriver driver, Widgets widget) {
        log.debug("Observe {} widget body started", widget.getName());
        WebElement displayedBodyElement = widgetBodyRetriever.retrieve(driver, generateMultiStateBodyLocator(widget));
        log.debug("Body element for widget {} was retrieved", widget.getName());
        WidgetBodyStateInstructions widgetBodyStateInstructions = getDisplayedState(driver, widget);
        List<By> bodyLocators = widgetBodyStateInstructions.getLocators();
        for(By bodyLocator : bodyLocators) {
            WebElement bodyElement = widgetBodyRetriever.retrieve(driver, bodyLocator);
            log.debug("Found widget body element");
            elementHighlighter.highlight(driver, bodyElement);
            elementMouseHoverer.hover(driver, bodyElement);
            executionPauser.pause();
        }
        log.debug("Observe {} widget body completed", widget.getName());
        return widgetBodyStateInstructions;
    }

    private By generateMultiStateBodyLocator(Widgets widget) {
        log.debug("Generate multi state body locator for widget {} started", widget.getName());
        WidgetBodyInstructions widgetBodyInstructions = widget.getWidgetBodyInstructions();
        List<WidgetBodyStateInstructions> widgetBodyStateInstructions = widgetBodyInstructions.getWidgetBodyStateInstructions();
        String xpath = "";
        for(WidgetBodyStateInstructions instructions : widgetBodyStateInstructions) {
            String indicatorXPath = instructions.getIndicatorXPath();
            if(indicatorXPath != null) {
                xpath = xpath.concat(indicatorXPath);
                xpath = xpath.concat(" | ");
            }
        }
        if(xpath.endsWith(" | ")) {
            xpath = xpath.substring(0, xpath.lastIndexOf( " | "));
        }
        log.debug("Generate multi state body locator for widget {} completed. Result is {}", widget.getName(), xpath);
        return By.xpath(xpath);
    }

    private WidgetBodyStateInstructions getDisplayedState(WebDriver driver, Widgets widget) {
        log.debug("Get displayed state for widget {} started", widget.getName());
        WidgetBodyStateInstructions result = null;
        WidgetBodyInstructions widgetBodyInstructions = widget.getWidgetBodyInstructions();
        List<WidgetBodyStateInstructions> widgetBodyStateInstructions = widgetBodyInstructions.getWidgetBodyStateInstructions();
        for(WidgetBodyStateInstructions instructions : widgetBodyStateInstructions) {
            List<By> locators = instructions.getLocators();
            if(!locators.isEmpty() && widgetBodyRetriever.isDisplayed(driver, locators.get(0))) {
                result = instructions;
                break;
            }
        }
        log.debug("Get displayed state for widget {} completed. Result is {}", widget.getName(), result.getName());
        return result;
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
