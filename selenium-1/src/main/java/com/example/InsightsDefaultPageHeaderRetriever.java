package com.example;

public class InsightsDefaultPageHeaderRetriever extends PageHeaderRetrieverByClassAndText {

    private static final String CLASS_NAME = "report-header__page-name";

    public InsightsDefaultPageHeaderRetriever(String text) {
        super(CLASS_NAME, text);
    }
}
