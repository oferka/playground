package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WidgetBodyInstructions {

    private List<WidgetBodyStateInstructions> widgetBodyStateInstructions;
}
