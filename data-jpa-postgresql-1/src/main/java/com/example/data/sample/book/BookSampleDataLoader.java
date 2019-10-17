package com.example.data.sample.book;

import com.example.model.book.Book;
import com.example.model.keyword.Keyword;
import com.example.model.language.Language;

import java.util.List;

public interface BookSampleDataLoader {

    BookSampleDataProvider getBookSampleDataProvider();

    List<Book> load(List<Language> loadedLanguages, List<Keyword> loadedKeywords);

    void clean();
}
