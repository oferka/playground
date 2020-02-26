package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.Arrays.asList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Widget {

    private String name;

    private List<WidgetTitleRetriever> widgetTitleRetrievers;

    public Widget(String name, WidgetTitleRetriever widgetTitleRetriever) {
        this.name = name;
        widgetTitleRetrievers = asList(widgetTitleRetriever);
    }
}
