package com.example.data.sample;

import com.example.data.sample.book.BookSampleDataLoader;
import com.example.data.sample.keyword.KeywordSampleDataLoader;
import com.example.data.sample.language.LanguageSampleDataLoader;

public interface SampleDataLoader {

    LanguageSampleDataLoader getLanguageSampleDataLoader();

    KeywordSampleDataLoader getKeywordSampleDataLoader();

    BookSampleDataLoader getBookSampleDataLoader();

    void load();

    void clean();
}
