    package com.example;

    import com.example.ViewStateChangeInstructions.ViewState;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.ToString;
    import org.openqa.selenium.By;

    import java.util.List;

    import static java.util.Arrays.asList;
    import static java.util.Collections.singletonList;

    @ToString
    @AllArgsConstructor
    public enum Widgets {

        UNIQUE_USER_AND_SESSIONS(
                "Unique User and Sessions",
                By.xpath("//div[@class='highlights-with-graph']"),
                asList(
                        By.xpath("//div[@class='walkme-app-highlight__title' and text()='Unique Users']"),
                        By.xpath("//div[@class='walkme-app-highlight__title' and text()='Sessions']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[contains(@data-tip,'count of users who visited')]",
                                        asList(
                                                By.xpath("//div[contains(@data-tip,'count of users who visited')]"),
                                                By.xpath("//div[contains(@data-tip,'single visit of a user')]"),
                                                By.xpath("//*[@class='recharts-layer recharts-line']/ancestor::*[@class='recharts-surface']")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                                ,
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                null
        ),
        WHERE_ARE_USERS_ACCESSING_YOUR_SITE_FROM(
                "Where are users accessing your site from?",
                By.xpath("//div[@class='sessions-by-country']"),
                asList(
                        By.xpath("//div[@class='sessions-by-country__header-title' and text()='Where are users accessing your site from?']"),
                        By.xpath("//div[text()='MAP']/ancestor::div[@class='view-state']"),
                        By.xpath("//div[text()='MAP']"),
                        By.xpath("//div[text()='LIST']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[@class='sessions-by-country__map']",
                                        asList(
                                                By.xpath("//div[@class='sessions-by-country__map']"),
                                                By.xpath("//div[@id='GeoChart']")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        "//div[@class='sessions-by-country__header-title' and text()='Where are users accessing your site from?']/following::div[@class='no-data-card__text' and text()='No sessions found'][1]",
                                        asList(
                                                By.xpath("//div[@class='sessions-by-country__header-title' and text()='Where are users accessing your site from?']/following::div[@class='no-data-card'][1]"),
                                                By.xpath("//div[@class='sessions-by-country__header-title' and text()='Where are users accessing your site from?']/following::div[@class='no-data-card__img'][1]"),
                                                By.xpath("//div[@class='sessions-by-country__header-title' and text()='Where are users accessing your site from?']/following::div[@class='no-data-card__text' and text()='No sessions found'][1]")
                                        ),
                                        false
                                )
                                ,
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                new ViewStateChangeInstructions(
                        asList(
                                new ViewState(
                                        "LIST",
                                        By.xpath("//div[text()='LIST']"),
                                        asList(
                                                By.xpath("//div[text()='Country']/ancestor::div[@class='react-bs-table-container']"),
                                                By.xpath("//div[text()='Country']/following::tr[@class='report-list-view__row'][1]"),
                                                By.xpath("//div[text()='Country']/following::img[@class='sessions-by-country__flag'][1]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__country-name'][1]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__sessions-value-cell__count'][1]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__sessions-value-cell__percent'][1]"),
                                                By.xpath("//div[text()='Country']/following::tr[@class='report-list-view__row'][2]"),
                                                By.xpath("//div[text()='Country']/following::img[@class='sessions-by-country__flag'][2]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__country-name'][2]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__sessions-value-cell__count'][2]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__sessions-value-cell__percent'][2]"),
                                                By.xpath("//div[text()='Country']/following::tr[@class='report-list-view__row'][3]"),
                                                By.xpath("//div[text()='Country']/following::img[@class='sessions-by-country__flag'][3]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__country-name'][3]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__sessions-value-cell__count'][3]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__sessions-value-cell__percent'][3]"),
                                                By.xpath("//div[text()='Country']/following::tr[@class='report-list-view__row'][4]"),
                                                By.xpath("//div[text()='Country']/following::img[@class='sessions-by-country__flag'][4]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__country-name'][4]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__sessions-value-cell__count'][4]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__sessions-value-cell__percent'][4]"),
                                                By.xpath("//div[text()='Country']/following::tr[@class='report-list-view__row'][5]"),
                                                By.xpath("//div[text()='Country']/following::img[@class='sessions-by-country__flag'][5]]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__country-name'][5]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__sessions-value-cell__count'][5]"),
                                                By.xpath("//div[text()='Country']/following::div[@class='sessions-by-country__sessions-value-cell__percent'][5]")
                                        )
                                ),
                                new ViewState(
                                        "MAP",
                                        By.xpath("//div[text()='MAP']"),
                                        asList(
                                                By.xpath("//div[@id='GeoChart']")
                                        )
                                )
                        )
                )
        ),
        SESSIONS_DURATION_BREAKDOWN(
                "Sessions Duration Breakdown",
                By.xpath("//div[@class='sessions-time-breakdown']"),
                singletonList(
                        By.xpath("//div[@class='sessions-time-breakdown__header' and text()='Sessions Duration Breakdown']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[@class='result-bar-label']",
                                        asList(
                                                By.xpath("//div[@class='result-bar-label']"),
                                                By.xpath("//div[text()='Sessions Duration Breakdown']/following::div[@class='card-state-wrapper'][1]"),
                                                By.xpath("//div[@class='report-pie-chart-wrapper']"),
                                                By.xpath("//div[@class='pie-chart-legend-item'][1]"),
                                                By.xpath("//div[@class='pie-chart-legend-item'][2]"),
                                                By.xpath("//div[@class='pie-chart-legend-item'][3]")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        "//div[@class='sessions-time-breakdown__header' and text()='Sessions Duration Breakdown']/following::div[@class='no-data-card__text' and text()='No sessions found']",
                                        asList(
                                                By.xpath("//div[@class='sessions-time-breakdown__header' and text()='Sessions Duration Breakdown']/following::div[@class='card-state-wrapper'][1]"),
                                                By.xpath("//div[@class='sessions-time-breakdown__header' and text()='Sessions Duration Breakdown']/following::div[@class='no-data-card__img'][1]"),
                                                By.xpath("//div[@class='sessions-time-breakdown__header' and text()='Sessions Duration Breakdown']/following::div[@class='no-data-card__text' and text()='No sessions found']")
                                        ),
                                        false
                                ),
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                null
        ),
        OVERVIEW_TOP_WALKME_ITEMS(
                "Overview Top WalkMe Items",
                By.xpath("//div[text()='Type']/ancestor::div[@class='overview-walkme-report']"),
                asList(
                        By.xpath("//div[@class='overview-walkme-report__title' and text()='Top WalkMe Items']"),
                        By.xpath("//div[@class='overview-walkme-report__icon overview-walkme-reports__walkme-items-icon']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[text()='Type']/following::div[@class='report-list-view__cell'][1]",
                                        asList(
                                                By.xpath("//div[text()='Type']/ancestor::div[@class='react-bs-table-container']"),
                                                By.xpath("//div[text()='Type']/following::tr[@class='report-list-view__row'][1]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][1]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][2]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][3]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][4]"),
                                                By.xpath("//div[text()='Type']/following::tr[@class='report-list-view__row'][2]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][5]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][6]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][7]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][8]"),
                                                By.xpath("//div[text()='Type']/following::tr[@class='report-list-view__row'][3]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][9]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][10]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][11]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][12]"),
                                                By.xpath("//div[text()='Type']/following::tr[@class='report-list-view__row'][4]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][13]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][14]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][15]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][16]"),
                                                By.xpath("//div[text()='Type']/following::tr[@class='report-list-view__row'][5]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][17]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][18]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][19]"),
                                                By.xpath("//div[text()='Type']/following::div[@class='report-list-view__cell'][20]")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        "//div[text()='No WalkMe activity found']",
                                        asList(
                                                By.xpath("//div[text()='No WalkMe activity found']/ancestor::div[@class='react-bs-table-container']"),
                                                By.xpath("//div[text()='No WalkMe activity found']/preceding-sibling::div[@class='no-data-card__img']"),
                                                By.xpath("//div[text()='No WalkMe activity found']")
                                        ),
                                        false
                                )
                                ,
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                null
        ),
        OVERVIEW_TOP_GOALS_REACHED(
                "Overview Top Goals Reached",
                By.xpath("//div[text()='Goal']/ancestor::div[@class='overview-walkme-report']"),
                asList(
                        By.xpath("//div[@class='overview-walkme-report__title' and text()='Top Goals Reached']"),
                        By.xpath("//div[@class='overview-walkme-report__icon overview-walkme-reports__goals-reached-icon']"),
                        By.xpath("//div[@class='walkme-icon-image-div' and text()='?']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[text()='Goal']/following::div[@class='report-list-view__cell'][1]",
                                        asList(
                                                By.xpath("//div[text()='Goal']/ancestor::div[@class='react-bs-table-container']"),
                                                By.xpath("//div[text()='Goal']/following::tr[@class='report-list-view__row'][1]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][1]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][2]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][3]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][4]"),
                                                By.xpath("//div[text()='Goal']/following::tr[@class='report-list-view__row'][2]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][5]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][6]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][7]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][8]"),
                                                By.xpath("//div[text()='Goal']/following::tr[@class='report-list-view__row'][3]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][9]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][10]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][11]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][12]"),
                                                By.xpath("//div[text()='Goal']/following::tr[@class='report-list-view__row'][4]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][13]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][14]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][15]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][16]"),
                                                By.xpath("//div[text()='Goal']/following::tr[@class='report-list-view__row'][5]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][17]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][18]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][19]"),
                                                By.xpath("//div[text()='Goal']/following::div[@class='report-list-view__cell'][20]")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        "//div[text()='No goals reached']",
                                        asList(
                                                By.xpath("//div[text()='No goals reached']/ancestor::div[@class='react-bs-table-container']"),
                                                By.xpath("/div[text()='No goals reached']/preceding-sibling::div[@class='no-data-card__img']"),
                                                By.xpath("//div[text()='No goals reached']")
                                        ),
                                        false
                                )
                                ,
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                null
        ),
        USERS_WALKME_WAS_AVAILABLE_TO_AND_USERS_WHO_INTERACTED_AND_AVG_USER_INTERACTION(
                "Users WalkMe was available to and Users who Interacted with WalkMe and Avg. user interactions with WalkMe",
                By.xpath("//div[@class='highlights-with-graph']"),
                asList(
                        By.xpath("//div[@class='walkme-app-highlight__title' and text()='Users WalkMe was available to']"),
                        By.xpath("//div[@class='walkme-app-highlight__title' and text()='Users who Interacted with WalkMe']"),
                        By.xpath("//div[@class='walkme-app-highlight__title' and text()='Avg. user interactions with WalkMe']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[contains(@data-tip,'Users who had any WalkMe content visible')]",
                                        asList(
                                                By.xpath("//div[contains(@data-tip,'Users who had any WalkMe content visible')]"),
                                                By.xpath("//div[contains(@data-tip,'Users who interacted with any WalkMe item')]"),
                                                By.xpath("//div[contains(@data-tip,'The average number of times a user has interacted with any WalkMe content.')]"),
                                                By.xpath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'in Filter')]/ancestor::div[@class='walkme-app-highlight__subtitle']"),
                                                By.xpath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'Available To')]/ancestor::div[@class='walkme-app-highlight__subtitle']"),
                                                By.xpath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'more than once')]/ancestor::div[@class='walkme-app-highlight__subtitle']"),
                                                By.xpath("//*[@class='recharts-layer recharts-line']/ancestor::*[@class='recharts-surface']")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                                ,
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                null
        ),
        WALKME_USAGE_BY_ITEM_TYPE(
                "WalkMe Usage by Item Type",
                By.xpath("//div[@class='report-card walkme-overview-page__report-card walkme-overview-page__report-card--usage']"),
                singletonList(
                        By.xpath("//div[@class='walkme-items-usage__title' and text()='WalkMe Usage by Item Type']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[@class='walkme-items-usage__overview']",
                                        asList(
                                                By.xpath("//div[@class='walkme-items-usage__overview']"),
                                                By.xpath("//div[@class='walkme-items-usage__chart ']"),
                                                By.xpath("//div[@class='bubble-chart']"),
                                                By.xpath("//div[@class='walkme-items-usage__table']")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                                ,
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                null
        ),
        APPS_OVERVIEW_TOP_WALKME_ITEMS(
                "Apps Overview Top WalkMe Items",
                By.xpath("//div[text()='Type']/ancestor::div[@class='overview-walkme-report']"),
                asList(
                        By.xpath("//div[@class='overview-walkme-report__title' and text()='Top WalkMe Items']"),
                        By.xpath("//div[@class='overview-walkme-report__icon overview-walkme-reports__walkme-items-icon']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[text()='Type']/ancestor::div[@class='react-bs-table-container']",
                                        singletonList(
                                                By.xpath("//div[text()='Type']/ancestor::div[@class='react-bs-table-container']")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                                ,
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                null
        ),
        APPS_OVERVIEW_TOP_GOALS_REACHED(
                "Apps Overview Top Goals Reached",
                By.xpath("//div[text()='Goal']/ancestor::div[@class='overview-walkme-report']"),
                asList(
                        By.xpath("//div[@class='overview-walkme-report__title' and text()='Top Goals Reached']"),
                        By.xpath("//div[@class='overview-walkme-report__icon overview-walkme-reports__goals-reached-icon']"),
                        By.xpath("//div[@class='walkme-icon-image-div' and text()='?']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[text()='Goal']/ancestor::div[@class='react-bs-table-container']",
                                        singletonList(
                                                By.xpath("//div[text()='Goal']/ancestor::div[@class='react-bs-table-container']")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                                ,
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                null
        ),
        USERS_PLAYED_SMART_WALK_THRUS_AND_SMART_WALK_THRU_PLAYS_AND_MAIN_GOALS_REACHED(
                "Users Played Smart Walk-Thrus and Smart Walk-Thru Plays and Main Goals Reached",
                By.xpath("//div[@class='walkme-app-highlights walkme-app-highlights__triple-mode']"),
                asList(
                        By.xpath("//div[@class='walkme-app-highlight__title' and text()='Users Played Smart Walk-Thrus']"),
                        By.xpath("//div[@class='walkme-app-highlight__title' and text()='Smart Walk-Thru Plays']"),
                        By.xpath("//div[@class='walkme-app-highlight__title' and text()='Main Goals Reached']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[contains(@data-tip,'The total count of users who played Smart Walk-Thrus')]",
                                        asList(
                                                By.xpath("//div[contains(@data-tip,'The total count of users who played Smart Walk-Thrus')]"),
                                                By.xpath("//div[contains(@data-tip,'The total count of Smart Walk-Thru plays')]"),
                                                By.xpath("//div[contains(@data-tip,'The total count of Smart Walk-Thru plays that resulted')]"),
                                                By.xpath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'total users')]/ancestor::div[@class='walkme-app-highlight__subtitle']"),
                                                By.xpath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'plays per user')]/ancestor::div[@class='walkme-app-highlight__subtitle']"),
                                                By.xpath("//div[@class='walkme-app-highlight__subtitle-text' and contains(text(), 'plays resulted in')]/ancestor::div[@class='walkme-app-highlight__subtitle']")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                                ,
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                null
        ),
        SMART_WALK_THRUS_SUMMARY(
                "Smart Walk-Thrus Summary",
                By.xpath("//div[@class='insights-table__container']"),
                asList(
                        By.xpath("//div[@class='insights-table__header']"),
                        By.xpath("//div[@class='insights-table__title' and text()='Smart Walk-Thrus Summary']"),
                        By.xpath("//div[@class='table-search-bar table-search-bar--blurred']")
                ),
                new WidgetBodyInstructions(
                        asList(
                                new WidgetBodyStateInstructions(
                                        "Success",
                                        "//div[@class='react-bs-table-container']",
                                        asList(
                                                By.xpath("//div[@class='react-bs-table-container']"),
                                                By.xpath("//div[@class='reports-table__pagination-container']"),
                                                By.xpath("//div[@class='reports-table__pagination-text']"),
                                                By.xpath("//div[@class='reports-table__pagination']")
                                        ),
                                        true
                                ),
                                new WidgetBodyStateInstructions(
                                        "No Data",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                                ,
                                new WidgetBodyStateInstructions(
                                        "Error",
                                        null,
                                        asList(

                                        ),
                                        false
                                )
                        )
                ),
                null
        );

        @Getter
        private String name;

        @Getter
        private By borderLocator;

        @Getter
        private List<By> titleLocators;

        @Getter
        private WidgetBodyInstructions widgetBodyInstructions;

        @Getter
        private ViewStateChangeInstructions viewStateChangeInstructions;
    }
