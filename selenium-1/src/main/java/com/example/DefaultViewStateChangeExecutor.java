package com.example;

import com.example.ViewStateChangeInstructions.ViewState;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DefaultViewStateChangeExecutor implements ViewStateChangeExecutor {

    @Autowired
    private ElementRetriever elementRetriever;

    @Autowired
    private ElementHighlighter elementHighlighter;

    @Autowired
    private ExecutionPauser executionPauser;

    @Override
    public void execute(WebDriver driver, ViewStateChangeInstructions viewStateChangeInstructions) {
        log.debug("View state change executor started");
        List<ViewState> viewStates = viewStateChangeInstructions.getViewStates();
        for(ViewState viewState : viewStates) {
            execute(driver, viewState);
            executionPauser.pause();
        }
        log.debug("View state change executor completed");
    }

    private void execute(WebDriver driver, ViewState viewState) {
        log.debug("View state change for view state '{}' started", viewState.getName());
        By controlLocator = viewState.getControlLocator();
        WebElement controlElement = elementRetriever.retrieve(driver, controlLocator);
        elementHighlighter.highlight(driver, controlElement);
        controlElement.click();
        List<By> viewLocators = viewState.getViewLocators();
        for(By viewLocator : viewLocators) {
            if(elementRetriever.isDisplayed(driver, viewLocator)) {
                WebElement viewElement = elementRetriever.retrieve(driver, viewLocator);
                elementHighlighter.highlight(driver, viewElement);
                executionPauser.pause();
            }
        }
        log.debug("View state change for view state '{}' completed", viewState.getName());
    }
}
