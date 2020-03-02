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
            asList(

            )
    ),
    ONBOARDING_PAGE(
            "Onboarding",
            ONBOARDING_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Onboarding Tasks']"),
            asList(

            )
    ),
    SHOUT_OUTS_PAGE(
            "ShoutOuts",
            SHOUT_OUTS_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='ShoutOuts']"),
            asList(

            )
    ),
    LAUNCHERS_PAGE(
            "Launchers",
            LAUNCHERS_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Launchers']"),
            asList(

            )
    ),
    RESOURCES_PAGE(
            "Resources",
            RESOURCES_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Resources']"),
            asList(

            )
    ),
    SHUTTLES_PAGE(
            "Shuttles",
            SHUTTLES_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Shuttles']"),
            asList(

            )
    ),
    SURVEYS_PAGE(
            "Surveys",
            SURVEYS_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Surveys']"),
            asList(

            )
    ),
    SMART_TIPS_PAGE(
            "SmartTips",
            SMART_TIPS_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='SmartTips']"),
            asList(

            )
    ),
    MENU_AND_SEARCH_PAGE(
            "MenuAndSearch",
            MENU_AND_SEARCH_NAVIGATION_ELEMET,
            "Apps",
            By.xpath("//div[@class='report-header__page-name' and text()='Menu & Search']"),
            asList(

            )
    ),
    USERS_PAGE(
            "Users",
            USERS_NAVIGATION_ELEMET,
            "Users",
            By.xpath("//div[@class='report-header__page-name' and text()='Users']"),
            asList(

            )
    ),
    SESSION_PLAYBACK_PAGE(
            "Session Playback",
            SESSION_PLAYBACK_NAVIGATION_ELEMET,
            "Session Playback",
            By.xpath("//div[@class='enable-sessions-empty-state__title' and text()='Watch Session Playback']"),
            asList(

            )
    ),
    FEATURES_PAGE(
            "Features",
            FEATURES_NAVIGATION_ELEMET,
            "Features",
            By.xpath("//div[@class='report-header__page-name' and text()='Features']"),
            asList(

            )
    ),
    FUNNELS_PAGE(
            "Funnels",
            FUNNELS_NAVIGATION_ELEMET,
            "Funnels",
            By.xpath("//div[@class='funnel-selection__title' and text()='Funnels']"),
            asList(

            )
    ),
    TRACKED_EVENTS_ANALYTICS_PAGE(
            "TrackedEventsAnalytics",
            TRACKED_EVENTS_ANALYTICS_NAVIGATION_ELEMET,
            "Tracked Events",
            By.xpath("//div[@class='report-header__page-name' and text()='Tracked Events Analytics']"),
            asList(

            )
    ),
    TRACKED_EVENTS_SETUP_PAGE(
            "TrackedEventsSetup",
            TRACKED_EVENTS_SETUP_NAVIGATION_ELEMET,
            "Tracked Events",
            By.xpath("//div[@class='tracked-events-report__header__page-name' and text()='Tracked Events Setup']"),
            asList(

            )
    ),
    REPORTS_PAGE(
            "Reports",
            REPORTS_NAVIGATION_ELEMET,
            "Reports",
            By.xpath("//div[@class='reports-page__page-name' and text()='Reports']"),
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
    private List<WidgetGroups> widgetGroups;
}
