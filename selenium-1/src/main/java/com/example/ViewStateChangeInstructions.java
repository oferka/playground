package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.List;

@AllArgsConstructor
public class ViewStateChangeInstructions {

    @Getter
    private List<ViewState> viewStates;

    @AllArgsConstructor
    public static class ViewState {

        @Getter
        private String name;

        @Getter
        private By controlLocator;

        @Getter
        private List<By> viewLocators;
    }
}
