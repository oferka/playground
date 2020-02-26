package com.example;

import com.example.PageScroller.ScrollDirections;
import com.example.PageScroller.ScrollSpeeds;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PageScrollPostObservationAction extends PostObservationAction {

    private ScrollDirections direction;

    private int pixels;

    private ScrollSpeeds speed;

    @Override
    void execute(WebDriver driver) {
        log.debug("Page scroll post observation action with direction {} pixels {} and speed {} started", direction.getName(), pixels, speed.getName());
        PageScroller pageScroller = new DefaultPageScroller();
        pageScroller.scroll(driver, direction, pixels, speed);
        log.debug("Page scroll post observation action with direction {} pixels {} and speed {} completed", direction.getName(), pixels, speed.getName());
    }
}
