package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.time.Duration;

import static com.example.PageScroller.ScrollDirections.UP;
import static java.lang.String.format;

@Service
@Slf4j
public class DefaultPageScroller implements PageScroller {

    @Override
    public void scroll(WebDriver driver, ScrollDirections direction, int pixels, ScrollSpeeds speed) {
        log.debug("Page scroll {} by {} with speed {} started", direction.getName(), pixels, speed);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int numberOfSteps = 10;
        int signedPixels = getSignedScroll(direction, pixels);
        int stepSize = signedPixels/numberOfSteps;
        for(int i=0; i<numberOfSteps; i++) {
            scroll(driver, stepSize);
            new DefaultExecutionPauser().pause(Duration.ofMillis(speed.getDelay()));
        }
        stepSize = signedPixels%numberOfSteps;
        if(stepSize != 0) {
            String script = format("window.scrollBy(0,%s)", stepSize);
            js.executeScript(script, "");
        }
        log.debug("Page scroll {} by {} with speed {} completed", direction.getName(), pixels, speed);
    }

    private int getSignedScroll(ScrollDirections direction, int pixels) {
        int result = pixels;
        if(direction == UP) {
            result = pixels * -1;
        }
        return result;
    }

    private void scroll(WebDriver driver, int signedPixels) {
        log.debug("Scroll by {} started", signedPixels);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = format("window.scrollBy(0,%s)", signedPixels);
        js.executeScript(script, "");
        log.debug("Scroll by {} completed", signedPixels);
    }
}
