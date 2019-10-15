package com.example;

import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class StreamTest {

    @Test
    public void testMapAndCollect() {
        final var items = List.of("Hila", "Maayan", "Yair");
        final List itemsUpper = items.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toUnmodifiableList());
        assertTrue(itemsUpper.equals(List.of("HILA", "MAAYAN", "YAIR")));
    }

}
