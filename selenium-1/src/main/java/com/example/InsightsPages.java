package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static com.example.InsightsNavigationBarElements.*;

@ToString
@AllArgsConstructor
public enum InsightsPages {

    OVERVIEW_PAGE(
            "Overview",
            OVERVIEW_NAVIGATION_ELEMET,
            "Overview",
            new InsightsDefaultPageHeaderRetriever("Overview")
    ),
    APPS_OVERVIEW_PAGE(
            "Apps Overview",
            APPS_OVERVIEW_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Apps Overview")
    ),
    SMART_WALK_THRUS_PAGE(
            "SmartWalkThrus",
            SMART_WALK_THRUS_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Smart Walk-Thrus")
    ),
    WALK_THRUS_PAGE(
            "WalkThrus",
            WALK_THRUS_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Walk-Thrus")
    ),
    ONBOARDING_PAGE(
            "Onboarding",
            ONBOARDING_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Onboarding Tasks")
    ),
    SHOUT_OUTS_PAGE(
            "ShoutOuts",
            SHOUT_OUTS_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("ShoutOuts")
    ),
    LAUNCHERS_PAGE(
            "Launchers",
            LAUNCHERS_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Launchers")
    ),
    RESOURCES_PAGE(
            "Resources",
            RESOURCES_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Resources")
    ),
    SHUTTLES_PAGE(
            "Shuttles",
            SHUTTLES_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Shuttles")
    ),
    SURVEYS_PAGE(
            "Surveys",
            SURVEYS_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Surveys")
    ),
    SMART_TIPS_PAGE(
            "SmartTips",
            SMART_TIPS_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("SmartTips")
    ),
    MENU_AND_SEARCH_PAGE(
            "MenuAndSearch",
            MENU_AND_SEARCH_NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Menu & Search")
    ),
    USERS_PAGE(
            "Users",
            USERS_NAVIGATION_ELEMET,
            "Users", new InsightsDefaultPageHeaderRetriever("Users")),
    SESSION_PLAYBACK_PAGE(
            "Session Playback",
            SESSION_PLAYBACK_NAVIGATION_ELEMET,
            "Session Playback",
            new PageHeaderRetrieverByClassAndText("enable-sessions-empty-state__title", "Watch Session Playback")
    ),
    FEATURES_PAGE(
            "Features",
            FEATURES_NAVIGATION_ELEMET,
            "Features",
            new PageHeaderRetrieverByClassAndText("features-empty-state__title", "Track Feature Adoption")
    ),
    FUNNELS_PAGE(
            "Funnels",
            FUNNELS_NAVIGATION_ELEMET,
            "Funnels",
            new PageHeaderRetrieverByClassAndText("funnel-selection__title", "Funnels")
    ),
    TRACKED_EVENTS_ANALYTICS_PAGE(
            "TrackedEventsAnalytics",
            TRACKED_EVENTS_ANALYTICS_NAVIGATION_ELEMET,
            "Tracked Events",
            new InsightsDefaultPageHeaderRetriever("Tracked Events Analytics")
    ),
    TRACKED_EVENTS_SETUP_PAGE(
            "Setup",
            TRACKED_EVENTS_SETUP_NAVIGATION_ELEMET,
            "Tracked Events",
            new PageHeaderRetrieverByClassAndText("tracked-events-report__header__page-name", "Tracked Events Setup")
    ),
    REPORTS_PAGE(
            "Reports",
            REPORTS_NAVIGATION_ELEMET,
            "Reports",
            new PageHeaderRetrieverByClassAndText("reports-page__page-name", "Reports")
    );

    @Getter
    private String name;

    @Getter
    private InsightsNavigationBarElements navigationBarElement;

    @Getter
    private String pageTitleContains;

    @Getter
    private PageHeaderRetriever pageHeaderRetriever;
}
