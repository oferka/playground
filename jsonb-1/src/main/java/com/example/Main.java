package com.example;

import javax.json.bind.Jsonb;
import javax.json.bind.JsonbBuilder;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Main {

    public static void main(String[] args) {
        mapToAndFromJSon();
        mapToAndFromJSonWithAttributeMappingConfiguration();
    }

    private static void mapToAndFromJSon() {
        Book book = new Book("Java 11", LocalDate.now(), 1, false, "Duke", new BigDecimal(44.444));
        Jsonb jsonb = JsonbBuilder.create();
        String resultJson = jsonb.toJson(book);
        Book fromJson = jsonb.fromJson(resultJson, Book.class);
        assert(book.equals(fromJson));
    }

    private static void mapToAndFromJSonWithAttributeMappingConfiguration() {
        Dog dog = new Dog("Milki", 1, true, null, LocalDate.now().minus(7, ChronoUnit.MONTHS), new BigDecimal(3500));
        Jsonb jsonb = JsonbBuilder.create();
        String resultJson = jsonb.toJson(dog);
        Dog fromJson = jsonb.fromJson(resultJson, Dog.class);
        assert(dog.equals(fromJson));
    }
}
