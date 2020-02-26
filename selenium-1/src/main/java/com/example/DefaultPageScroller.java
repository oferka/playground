package com.example;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import static com.example.PageScroller.ScrollDirections.UP;
import static java.lang.String.format;

@Service
@Slf4j
public class DefaultPageScroller implements PageScroller {

    @Override
    public void scroll(WebDriver driver, ScrollDirections direction, int pixels) {
        log.debug("Page scroll {} by {} started", direction.getName(), pixels);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String script = format("window.scrollBy(0,%s)", getSignedScroll(direction, pixels));
        js.executeScript(script, "");
        log.debug("Page scroll {} by {} completed", direction.getName(), pixels);
    }

    private int getSignedScroll(ScrollDirections direction, int pixels) {
        int result = pixels;
        if(direction == UP) {
            result = pixels * -1;
        }
        return result;
    }
}
