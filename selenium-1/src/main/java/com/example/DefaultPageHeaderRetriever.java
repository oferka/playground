package com.example;

public class DefaultPageHeaderRetriever extends PageHeaderRetrieverByClassAndText {

    private static final String CLASS_NAME = "report-header__page-name";

    public DefaultPageHeaderRetriever(String text) {
        super(CLASS_NAME, text);
    }
}
