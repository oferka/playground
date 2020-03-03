package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;

import static com.example.NavigationBarElementGroups.APPS_NAVIGATION_ELEMET_GROUP;
import static com.example.NavigationBarElementGroups.TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP;

@ToString
@AllArgsConstructor
public enum NavigationBarElements {

    OVERVIEW_NAVIGATION_ELEMET(
            "Overview",
            By.xpath("//*[@class='side-navbar__button__text' and text()='overview']"),
            null
    ),
    APPS_OVERVIEW_NAVIGATION_ELEMET(
            "Apps Overview",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Apps Overview']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    SMART_WALK_THRUS_NAVIGATION_ELEMET(
            "SmartWalkThrus",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Smart Walk-Thrus']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    WALK_THRUS_NAVIGATION_ELEMET(
            "WalkThrus",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Walk-Thrus']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    ONBOARDING_NAVIGATION_ELEMET(
            "Onboarding",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Onboarding']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    SHOUT_OUTS_NAVIGATION_ELEMET(
            "ShoutOuts",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='ShoutOuts']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    LAUNCHERS_NAVIGATION_ELEMET(
            "Launchers",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Launchers']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    RESOURCES_NAVIGATION_ELEMET(
            "Resources",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Resources']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    SHUTTLES_NAVIGATION_ELEMET(
            "Shuttles",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Shuttles']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    SURVEYS_NAVIGATION_ELEMET(
            "Surveys",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Surveys']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    SMART_TIPS_NAVIGATION_ELEMET(
            "SmartTips",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='SmartTips']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    MENU_AND_SEARCH_NAVIGATION_ELEMET(
            "MenuAndSearch",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Menu & Search']"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    USERS_NAVIGATION_ELEMET(
            "Users",
            By.xpath("//*[@class='side-navbar__button__text' and text()='users']"),
            null
    ),
    SESSION_PLAYBACK_NAVIGATION_ELEMET(
            "Session Playback",
            By.xpath("//*[@class='side-navbar__button__text' and text()='session playback']"),
            null
    ),
    FEATURES_NAVIGATION_ELEMET(
            "Features",
            By.xpath("//*[@class='side-navbar__button__text' and text()='features']"),
            null
    ),
    FUNNELS_NAVIGATION_ELEMET(
            "Funnels",
            By.xpath("//*[@class='side-navbar__button__text' and text()='funnels']"),
            null
    ),
    TRACKED_EVENTS_ANALYTICS_NAVIGATION_ELEMET(
            "TrackedEventsAnalytics",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Analytics']"),
            TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP
    ),
    TRACKED_EVENTS_SETUP_NAVIGATION_ELEMET(
            "Setup",
            By.xpath("//*[@class='side-navbar__sub-item-button__text' and text()='Setup']"),
            TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP
    ),
    REPORTS_NAVIGATION_ELEMET(
            "Reports",
            By.xpath("//*[@class='side-navbar__button__text' and text()='reports']"),
            null
    );

    @Getter
    private String name;

    @Getter
    private By navigationElementLocator;

    @Getter
    private NavigationBarElementGroups navigationBarElementGroup;
}
