package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum InsightsNavigationBarElementGroups {

    APPS_NAVIGATION_ELEMET_GROUP(
            "Apps",
            new InsightsFirstLevelNavigationElementRetriever("apps")
    ),
    TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP(
            "Tracked Events",
            new InsightsFirstLevelNavigationElementRetriever("tracked events")
    );

    @Getter
    private String name;

    @Getter
    private NavigationElementRetriever navigationElementRetriever;
}
