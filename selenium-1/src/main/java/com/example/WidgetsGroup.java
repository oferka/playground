package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WidgetsGroup {

    private List<Widget> widgets;

    private PostObservationAction postObservationAction;
}
