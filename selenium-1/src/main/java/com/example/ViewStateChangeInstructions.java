package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewStateChangeInstructions {

    private List<ViewState> viewStates;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ViewState {
        private String name;
        private By controlLocator;
        private By viewLocator;
    }
}
