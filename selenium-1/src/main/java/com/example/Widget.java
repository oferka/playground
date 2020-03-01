package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Widget {

    private String name;

    private WidgetBorderRetriever widgetBorderRetriever;

    private List<WidgetTitleRetriever> widgetTitleRetrievers;

    private List<WidgetBodyRetriever> widgetBodyRetrievers;
}
