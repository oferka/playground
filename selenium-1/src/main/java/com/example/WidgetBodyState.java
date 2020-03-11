package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.List;

@AllArgsConstructor
public class WidgetBodyState {

    @Getter
    private String name;

    @Getter
    private String indicatorXPath;

    @Getter
    private List<By> bodyElementLocators;

    @Getter
    private boolean success;

    @Getter
    private boolean unexpected;
}
