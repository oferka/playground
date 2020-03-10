package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.openqa.selenium.By;

import java.util.List;

@AllArgsConstructor
public class WidgetBodyStateInstructions {

    @Getter
    private String name;

    @Getter
    private String indicatorXPath;

    @Getter
    private List<By> locators;

    @Getter
    private boolean success;

    @Getter
    private boolean unexpected;
}
