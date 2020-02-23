package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum InsightsNavigationBarElements {

    OVERVIEW_NAVIGATION_ELEMET(
            "Overview",
            new InsightsFirstLevelNavigationElementRetriever("overview")
    ),
    APPS_OVERVIEW_NAVIGATION_ELEMET(
            "Apps Overview",
            new InsightsSecondLevelNavigationElementRetriever("Apps Overview")
    ),
    SMART_WALK_THRUS_NAVIGATION_ELEMET(
            "SmartWalkThrus",
            new InsightsSecondLevelNavigationElementRetriever("Smart Walk-Thrus")
    ),
    WALK_THRUS_NAVIGATION_ELEMET(
            "WalkThrus",
            new InsightsSecondLevelNavigationElementRetriever("Walk-Thrus")
    ),
    ONBOARDING_NAVIGATION_ELEMET(
            "Onboarding",
            new InsightsSecondLevelNavigationElementRetriever("Onboarding")
    ),
    SHOUT_OUTS_NAVIGATION_ELEMET(
            "ShoutOuts",
            new InsightsSecondLevelNavigationElementRetriever("ShoutOuts")
    ),
    LAUNCHERS_NAVIGATION_ELEMET(
            "Launchers",
            new InsightsSecondLevelNavigationElementRetriever("Launchers")
    ),
    RESOURCES_NAVIGATION_ELEMET(
            "Resources",
            new InsightsSecondLevelNavigationElementRetriever("Resources")
    ),
    SHUTTLES_NAVIGATION_ELEMET(
            "Shuttles",
            new InsightsSecondLevelNavigationElementRetriever("Shuttles")
    ),
    SURVEYS_NAVIGATION_ELEMET(
            "Surveys",
            new InsightsSecondLevelNavigationElementRetriever("Surveys")
    ),
    SMART_TIPS_NAVIGATION_ELEMET(
            "SmartTips",
            new InsightsSecondLevelNavigationElementRetriever("SmartTips")
    ),
    MENU_AND_SEARCH_NAVIGATION_ELEMET(
            "MenuAndSearch",
            new InsightsSecondLevelNavigationElementRetriever("Menu & Search")
    ),
    USERS_NAVIGATION_ELEMET(
            "Users",
            new InsightsFirstLevelNavigationElementRetriever("users")
    ),
    SESSION_PLAYBACK_NAVIGATION_ELEMET(
            "Session Playback",
            new InsightsFirstLevelNavigationElementRetriever("session playback")
    ),
    FEATURES_NAVIGATION_ELEMET(
            "Features",
            new InsightsFirstLevelNavigationElementRetriever("features")
    ),
    FUNNELS_NAVIGATION_ELEMET(
            "Funnels",
            new InsightsFirstLevelNavigationElementRetriever("funnels")
    ),
    TRACKED_EVENTS_ANALYTICS_NAVIGATION_ELEMET(
            "TrackedEventsAnalytics",
            new InsightsSecondLevelNavigationElementRetriever("Analytics")
    ),
    TRACKED_EVENTS_SETUP_NAVIGATION_ELEMET(
            "Setup",
            new InsightsSecondLevelNavigationElementRetriever("Setup")
    ),
    REPORTS_NAVIGATION_ELEMET(
            "Reports",
            new InsightsFirstLevelNavigationElementRetriever("reports")
    );

    @Getter
    private String name;

    @Getter
    private NavigationElementRetriever navigationElementRetriever;
}
