package com.example;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ListTest {

    @Test
    public void givenArraysAsList_thenInitialiseList() {
        List<String> list = Arrays.asList("foo", "bar");
        assertTrue(list.contains("foo"));
    }

    @Test(expected = UnsupportedOperationException.class)
    public void givenArraysAsList_whenAdd_thenUnsupportedException() {
        List<String> list = Arrays.asList("foo", "bar");
        list.add("baz");
    }

    @Test
    public void givenArraysAsList_whenCreated_thenShareReference(){
        String[] array = {"foo", "bar"};
        List<String> list = Arrays.asList(array);
        array[0] = "baz";
        assertEquals("baz", list.get(0));
    }

    @Test
    public void givenStream_thenInitializeList(){
        List<String> list = Stream.of("foo", "bar")
                .collect(Collectors.toList());
        assertTrue(list.contains("foo"));
    }

    @Test
    public void factoryMethod_thenInitializeList(){
        List<String> list = List.of("foo", "bar", "baz");
        assertEquals(list.size(), 3);
        Set<String> set = Set.of("foo", "bar", "baz");
        assertEquals(set.size(), 3);
    }
}
