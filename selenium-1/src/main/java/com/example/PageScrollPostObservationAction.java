package com.example;

import com.example.PageScroller.ScrollDirections;
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

    private PageScroller pageScroller;

    public PageScrollPostObservationAction(ScrollDirections direction, int pixels) {
        this(direction, pixels, new DefaultPageScroller());
    }

    @Override
    void execute(WebDriver driver) {
        pageScroller.scroll(driver, direction, pixels);
    }
}
