package com.example;

import lombok.Data;
import org.openqa.selenium.WebDriver;

@Data
public abstract class ObservationAction {

    abstract void execute(WebDriver driver);
}
