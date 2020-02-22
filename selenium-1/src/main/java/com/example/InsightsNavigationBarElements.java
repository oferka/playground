package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import static com.example.InsightsNavigationBarElements.InsightsNavigationBarElementTypes.NAVIGATION_ELEMET;
import static com.example.InsightsNavigationBarElements.InsightsNavigationBarElementTypes.NAVIGATION_ELEMET_GROUP;
import static com.example.InsightsNavigationBarElements.InsightsNavigationBarLevels.FIRST_LEVEL;
import static com.example.InsightsNavigationBarElements.InsightsNavigationBarLevels.SECOND_LEVEL;

@ToString
@AllArgsConstructor
public enum InsightsNavigationBarElements {

    OVERVIEW(
            "Overview",
            new InsightsFirstLevelNavigationElementRetriever("overview"),
            FIRST_LEVEL,
            0,
            NAVIGATION_ELEMET,
            "Overview",
            new InsightsDefaultPageHeaderRetriever("Overview")
    ),
    APPS(
            "Apps",
            new InsightsFirstLevelNavigationElementRetriever("apps"),
            FIRST_LEVEL,
            1,
            NAVIGATION_ELEMET_GROUP,
            null,
            null
    ),
    APPS_OVERVIEW(
            "Apps Overview",
            new InsightsSecondLevelNavigationElementRetriever("Apps Overview"),
            SECOND_LEVEL,
            0,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Apps Overview")
    ),
    SMART_WALK_THRUS(
            "SmartWalkThrus",
            new InsightsSecondLevelNavigationElementRetriever("Smart Walk-Thrus"),
            SECOND_LEVEL,
            1,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Smart Walk-Thrus")
    ),
    WALK_THRUS(
            "WalkThrus",
            new InsightsSecondLevelNavigationElementRetriever("Walk-Thrus"),
            SECOND_LEVEL,
            2,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Walk-Thrus")
    ),
    ONBOARDING(
            "Onboarding",
            new InsightsSecondLevelNavigationElementRetriever("Onboarding"),
            SECOND_LEVEL,
            3,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Onboarding Tasks")
    ),
    SHOUT_OUTS(
            "ShoutOuts",
            new InsightsSecondLevelNavigationElementRetriever("ShoutOuts"),
            SECOND_LEVEL,
            4,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("ShoutOuts")
    ),
    LAUNCHERS(
            "Launchers",
            new InsightsSecondLevelNavigationElementRetriever("Launchers"),
            SECOND_LEVEL,
            5,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Launchers")
    ),
    RESOURCES(
            "Resources",
            new InsightsSecondLevelNavigationElementRetriever("Resources"),
            SECOND_LEVEL,
            6,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Resources")
    ),
    SHUTTLES(
            "Shuttles",
            new InsightsSecondLevelNavigationElementRetriever("Shuttles"),
            SECOND_LEVEL,
            7,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Shuttles")
    ),
    SURVEYS(
            "Surveys",
            new InsightsSecondLevelNavigationElementRetriever("Surveys"),
            SECOND_LEVEL,
            8,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Surveys")
    ),
    SMART_TIPS(
            "SmartTips",
            new InsightsSecondLevelNavigationElementRetriever("SmartTips"),
            SECOND_LEVEL,
            9,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("SmartTips")
    ),
    MENU_AND_SEARCH(
            "MenuAndSearch",
            new InsightsSecondLevelNavigationElementRetriever("Menu & Search"),
            SECOND_LEVEL,
            10,
            NAVIGATION_ELEMET,
            "Apps",
            new InsightsDefaultPageHeaderRetriever("Menu & Search")
    ),
    USERS(
            "Users",
            new InsightsFirstLevelNavigationElementRetriever("users"),
            FIRST_LEVEL,
            2,
            NAVIGATION_ELEMET,
            "Users", new InsightsDefaultPageHeaderRetriever("Users")),
    SESSION_PLAYBACK(
            "Session Playback",
            new InsightsFirstLevelNavigationElementRetriever("session playback"),
            FIRST_LEVEL,
            3,
            NAVIGATION_ELEMET,
            "Session Playback",
            new PageHeaderRetrieverByClassAndText("enable-sessions-empty-state__title", "Watch Session Playback")
    ),
    FEATURES(
            "Features",
            new InsightsFirstLevelNavigationElementRetriever("features"),
            FIRST_LEVEL,
            4,
            NAVIGATION_ELEMET,
            "Features",
            new PageHeaderRetrieverByClassAndText("features-empty-state__title", "Track Feature Adoption")
    ),
    FUNNELS(
            "Funnels",
            new InsightsFirstLevelNavigationElementRetriever("funnels"),
            FIRST_LEVEL,
            5,
            NAVIGATION_ELEMET,
            "Funnels",
            new PageHeaderRetrieverByClassAndText("funnel-selection__title", "Funnels")
    ),
    TRACKED_EVENTS(
            "Tracked Events",
            new InsightsFirstLevelNavigationElementRetriever("tracked events"),
            FIRST_LEVEL,
            6,
            NAVIGATION_ELEMET_GROUP,
            null,
            null
    ),
    TRACKED_EVENTS_ANALYTICS(
            "TrackedEventsAnalytics",
            new InsightsSecondLevelNavigationElementRetriever("Analytics"),
            SECOND_LEVEL,
            11,
            NAVIGATION_ELEMET,
            "Tracked Events",
            new InsightsDefaultPageHeaderRetriever("Tracked Events Analytics")
    ),
    TRACKED_EVENTS_SETUP(
            "Setup",
            new InsightsSecondLevelNavigationElementRetriever("Setup"),
            SECOND_LEVEL,
            12,
            NAVIGATION_ELEMET,
            "Tracked Events",
            new PageHeaderRetrieverByClassAndText("tracked-events-report__header__page-name", "Tracked Events Setup")
    ),
    REPORTS(
            "Reports",
            new InsightsFirstLevelNavigationElementRetriever("reports"),
            FIRST_LEVEL,
            7,
            NAVIGATION_ELEMET,
            "Reports",
            new PageHeaderRetrieverByClassAndText("reports-page__page-name", "Reports")
    );

    @Getter
    private String name;

    @Getter
    private NavigationElementRetriever navigationElementRetriever;

    @Getter
    private InsightsNavigationBarLevels level;

    @Getter
    private int itemIndex;

    @Getter
    private InsightsNavigationBarElementTypes type;

    @Getter
    private String pageTitleContains;

    @Getter
    private PageHeaderRetriever pageHeaderRetriever;

    @ToString
    @AllArgsConstructor
    enum InsightsNavigationBarLevels {

        FIRST_LEVEL("First Level", 0),
        SECOND_LEVEL("Second Level", 1);

        @Getter
        private String name;

        @Getter
        private int levelIndex;
    }

    @ToString
    @AllArgsConstructor
    enum InsightsNavigationBarElementTypes {

        NAVIGATION_ELEMET("Navigation Element"),
        NAVIGATION_ELEMET_GROUP("Navigation Element Group");

        @Getter
        private String name;
    }
}
