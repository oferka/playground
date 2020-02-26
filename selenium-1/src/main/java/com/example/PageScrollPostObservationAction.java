package com.example;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PageScrollPostObservationAction extends PostObservationAction {

    private ScrollDirections scrollDirection;

    private int scroll;

    @Override
    void execute(WebDriver driver) {
        log.debug("Page scroll {} by {} started", scrollDirection.getName(), scroll);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = format("window.scrollBy(0,%s)", getSignedScroll());
        js.executeScript(script, "");
        log.debug("Page scroll {} by {} completed", scrollDirection.getName(), scroll);
    }

    private int getSignedScroll() {
        int result = scroll;
        if(scrollDirection == ScrollDirections.UP) {
            result = scroll * -1;
        }
        return result;
    }

    @ToString
    @AllArgsConstructor
    public enum ScrollDirections {
        UP ("Up"),
        DOWN("Down");

        @Getter
        private String name;
    }
}
