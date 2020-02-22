package com.example;

public class InsightsSecondLevelNavigationElementRetriever extends NavigationElementRetrieverByClassAndText {

    private static final String CLASS_NAME = "side-navbar__sub-item-button__text";

    public InsightsSecondLevelNavigationElementRetriever(String text) {
        super(CLASS_NAME, text);
    }
}
