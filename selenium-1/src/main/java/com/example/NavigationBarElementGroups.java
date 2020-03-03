package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;

@ToString
@AllArgsConstructor
public enum NavigationBarElementGroups {

    APPS_NAVIGATION_ELEMET_GROUP(
            "Apps",
            By.xpath("//*[@class='side-navbar__button__text' and text()='apps']")
    ),
    TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP(
            "Tracked Events",
            By.xpath("//*[@class='side-navbar__button__text' and text()='tracked events']")
    );

    @Getter
    private String name;

    @Getter
    private By navigationElementLocator;
}
