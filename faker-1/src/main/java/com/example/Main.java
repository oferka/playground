package com.example;

import com.github.javafaker.Faker;

public class Main {

    public static void main(String[] args) {
        Faker faker = new Faker();
        String name = faker.name().fullName();
        System.out.println("name: " + name);
        String firstName = faker.name().firstName();
        System.out.println("first name: " + firstName);
        String lastName = faker.name().lastName();
        System.out.println("lastName: " + lastName);
        String streetAddress = faker.address().streetAddress();
        System.out.println("streetAddress: " + streetAddress);
    }
}
