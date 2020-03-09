package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.openqa.selenium.By;

import java.util.List;

import static com.example.NavigationBarElements.*;
import static com.example.WidgetGroups.*;
import static java.util.Arrays.asList;

@ToString
@AllArgsConstructor
public enum Pages {

    OVERVIEW_PAGE(
            "Overview",
            OVERVIEW_NAVIGATION_ELEMET,
            "Overview",
            By.xpath("//div[@class='report-header__page-name' and text()='Overview']"),
            new FilterInstructions(
                    asList(
//                            "All Sessions",
//                            "need training visits",
//                            "Chrome users",
//                            "Accessed PPS+ Article"
                    ),
                    asList(
//                            "All Sessions",
//                            "need training visits",
//                            "Chrome users",
//                            "Accessed PPS+ Article"
                    )
            ),
            new TimePeriodInstructions(
                    asList(
//                            "Today",
//                            "Yesterday",
//                            "Last Week",
//                            "Last Month",
//                            "Last 3 Months",
//                            "Last 30 Days",
//                            "Last 90 Days",
//                            "Custom Dates"
                    ),
                    asList(
//                            "Today",
//                            "Yesterday",
//                            "Last Week",
//                            "Last Month",
//                            "Last 3 Months",
//                            "Last 30 Days",
//                            "Last 90 Days",
//                            "Custom Dates"
                    )
            ),
            asList(
                    OVERVIEW_PAGE_GROUP_1,
                    OVERVIEW_PAGE_GROUP_2,
                    OVERVIEW_PAGE_GROUP_3
            )
    ),
    APPS_OVERVIEW_PAGE(
            "Apps Overview",
            APPS_OVERVIEW_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Apps Overview']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(
                    APPS_OVERVIEW_PAGE_GROUP_1,
                    APPS_OVERVIEW_PAGE_GROUP_2,
                    APPS_OVERVIEW_PAGE_GROUP_3
            )
    ),
    SMART_WALK_THRUS_PAGE(
            "SmartWalkThrus",
            SMART_WALK_THRUS_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Smart Walk-Thrus']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(
                    SMART_WALK_THRUS_GROUP_1,
                    SMART_WALK_THRUS_GROUP_2
            )
    ),
    WALK_THRUS_PAGE(
            "WalkThrus",
            WALK_THRUS_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Walk-Thrus']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    ONBOARDING_PAGE(
            "Onboarding",
            ONBOARDING_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Onboarding Tasks']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    SHOUT_OUTS_PAGE(
            "ShoutOuts",
            SHOUT_OUTS_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='ShoutOuts']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    LAUNCHERS_PAGE(
            "Launchers",
            LAUNCHERS_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Launchers']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    RESOURCES_PAGE(
            "Resources",
            RESOURCES_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Resources']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    SHUTTLES_PAGE(
            "Shuttles",
            SHUTTLES_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Shuttles']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    SURVEYS_PAGE(
            "Surveys",
            SURVEYS_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Surveys']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    SMART_TIPS_PAGE(
            "SmartTips",
            SMART_TIPS_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='SmartTips']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    MENU_AND_SEARCH_PAGE(
            "MenuAndSearch",
            MENU_AND_SEARCH_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Menu & Search']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    USERS_PAGE(
            "Users",
            USERS_NAVIGATION_ELEMET,
            "Users",
            By.xpath("//div[@class='report-header__page-name' and text()='Users']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    SESSION_PLAYBACK_PAGE(
            "Session Playback",
            SESSION_PLAYBACK_NAVIGATION_ELEMET,
            "Session Playback",
            By.xpath("//div[@class='enable-sessions-empty-state__title' and text()='Watch Session Playback']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    FEATURES_PAGE(
            "Features",
            FEATURES_NAVIGATION_ELEMET,
            "Features",
            By.xpath("//div[@class='report-header__page-name' and text()='Features']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    FUNNELS_PAGE(
            "Funnels",
            FUNNELS_NAVIGATION_ELEMET,
            "Funnels",
            By.xpath("//div[@class='report-header__page-name' and text()='Funnels']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    TRACKED_EVENTS_ANALYTICS_PAGE(
            "TrackedEventsAnalytics",
            TRACKED_EVENTS_ANALYTICS_NAVIGATION_ELEMET,
            "Tracked Events",
            By.xpath("//div[@class='report-header__page-name' and text()='Tracked Events Analytics']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    TRACKED_EVENTS_SETUP_PAGE(
            "TrackedEventsSetup",
            TRACKED_EVENTS_SETUP_NAVIGATION_ELEMET,
            "Tracked Events",
            By.xpath("//div[@class='tracked-events-report__header__page-name' and text()='Tracked Events Setup']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            asList(

            )
    ),
    REPORTS_PAGE(
            "Reports",
            REPORTS_NAVIGATION_ELEMET,
            "Reports",
            By.xpath("//div[@class='reports-page__page-name' and text()='Reports']"),
            new FilterInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
            new TimePeriodInstructions(
                    asList(

                    ),
                    asList(

                    )
            ),
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
    private By pageHeaderLocator;

    @Getter
    private FilterInstructions filterInstructions;

    @Getter
    private TimePeriodInstructions timePeriodInstructions;

    @Getter
    private List<WidgetGroups> widgetGroups;
}
