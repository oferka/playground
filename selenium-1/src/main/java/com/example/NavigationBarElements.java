package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static com.example.NavigationBarElementGroups.APPS_NAVIGATION_ELEMET_GROUP;
import static com.example.NavigationBarElementGroups.TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP;

@ToString
@AllArgsConstructor
public enum NavigationBarElements {

    OVERVIEW_NAVIGATION_ELEMET(
            "Overview",
            new FirstLevelNavigationElementRetriever("overview"),
            null
    ),
    APPS_OVERVIEW_NAVIGATION_ELEMET(
            "Apps Overview",
            new SecondLevelNavigationElementRetriever("Apps Overview"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    SMART_WALK_THRUS_NAVIGATION_ELEMET(
            "SmartWalkThrus",
            new SecondLevelNavigationElementRetriever("Smart Walk-Thrus"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    WALK_THRUS_NAVIGATION_ELEMET(
            "WalkThrus",
            new SecondLevelNavigationElementRetriever("Walk-Thrus"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    ONBOARDING_NAVIGATION_ELEMET(
            "Onboarding",
            new SecondLevelNavigationElementRetriever("Onboarding"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    SHOUT_OUTS_NAVIGATION_ELEMET(
            "ShoutOuts",
            new SecondLevelNavigationElementRetriever("ShoutOuts"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    LAUNCHERS_NAVIGATION_ELEMET(
            "Launchers",
            new SecondLevelNavigationElementRetriever("Launchers"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    RESOURCES_NAVIGATION_ELEMET(
            "Resources",
            new SecondLevelNavigationElementRetriever("Resources"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    SHUTTLES_NAVIGATION_ELEMET(
            "Shuttles",
            new SecondLevelNavigationElementRetriever("Shuttles"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    SURVEYS_NAVIGATION_ELEMET(
            "Surveys",
            new SecondLevelNavigationElementRetriever("Surveys"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    SMART_TIPS_NAVIGATION_ELEMET(
            "SmartTips",
            new SecondLevelNavigationElementRetriever("SmartTips"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    MENU_AND_SEARCH_NAVIGATION_ELEMET(
            "MenuAndSearch",
            new SecondLevelNavigationElementRetriever("Menu & Search"),
            APPS_NAVIGATION_ELEMET_GROUP
    ),
    USERS_NAVIGATION_ELEMET(
            "Users",
            new FirstLevelNavigationElementRetriever("users"),
            null
    ),
    SESSION_PLAYBACK_NAVIGATION_ELEMET(
            "Session Playback",
            new FirstLevelNavigationElementRetriever("session playback"),
            null
    ),
    FEATURES_NAVIGATION_ELEMET(
            "Features",
            new FirstLevelNavigationElementRetriever("features"),
            null
    ),
    FUNNELS_NAVIGATION_ELEMET(
            "Funnels",
            new FirstLevelNavigationElementRetriever("funnels"),
            null
    ),
    TRACKED_EVENTS_ANALYTICS_NAVIGATION_ELEMET(
            "TrackedEventsAnalytics",
            new SecondLevelNavigationElementRetriever("Analytics"),
            TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP
    ),
    TRACKED_EVENTS_SETUP_NAVIGATION_ELEMET(
            "Setup",
            new SecondLevelNavigationElementRetriever("Setup"),
            TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP
    ),
    REPORTS_NAVIGATION_ELEMET(
            "Reports",
            new FirstLevelNavigationElementRetriever("reports"),
            null
    );

    @Getter
    private String name;

    @Getter
    private NavigationElementRetriever navigationElementRetriever;

    @Getter
    private NavigationBarElementGroups navigationBarElementGroup;
}
