package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

import static com.example.PageScrollInstructions.ScrollDirections.DOWN;
import static com.example.PageScrollInstructions.ScrollDirections.UP;
import static com.example.PageScrollInstructions.ScrollSpeeds.MEDIUM;
import static com.example.Widgets.*;
import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

@ToString
@AllArgsConstructor
public enum WidgetGroups {

    OVERVIEW_PAGE_GROUP_1(
            "Overview Page Group 1",
            singletonList(
                    UNIQUE_USER_AND_SESSIONS
            ),
            new PageScrollInstructions(DOWN, 300, MEDIUM)
    ),
    OVERVIEW_PAGE_GROUP_2(
            "Overview Page Group 2",
            asList(
                    WHERE_ARE_USERS_ACCESSING_YOUR_SITE_FROM,
                    SESSIONS_DURATION_BREAKDOWN
            ),
            new PageScrollInstructions(DOWN, 600, MEDIUM)
    ),
    OVERVIEW_PAGE_GROUP_3(
            "Overview Page Group 3",
            asList(
                    OVERVIEW_TOP_WALKME_ITEMS,
                    OVERVIEW_TOP_GOALS_REACHED
            ),
            new PageScrollInstructions(UP, 900, MEDIUM)
    ),
    APPS_OVERVIEW_PAGE_GROUP_1(
            "Apps Overview Page Group 1",
            singletonList(
                    USERS_WALKME_WAS_AVAILABLE_TO_AND_USERS_WHO_INTERACTED_AND_AVG_USER_INTERACTION
            ),
            new PageScrollInstructions(DOWN, 500, MEDIUM)
    ),
    APPS_OVERVIEW_PAGE_GROUP_2(
            "Apps Overview Page Group 2",
            singletonList(
                    WALKME_USAGE_BY_ITEM_TYPE
            ),
            new PageScrollInstructions(DOWN, 600, MEDIUM)
    ),
    APPS_OVERVIEW_PAGE_GROUP_3(
            "Apps Overview Page Group 3",
            asList(
                    APPS_OVERVIEW_TOP_WALKME_ITEMS,
                    APPS_OVERVIEW_TOP_GOALS_REACHED
            ),
            new PageScrollInstructions(UP, 1100, MEDIUM)
    ),
    SMART_WALK_THRUS_GROUP_1(
            "SmartWalkThrus Group 1",
            singletonList(
                    USERS_PLAYED_SMART_WALK_THRUS_AND_SMART_WALK_THRU_PLAYS_AND_MAIN_GOALS_REACHED
            ),
            new PageScrollInstructions(DOWN, 600, MEDIUM)
    ),
    SMART_WALK_THRUS_GROUP_2(
            "SmartWalkThrus Group 2",
            singletonList(
                    SMART_WALK_THRUS_SUMMARY
            ),
            new PageScrollInstructions(UP, 600, MEDIUM)
    );

    @Getter
    private String name;

    @Getter
    private List<Widgets> widgets;

    @Getter
    private PageScrollInstructions pageScrollInstructions;
}
