package com.example;

public interface BookRepository {

    Book getByIsbn(String isbn);
}
