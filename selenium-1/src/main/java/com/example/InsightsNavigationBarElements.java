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
            NAVIGATION_ELEMET,
            "Overview",
            new InsightsDefaultPageHeaderRetriever("Overview")
    ),
    APPS_NAVIGATION_ELEMET_GROUP(
            "Apps",
            new InsightsFirstLevelNavigationElementRetriever("apps"),
            NAVIGATION_ELEMET_GROUP,
            null,
            null
    ),
    APPS_OVERVIEW_NAVIGATION_ELEMET(
            "Apps Overview",
            new InsightsSecondLevelNavigationElementRetriever("Apps Overview"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Apps Overview")
    ),
    SMART_WALK_THRUS_NAVIGATION_ELEMET(
            "SmartWalkThrus",
            new InsightsSecondLevelNavigationElementRetriever("Smart Walk-Thrus"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Smart Walk-Thrus")
    ),
    WALK_THRUS_NAVIGATION_ELEMET(
            "WalkThrus",
            new InsightsSecondLevelNavigationElementRetriever("Walk-Thrus"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Walk-Thrus")
    ),
    ONBOARDING_NAVIGATION_ELEMET(
            "Onboarding",
            new InsightsSecondLevelNavigationElementRetriever("Onboarding"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Onboarding Tasks")
    ),
    SHOUT_OUTS_NAVIGATION_ELEMET(
            "ShoutOuts",
            new InsightsSecondLevelNavigationElementRetriever("ShoutOuts"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("ShoutOuts")
    ),
    LAUNCHERS_NAVIGATION_ELEMET(
            "Launchers",
            new InsightsSecondLevelNavigationElementRetriever("Launchers"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Launchers")
    ),
    RESOURCES_NAVIGATION_ELEMET(
            "Resources",
            new InsightsSecondLevelNavigationElementRetriever("Resources"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Resources")
    ),
    SHUTTLES_NAVIGATION_ELEMET(
            "Shuttles",
            new InsightsSecondLevelNavigationElementRetriever("Shuttles"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Shuttles")
    ),
    SURVEYS_NAVIGATION_ELEMET(
            "Surveys",
            new InsightsSecondLevelNavigationElementRetriever("Surveys"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Surveys")
    ),
    SMART_TIPS_NAVIGATION_ELEMET(
            "SmartTips",
            new InsightsSecondLevelNavigationElementRetriever("SmartTips"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("SmartTips")
    ),
    MENU_AND_SEARCH_NAVIGATION_ELEMET(
            "MenuAndSearch",
            new InsightsSecondLevelNavigationElementRetriever("Menu & Search"),
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Menu & Search")
    ),
    USERS_NAVIGATION_ELEMET(
            "Users",
            new InsightsFirstLevelNavigationElementRetriever("users"),
            NAVIGATION_ELEMET,
            "Users", new InsightsDefaultPageHeaderRetriever("Users")),
    SESSION_PLAYBACK_NAVIGATION_ELEMET(
            "Session Playback",
            new InsightsFirstLevelNavigationElementRetriever("session playback"),
            NAVIGATION_ELEMET,
            "Session Playback",
            new PageHeaderRetrieverByClassAndText("enable-sessions-empty-state__title", "Watch Session Playback")
    ),
    FEATURES_NAVIGATION_ELEMET(
            "Features",
            new InsightsFirstLevelNavigationElementRetriever("features"),
            NAVIGATION_ELEMET,
            "Features",
            new PageHeaderRetrieverByClassAndText("features-empty-state__title", "Track Feature Adoption")
    ),
    FUNNELS_NAVIGATION_ELEMET(
            "Funnels",
            new InsightsFirstLevelNavigationElementRetriever("funnels"),
            NAVIGATION_ELEMET,
            "Funnels",
            new PageHeaderRetrieverByClassAndText("funnel-selection__title", "Funnels")
    ),
    TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP(
            "Tracked Events",
            new InsightsFirstLevelNavigationElementRetriever("tracked events"),
            NAVIGATION_ELEMET_GROUP,
            null,
            null
    ),
    TRACKED_EVENTS_ANALYTICS_NAVIGATION_ELEMET(
            "TrackedEventsAnalytics",
            new InsightsSecondLevelNavigationElementRetriever("Analytics"),
            NAVIGATION_ELEMET,
            "Tracked Events",
            new InsightsDefaultPageHeaderRetriever("Tracked Events Analytics")
    ),
    TRACKED_EVENTS_SETUP_NAVIGATION_ELEMET(
            "Setup",
            new InsightsSecondLevelNavigationElementRetriever("Setup"),
            NAVIGATION_ELEMET,
            "Tracked Events",
            new PageHeaderRetrieverByClassAndText("tracked-events-report__header__page-name", "Tracked Events Setup")
    ),
    REPORTS_NAVIGATION_ELEMET(
            "Reports",
            new InsightsFirstLevelNavigationElementRetriever("reports"),
            NAVIGATION_ELEMET,
            "Reports",
            new PageHeaderRetrieverByClassAndText("reports-page__page-name", "Reports")
    );

    @Getter
    private String name;

    @Getter
    private NavigationElementRetriever navigationElementRetriever;

    @Getter
    private InsightsNavigationBarElementTypes type;

    @Getter
    private String pageTitleContains;

    @Getter
    private PageHeaderRetriever pageHeaderRetriever;

    @ToString
    @AllArgsConstructor
    enum InsightsNavigationBarElementTypes {

        NAVIGATION_ELEMET("Navigation Element"),
        NAVIGATION_ELEMET_GROUP("Navigation Element Group");

        @Getter
        private String name;
    }
}
