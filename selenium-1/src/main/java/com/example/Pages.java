package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

import static com.example.NavigationBarElements.*;
import static com.example.PageScroller.ScrollDirections.DOWN;
import static com.example.PageScroller.ScrollDirections.UP;
import static java.util.Arrays.asList;

@ToString
@AllArgsConstructor
public enum Pages {

    OVERVIEW_PAGE(
            "Overview",
            OVERVIEW_NAVIGATION_ELEMET,
            "Overview",
            new DefaultPageHeaderRetriever("Overview"),
            asList(
                new ObservedWidgetsGroup(
                        asList(
                                new Widget(
                                        "Unique User and Sessions",
                                        asList(
                                                new WidgetTitleRetrieverByClassAndText("walkme-app-highlight__title", "Unique Users"),
                                                new WidgetTitleRetrieverByClassAndText("walkme-app-highlight__title", "Sessions")
                                        )
                                ),
                                new Widget(
                                        "Where are users accessing your site from?",
                                        new WidgetTitleRetrieverByClassAndText("sessions-by-country__header-title", "Where are users accessing your site from?")
                                ),
                                new Widget(
                                        "Sessions Duration Breakdown",
                                        new WidgetTitleRetrieverByClassAndText("sessions-time-breakdown__header", "Sessions Duration Breakdown")
                                )
                        ),
                        new PageScrollPostObservationAction(DOWN, 500)
                ),
                    new ObservedWidgetsGroup(
                            asList(
                                    new Widget(
                                            "Top WalkMe Items",
                                            new WidgetTitleRetrieverByClassAndText("overview-walkme-report__title", "Top WalkMe Items")
                                    ),
                                    new Widget(
                                            "Top Goals Reached",
                                            new WidgetTitleRetrieverByClassAndText("overview-walkme-report__title", "Top Goals Reached")
                                    )
                            ),
                            new PageScrollPostObservationAction(UP, 500)
                    )
            )
    ),
    APPS_OVERVIEW_PAGE(
            "Apps Overview",
            APPS_OVERVIEW_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("Apps Overview"),
            asList(

            )
    ),
    SMART_WALK_THRUS_PAGE(
            "SmartWalkThrus",
            SMART_WALK_THRUS_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("Smart Walk-Thrus"),
            asList(

            )
    ),
    WALK_THRUS_PAGE(
            "WalkThrus",
            WALK_THRUS_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("Walk-Thrus"),
            asList(

            )
    ),
    ONBOARDING_PAGE(
            "Onboarding",
            ONBOARDING_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("Onboarding Tasks"),
            asList(

            )
    ),
    SHOUT_OUTS_PAGE(
            "ShoutOuts",
            SHOUT_OUTS_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("ShoutOuts"),
            asList(

            )
    ),
    LAUNCHERS_PAGE(
            "Launchers",
            LAUNCHERS_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("Launchers"),
            asList(

            )
    ),
    RESOURCES_PAGE(
            "Resources",
            RESOURCES_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("Resources"),
            asList(

            )
    ),
    SHUTTLES_PAGE(
            "Shuttles",
            SHUTTLES_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("Shuttles"),
            asList(

            )
    ),
    SURVEYS_PAGE(
            "Surveys",
            SURVEYS_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("Surveys"),
            asList(

            )
    ),
    SMART_TIPS_PAGE(
            "SmartTips",
            SMART_TIPS_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("SmartTips"),
            asList(

            )
    ),
    MENU_AND_SEARCH_PAGE(
            "MenuAndSearch",
            MENU_AND_SEARCH_NAVIGATION_ELEMET,
            "Apps",
            new DefaultPageHeaderRetriever("Menu & Search"),
            asList(

            )
    ),
    USERS_PAGE(
            "Users",
            USERS_NAVIGATION_ELEMET,
            "Users", new DefaultPageHeaderRetriever("Users"),
            asList(

            )
    ),
    SESSION_PLAYBACK_PAGE(
            "Session Playback",
            SESSION_PLAYBACK_NAVIGATION_ELEMET,
            "Session Playback",
            new PageHeaderRetrieverByClassAndText("enable-sessions-empty-state__title", "Watch Session Playback"),
            asList(

            )
    ),
    FEATURES_PAGE(
            "Features",
            FEATURES_NAVIGATION_ELEMET,
            "Features",
            new PageHeaderRetrieverByClassAndText("features-empty-state__title", "Track Feature Adoption"),
            asList(

            )
    ),
    FUNNELS_PAGE(
            "Funnels",
            FUNNELS_NAVIGATION_ELEMET,
            "Funnels",
            new PageHeaderRetrieverByClassAndText("funnel-selection__title", "Funnels"),
            asList(

            )
    ),
    TRACKED_EVENTS_ANALYTICS_PAGE(
            "TrackedEventsAnalytics",
            TRACKED_EVENTS_ANALYTICS_NAVIGATION_ELEMET,
            "Tracked Events",
            new DefaultPageHeaderRetriever("Tracked Events Analytics"),
            asList(

            )
    ),
    TRACKED_EVENTS_SETUP_PAGE(
            "TrackedEventsSetup",
            TRACKED_EVENTS_SETUP_NAVIGATION_ELEMET,
            "Tracked Events",
            new PageHeaderRetrieverByClassAndText("tracked-events-report__header__page-name", "Tracked Events Setup"),
            asList(

            )
    ),
    REPORTS_PAGE(
            "Reports",
            REPORTS_NAVIGATION_ELEMET,
            "Reports",
            new PageHeaderRetrieverByClassAndText("reports-page__page-name", "Reports"),
            asList(

            )
    );

    @Getter
    private String name;

    @Getter
    private NavigationBarElements navigationBarElement;

    @Getter
    private String pageTitleContains;

    @Getter
    private PageHeaderRetriever pageHeaderRetriever;

    @Getter
    private List<ObservedWidgetsGroup> observedWidgetsGroups;
}
