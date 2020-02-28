package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

import static com.example.NavigationBarElements.*;
import static com.example.PageScroller.ScrollDirections.DOWN;
import static com.example.PageScroller.ScrollDirections.UP;
import static com.example.PageScroller.ScrollSpeeds.MEDIUM;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

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
                                                new WidgetTitleRetrieverByXPath("//div[@class='walkme-app-highlight__title' and text()='Unique Users']"),
                                                new WidgetTitleRetrieverByXPath("//div[@class='walkme-app-highlight__title' and text()='Sessions']")
                                        ),
                                        asList(
                                                new WidgetBodyRetrieverByXPath("//*[@class='recharts-layer recharts-line']/ancestor::*[@class='recharts-surface']"),
                                                new WidgetBodyRetrieverByXPath("//div[contains(@data-tip,'count of users who visited')]"),
                                                new WidgetBodyRetrieverByXPath("//div[contains(@data-tip,'single visit of a user')] ")
                                        )
                                ),
                                new Widget(
                                        "Where are users accessing your site from?",
                                        asList(
                                                new WidgetTitleRetrieverByXPath("//div[@class='sessions-by-country__header-title' and text()='Where are users accessing your site from?']"),
                                                new WidgetTitleRetrieverByXPath("//div[text()='MAP']"),
                                                new WidgetTitleRetrieverByXPath("//div[text()='LIST']"),
                                                new WidgetTitleRetrieverByXPath("//div[text()='MAP']/ancestor::div[@class='view-state']")
                                        ),
                                        singletonList(
                                                new WidgetBodyRetrieverByClass("sessions-by-country__map")
                                        )
                                ),
                                new Widget(
                                        "Sessions Duration Breakdown",
                                        singletonList(
                                                new WidgetTitleRetrieverByClassAndText("sessions-time-breakdown__header", "Sessions Duration Breakdown")
                                        ),
                                        asList(
                                                new WidgetBodyRetrieverByXPath("//div[text()='Sessions Duration Breakdown']/following::div[@class='card-state-wrapper'][1]"),
                                                new WidgetBodyRetrieverByXPath("//div[@class='result-bar-label']"),
                                                new WidgetBodyRetrieverByXPath("//div[@class='pie-chart-legend-item'][1]"),
                                                new WidgetBodyRetrieverByXPath("//div[@class='pie-chart-legend-item'][2]"),
                                                new WidgetBodyRetrieverByXPath("//div[@class='pie-chart-legend-item'][3]")
                                        )
                                )
                        ),
                        new PageScrollPostObservationAction(DOWN, 600, MEDIUM)
                    ),
                    new ObservedWidgetsGroup(
                            asList(
                                    new Widget(
                                            "Top WalkMe Items",
                                            asList(
                                                    new WidgetTitleRetrieverByClassAndText("overview-walkme-report__title", "Top WalkMe Items")
                                            ),
                                            asList(
                                                    new WidgetBodyRetrieverByXPath("//div[text()='Type']/ancestor::div[@class='react-bs-table-container']")
                                            )
                                    ),
                                    new Widget(
                                            "Top Goals Reached",
                                            asList(
                                                    new WidgetTitleRetrieverByClassAndText("overview-walkme-report__title", "Top Goals Reached")
                                            ),
                                            asList(
                                                    new WidgetBodyRetrieverByXPath("//div[text()='Goal']/ancestor::div[@class='react-bs-table-container']")
                                            )
                                    )
                            ),
                            new PageScrollPostObservationAction(UP, 600, MEDIUM)
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
            new DefaultPageHeaderRetriever("Features"),
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
