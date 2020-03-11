package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class WidgetBodyInstructions {

    @Getter
    private List<WidgetBodyState> widgetBodyStateInstructions;
}
