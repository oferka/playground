package com.example.data.sample.book;

import com.example.model.book.Book;
import com.example.model.language.Language;

import java.util.List;

public interface BookSampleDataProvider {

    List<Book> getSampleItems(List<Language> loadedLanguages);

    Book getSampleItem();
}
