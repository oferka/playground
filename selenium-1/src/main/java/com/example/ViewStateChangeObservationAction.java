package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class ViewStateChangeObservationAction extends ObservationAction {

    private List<ViewState> viewStates;

    @Override
    public void execute(WebDriver driver) {
        log.debug("View state change observation action started");
        for(ViewState viewState : viewStates) {
            execute(driver, viewState);
        }
        log.debug("View state change observation action completed");
    }

    private void execute(WebDriver driver, ViewState viewState) {
        log.debug("View state change for view state {} started", viewState.getName());
        WebElement controlElement = viewState.getControlElementRetriver().retrieve(driver);
        controlElement.click();
        WebElement viewElement = viewState.getViewElementRetriver().retrieve(driver);
        log.debug("View state change for view state {} completed", viewState.getName());
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ViewState {
        private String name;
        private ElementRetriever controlElementRetriver;
        private ElementRetriever viewElementRetriver;
    }
}
