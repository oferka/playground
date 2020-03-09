package com.example;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
public class TimePeriodInstructions {

    @Getter
    private List<String> include;

    @Getter
    private List<String> exclude;
}
