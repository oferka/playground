package com.example;

public class InsightsFirstLevelNavigationElementRetriever extends NavigationElementRetrieverByClassAndText {

    private static final String CLASS_NAME = "side-navbar__button__text";

    public InsightsFirstLevelNavigationElementRetriever(String text) {
        super(CLASS_NAME, text);
    }
}
