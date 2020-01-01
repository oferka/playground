package com.example;

import static java.lang.String.format;

public class GreetingService {

    public String greeting(String name) {
        return format("Hello %s", name);
    }
}
