package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.WebDriver;

public interface PageScroller {

    void scroll(WebDriver driver, ScrollDirections direction, int pixels);

    @ToString
    @AllArgsConstructor
    public enum ScrollDirections {
        UP ("Up"),
        DOWN("Down");

        @Getter
        private String name;
    }
}
