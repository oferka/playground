package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
public class PageScrollInstructions {

    @Getter
    private ScrollDirections direction;

    @Getter
    private int pixels;

    @Getter
    private ScrollSpeeds speed;

    @ToString
    @AllArgsConstructor
    enum ScrollDirections {
        UP ("Up"),
        DOWN("Down");

        @Getter
        private String name;
    }

    @ToString
    @AllArgsConstructor
    enum ScrollSpeeds {
        FAST ("Fast", 100),
        MEDIUM("Medium", 250),
        SLOW("Slow", 1000);

        @Getter
        private String name;

        @Getter
        private long delay;
    }
}
