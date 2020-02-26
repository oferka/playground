package com.example;

public class SecondLevelNavigationElementRetriever extends NavigationElementRetrieverByClassAndText {

    private static final String CLASS_NAME = "side-navbar__sub-item-button__text";

    public SecondLevelNavigationElementRetriever(String text) {
        super(CLASS_NAME, text);
    }
}
