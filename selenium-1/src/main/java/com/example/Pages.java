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
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Overview']"),
            asList(
                    new WidgetsGroup(
                        singletonList(
                                new Widget(
                                        "Unique User and Sessions",
                                        new WidgetBorderRetrieverByXPath("//div[@class='highlights-with-graph']"),
                                        asList(
                                                new WidgetTitleRetrieverByXPath("//div[@class='walkme-app-highlight__title' and text()='Unique Users']"),
                                                new WidgetTitleRetrieverByXPath("//div[@class='walkme-app-highlight__title' and text()='Sessions']")
                                        ),
                                        asList(
                                                new WidgetBodyRetrieverByXPath("//div[contains(@data-tip,'count of users who visited')]"),
                                                new WidgetBodyRetrieverByXPath("//div[contains(@data-tip,'single visit of a user')]"),
                                                new WidgetBodyRetrieverByXPath("//*[@class='recharts-layer recharts-line']/ancestor::*[@class='recharts-surface']")
                                        )
                                )
                        ),
                        new PageScrollPostObservationAction(DOWN, 200, MEDIUM)
                    ),
                    new WidgetsGroup(
                            asList(
                                    new Widget(
                                            "Where are users accessing your site from?",
                                            new WidgetBorderRetrieverByXPath("//div[@class='sessions-by-country']"),
                                            asList(
                                                    new WidgetTitleRetrieverByXPath("//div[@class='sessions-by-country__header-title' and text()='Where are users accessing your site from?']"),
                                                    new WidgetTitleRetrieverByXPath("//div[text()='MAP']"),
                                                    new WidgetTitleRetrieverByXPath("//div[text()='LIST']"),
                                                    new WidgetTitleRetrieverByXPath("//div[text()='MAP']/ancestor::div[@class='view-state']")
                                            ),
                                            asList(
                                                    new WidgetBodyRetrieverByXPath("//div[@class='sessions-by-country__map']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@id='GeoChart']")
                                            )
                                    ),
                                    new Widget(
                                            "Sessions Duration Breakdown",
                                            new WidgetBorderRetrieverByXPath("//div[@class='sessions-time-breakdown']"),
                                            singletonList(
                                                    new WidgetTitleRetrieverByXPath("//div[@class='sessions-time-breakdown__header' and text()='Sessions Duration Breakdown']")
                                            ),
                                            asList(
                                                    new WidgetBodyRetrieverByXPath("//div[text()='Sessions Duration Breakdown']/following::div[@class='card-state-wrapper'][1]"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='result-bar-label']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='report-pie-chart-wrapper']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='pie-chart-legend-item'][1]"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='pie-chart-legend-item'][2]"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='pie-chart-legend-item'][3]")
                                            )
                                    )
                            ),
                            new PageScrollPostObservationAction(DOWN, 400, MEDIUM)
                    ),
                    new WidgetsGroup(
                            asList(
                                    new Widget(
                                            "Top WalkMe Items",
                                            new WidgetBorderRetrieverByXPath("//div[text()='Type']/ancestor::div[@class='overview-walkme-report']"),
                                            asList(
                                                    new WidgetTitleRetrieverByXPath("//div[@class='overview-walkme-report__title' and text()='Top WalkMe Items']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='overview-walkme-report__icon overview-walkme-reports__walkme-items-icon']")
                                            ),
                                            singletonList(
                                                    new WidgetBodyRetrieverByXPath("//div[text()='Type']/ancestor::div[@class='react-bs-table-container']")
                                            )
                                    ),
                                    new Widget(
                                            "Top Goals Reached",
                                            new WidgetBorderRetrieverByXPath("//div[text()='Goal']/ancestor::div[@class='overview-walkme-report']"),
                                            asList(
                                                    new WidgetTitleRetrieverByXPath("//div[@class='overview-walkme-report__title' and text()='Top Goals Reached']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='overview-walkme-report__icon overview-walkme-reports__goals-reached-icon']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='walkme-icon-image-div' and text()='?']")
                                            ),
                                            singletonList(
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
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Apps Overview']"),
            asList(
                    new WidgetsGroup(
                            singletonList(
                                    new Widget(
                                            "Users WalkMe was available to and Users who Interacted with WalkMe and Avg. user interactions with WalkMe",
                                            new WidgetBorderRetrieverByXPath("//div[@class='highlights-with-graph']"),
                                            asList(
                                                    new WidgetTitleRetrieverByXPath("//div[@class='walkme-app-highlight__title' and text()='Users WalkMe was available to']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='walkme-app-highlight__title' and text()='Users who Interacted with WalkMe']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='walkme-app-highlight__title' and text()='Avg. user interactions with WalkMe']")
                                            ),
                                            asList(
                                                    new WidgetBodyRetrieverByXPath("//div[contains(@data-tip,'Users who had any WalkMe content visible')]"),
                                                    new WidgetBodyRetrieverByXPath("//div[contains(@data-tip,'Users who interacted with any WalkMe item')]"),
                                                    new WidgetBodyRetrieverByXPath("//div[contains(@data-tip,'The average number of times a user has interacted with any WalkMe content.')]"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'in Filter')]/ancestor::div[@class='walkme-app-highlight__subtitle']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'Available To')]/ancestor::div[@class='walkme-app-highlight__subtitle']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'more than once')]/ancestor::div[@class='walkme-app-highlight__subtitle']"),
                                                    new WidgetBodyRetrieverByXPath("//*[@class='recharts-layer recharts-line']/ancestor::*[@class='recharts-surface']")
                                            )
                                    )
                            ),
                            new PageScrollPostObservationAction(DOWN, 500, MEDIUM)
                    ),
                    new WidgetsGroup(
                            singletonList(
                                    new Widget(
                                            "WalkMe Usage by Item Type",
                                            new WidgetBorderRetrieverByXPath("//div[@class='report-card walkme-overview-page__report-card walkme-overview-page__report-card--usage']"),
                                            singletonList(
                                                    new WidgetTitleRetrieverByXPath("//div[@class='walkme-items-usage__title' and text()='WalkMe Usage by Item Type']")
                                            ),
                                            asList(
                                                    new WidgetBodyRetrieverByXPath("//div[@class='walkme-items-usage__overview']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='walkme-items-usage__chart ']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='bubble-chart']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='walkme-items-usage__table']")
                                            )
                                    )
                            ),
                            new PageScrollPostObservationAction(DOWN, 400, MEDIUM)
                    ),
                    new WidgetsGroup(
                            asList(
                                    new Widget(
                                            "Top WalkMe Items",
                                            new WidgetBorderRetrieverByXPath("//div[text()='Type']/ancestor::div[@class='overview-walkme-report']"),
                                            asList(
                                                    new WidgetTitleRetrieverByXPath("//div[@class='overview-walkme-report__title' and text()='Top WalkMe Items']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='overview-walkme-report__icon overview-walkme-reports__walkme-items-icon']")
                                            ),
                                            singletonList(
                                                    new WidgetBodyRetrieverByXPath("//div[text()='Type']/ancestor::div[@class='react-bs-table-container']")
                                            )
                                    ),
                                    new Widget(
                                            "Top Goals Reached",
                                            new WidgetBorderRetrieverByXPath("//div[text()='Goal']/ancestor::div[@class='overview-walkme-report']"),
                                            asList(
                                                    new WidgetTitleRetrieverByXPath("//div[@class='overview-walkme-report__title' and text()='Top Goals Reached']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='overview-walkme-report__icon overview-walkme-reports__goals-reached-icon']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='walkme-icon-image-div' and text()='?']")
                                            ),
                                            singletonList(
                                                    new WidgetBodyRetrieverByXPath("//div[text()='Goal']/ancestor::div[@class='react-bs-table-container']")
                                            )
                                    )
                            ),
                            new PageScrollPostObservationAction(UP, 900, MEDIUM)
                    )
            )
    ),
    SMART_WALK_THRUS_PAGE(
            "SmartWalkThrus",
            SMART_WALK_THRUS_NAVIGATION_ELEMET,
            "Apps",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Smart Walk-Thrus']"),
            asList(
                    new WidgetsGroup(
                            singletonList(
                                    new Widget(
                                            "Users Played Smart Walk-Thrus and Smart Walk-Thru Plays and Main Goals Reached",
                                            new WidgetBorderRetrieverByXPath("//div[@class='walkme-app-highlights walkme-app-highlights__triple-mode']"),
                                            asList(
                                                    new WidgetTitleRetrieverByXPath("//div[@class='walkme-app-highlight__title' and text()='Users Played Smart Walk-Thrus']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='walkme-app-highlight__title' and text()='Smart Walk-Thru Plays']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='walkme-app-highlight__title' and text()='Main Goals Reached']")
                                            ),
                                            asList(
                                                    new WidgetBodyRetrieverByXPath("//div[contains(@data-tip,'The total count of users who played Smart Walk-Thrus')]"),
                                                    new WidgetBodyRetrieverByXPath("//div[contains(@data-tip,'The total count of Smart Walk-Thru plays')]"),
                                                    new WidgetBodyRetrieverByXPath("//div[contains(@data-tip,'The total count of Smart Walk-Thru plays that resulted')]"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'total users')]/ancestor::div[@class='walkme-app-highlight__subtitle']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'plays per user')]/ancestor::div[@class='walkme-app-highlight__subtitle']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'plays resulted in')]/ancestor::div[@class='walkme-app-highlight__subtitle']")
                                            )
                                    )
                            ),
                            new PageScrollPostObservationAction(DOWN, 500, MEDIUM)
                    ),
                    new WidgetsGroup(
                            singletonList(
                                    new Widget(
                                            "Smart Walk-Thrus Summary",
                                            new WidgetBorderRetrieverByXPath("//div[@class='insights-table__container']"),
                                            asList(
                                                    new WidgetTitleRetrieverByXPath("//div[@class='insights-table__header']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='insights-table__title' and text()='Smart Walk-Thrus Summary']"),
                                                    new WidgetTitleRetrieverByXPath("//div[@class='table-search-bar table-search-bar--blurred']")
                                            ),
                                            asList(
                                                    new WidgetBodyRetrieverByXPath("//div[@class='react-bs-table-container']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='reports-table__pagination-container']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='reports-table__pagination-text']"),
                                                    new WidgetBodyRetrieverByXPath("//div[@class='reports-table__pagination']")
                                            )
                                    )
                            ),
                            new PageScrollPostObservationAction(UP, 500, MEDIUM)
                    )
            )
    ),
    WALK_THRUS_PAGE(
            "WalkThrus",
            WALK_THRUS_NAVIGATION_ELEMET,
            "Apps",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Walk-Thrus']"),
            asList(

            )
    ),
    ONBOARDING_PAGE(
            "Onboarding",
            ONBOARDING_NAVIGATION_ELEMET,
            "Apps",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Onboarding Tasks']"),
            asList(

            )
    ),
    SHOUT_OUTS_PAGE(
            "ShoutOuts",
            SHOUT_OUTS_NAVIGATION_ELEMET,
            "Apps",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='ShoutOuts']"),
            asList(

            )
    ),
    LAUNCHERS_PAGE(
            "Launchers",
            LAUNCHERS_NAVIGATION_ELEMET,
            "Apps",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Launchers']"),
            asList(

            )
    ),
    RESOURCES_PAGE(
            "Resources",
            RESOURCES_NAVIGATION_ELEMET,
            "Apps",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Resources']"),
            asList(

            )
    ),
    SHUTTLES_PAGE(
            "Shuttles",
            SHUTTLES_NAVIGATION_ELEMET,
            "Apps",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Shuttles']"),
            asList(

            )
    ),
    SURVEYS_PAGE(
            "Surveys",
            SURVEYS_NAVIGATION_ELEMET,
            "Apps",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Surveys']"),
            asList(

            )
    ),
    SMART_TIPS_PAGE(
            "SmartTips",
            SMART_TIPS_NAVIGATION_ELEMET,
            "Apps",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='SmartTips']"),
            asList(

            )
    ),
    MENU_AND_SEARCH_PAGE(
            "MenuAndSearch",
            MENU_AND_SEARCH_NAVIGATION_ELEMET,
            "Apps",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Menu & Search']"),
            asList(

            )
    ),
    USERS_PAGE(
            "Users",
            USERS_NAVIGATION_ELEMET,
            "Users",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Users']"),
            asList(

            )
    ),
    SESSION_PLAYBACK_PAGE(
            "Session Playback",
            SESSION_PLAYBACK_NAVIGATION_ELEMET,
            "Session Playback",
            new PageHeaderRetrieverByXPath("//div[@class='enable-sessions-empty-state__title' and text()='Watch Session Playback']"),
            asList(

            )
    ),
    FEATURES_PAGE(
            "Features",
            FEATURES_NAVIGATION_ELEMET,
            "Features",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Features']"),
            asList(

            )
    ),
    FUNNELS_PAGE(
            "Funnels",
            FUNNELS_NAVIGATION_ELEMET,
            "Funnels",
            new PageHeaderRetrieverByXPath("//div[@class='funnel-selection__title' and text()='Funnels']"),
            asList(

            )
    ),
    TRACKED_EVENTS_ANALYTICS_PAGE(
            "TrackedEventsAnalytics",
            TRACKED_EVENTS_ANALYTICS_NAVIGATION_ELEMET,
            "Tracked Events",
            new PageHeaderRetrieverByXPath("//div[@class='report-header__page-name' and text()='Tracked Events Analytics']"),
            asList(

            )
    ),
    TRACKED_EVENTS_SETUP_PAGE(
            "TrackedEventsSetup",
            TRACKED_EVENTS_SETUP_NAVIGATION_ELEMET,
            "Tracked Events",
            new PageHeaderRetrieverByXPath("//div[@class='tracked-events-report__header__page-name' and text()='Tracked Events Setup']"),
            asList(

            )
    ),
    REPORTS_PAGE(
            "Reports",
            REPORTS_NAVIGATION_ELEMET,
            "Reports",
            new PageHeaderRetrieverByXPath("//div[@class='reports-page__page-name' and text()='Reports']"),
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
    private List<WidgetsGroup> widgetsGroups;
}
