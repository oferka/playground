package com.example;

public class FirstLevelNavigationElementRetriever extends NavigationElementRetrieverByClassAndText {

    private static final String CLASS_NAME = "side-navbar__button__text";

    public FirstLevelNavigationElementRetriever(String text) {
        super(CLASS_NAME, text);
    }
}
