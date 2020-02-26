package com.example;

import lombok.Data;
import org.openqa.selenium.WebDriver;

@Data
public abstract class PostObservationAction {

    abstract void execute(WebDriver driver);
}
