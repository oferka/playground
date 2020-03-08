package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WidgetBodyStateInstructions {

    private String name;
    private List<By> locators;
}
