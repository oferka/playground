package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@Data
@AllArgsConstructor
public class PageScrollInstructions {

    private ScrollDirections direction;

    private int pixels;

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
        MEDIUM("Medium", 500),
        SLOW("Slow", 1000);

        @Getter
        private String name;

        @Getter
        private long delay;
    }
}
