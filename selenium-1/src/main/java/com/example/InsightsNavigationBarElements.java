package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static com.example.InsightsNavigationBarElements.InsightsNavigationBarElementTypes.NAVIGATION_ELEMET;
import static com.example.InsightsNavigationBarElements.InsightsNavigationBarElementTypes.NAVIGATION_ELEMET_GROUP;

@ToString
@AllArgsConstructor
public enum InsightsNavigationBarElements {

    OVERVIEW_NAVIGATION_ELEMET(
            "Overview",
            new InsightsFirstLevelNavigationElementRetriever("overview"),
            NAVIGATION_ELEMET
    ),
    APPS_NAVIGATION_ELEMET_GROUP(
            "Apps",
            new InsightsFirstLevelNavigationElementRetriever("apps"),
            NAVIGATION_ELEMET_GROUP
    ),
    APPS_OVERVIEW_NAVIGATION_ELEMET(
            "Apps Overview",
            new InsightsSecondLevelNavigationElementRetriever("Apps Overview"),
            NAVIGATION_ELEMET
    ),
    SMART_WALK_THRUS_NAVIGATION_ELEMET(
            "SmartWalkThrus",
            new InsightsSecondLevelNavigationElementRetriever("Smart Walk-Thrus"),
            NAVIGATION_ELEMET
    ),
    WALK_THRUS_NAVIGATION_ELEMET(
            "WalkThrus",
            new InsightsSecondLevelNavigationElementRetriever("Walk-Thrus"),
            NAVIGATION_ELEMET
    ),
    ONBOARDING_NAVIGATION_ELEMET(
            "Onboarding",
            new InsightsSecondLevelNavigationElementRetriever("Onboarding"),
            NAVIGATION_ELEMET
    ),
    SHOUT_OUTS_NAVIGATION_ELEMET(
            "ShoutOuts",
            new InsightsSecondLevelNavigationElementRetriever("ShoutOuts"),
            NAVIGATION_ELEMET
    ),
    LAUNCHERS_NAVIGATION_ELEMET(
            "Launchers",
            new InsightsSecondLevelNavigationElementRetriever("Launchers"),
            NAVIGATION_ELEMET
    ),
    RESOURCES_NAVIGATION_ELEMET(
            "Resources",
            new InsightsSecondLevelNavigationElementRetriever("Resources"),
            NAVIGATION_ELEMET
    ),
    SHUTTLES_NAVIGATION_ELEMET(
            "Shuttles",
            new InsightsSecondLevelNavigationElementRetriever("Shuttles"),
            NAVIGATION_ELEMET
    ),
    SURVEYS_NAVIGATION_ELEMET(
            "Surveys",
            new InsightsSecondLevelNavigationElementRetriever("Surveys"),
            NAVIGATION_ELEMET
    ),
    SMART_TIPS_NAVIGATION_ELEMET(
            "SmartTips",
            new InsightsSecondLevelNavigationElementRetriever("SmartTips"),
            NAVIGATION_ELEMET
    ),
    MENU_AND_SEARCH_NAVIGATION_ELEMET(
            "MenuAndSearch",
            new InsightsSecondLevelNavigationElementRetriever("Menu & Search"),
            NAVIGATION_ELEMET
    ),
    USERS_NAVIGATION_ELEMET(
            "Users",
            new InsightsFirstLevelNavigationElementRetriever("users"),
            NAVIGATION_ELEMET
    ),
    SESSION_PLAYBACK_NAVIGATION_ELEMET(
            "Session Playback",
            new InsightsFirstLevelNavigationElementRetriever("session playback"),
            NAVIGATION_ELEMET
    ),
    FEATURES_NAVIGATION_ELEMET(
            "Features",
            new InsightsFirstLevelNavigationElementRetriever("features"),
            NAVIGATION_ELEMET
    ),
    FUNNELS_NAVIGATION_ELEMET(
            "Funnels",
            new InsightsFirstLevelNavigationElementRetriever("funnels"),
            NAVIGATION_ELEMET
    ),
    TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP(
            "Tracked Events",
            new InsightsFirstLevelNavigationElementRetriever("tracked events"),
            NAVIGATION_ELEMET_GROUP
    ),
    TRACKED_EVENTS_ANALYTICS_NAVIGATION_ELEMET(
            "TrackedEventsAnalytics",
            new InsightsSecondLevelNavigationElementRetriever("Analytics"),
            NAVIGATION_ELEMET
    ),
    TRACKED_EVENTS_SETUP_NAVIGATION_ELEMET(
            "Setup",
            new InsightsSecondLevelNavigationElementRetriever("Setup"),
            NAVIGATION_ELEMET
    ),
    REPORTS_NAVIGATION_ELEMET(
            "Reports",
            new InsightsFirstLevelNavigationElementRetriever("reports"),
            NAVIGATION_ELEMET
    );

    @Getter
    private String name;

    @Getter
    private NavigationElementRetriever navigationElementRetriever;

    @Getter
    private InsightsNavigationBarElementTypes type;

    @ToString
    @AllArgsConstructor
    enum InsightsNavigationBarElementTypes {

        NAVIGATION_ELEMET("Navigation Element"),
        NAVIGATION_ELEMET_GROUP("Navigation Element Group");

        @Getter
        private String name;
    }
}
