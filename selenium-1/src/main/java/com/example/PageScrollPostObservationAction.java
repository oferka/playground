package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import static java.lang.String.format;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PageScrollPostObservationAction extends PostObservationAction {

    private int scroll;

    @Override
    void execute(WebDriver driver) {
        log.info("Page scroll started");
        JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        String script = format("window.scrollBy(0,%s)", scroll);
        js.executeScript(script, "");
        log.info("Page scroll completed");
    }
}
