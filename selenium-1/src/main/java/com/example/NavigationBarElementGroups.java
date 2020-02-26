package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@ToString
@AllArgsConstructor
public enum NavigationBarElementGroups {

    APPS_NAVIGATION_ELEMET_GROUP(
            "Apps",
            new FirstLevelNavigationElementRetriever("apps")
    ),
    TRACKED_EVENTS_NAVIGATION_ELEMET_GROUP(
            "Tracked Events",
            new FirstLevelNavigationElementRetriever("tracked events")
    );

    @Getter
    private String name;

    @Getter
    private NavigationElementRetriever navigationElementRetriever;
}
