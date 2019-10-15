package com.example.data.sample;

import com.example.data.sample.book.BookSampleDataLoader;
import com.example.data.sample.keyword.KeywordSampleDataLoader;
import com.example.data.sample.language.LanguageSampleDataLoader;
import com.example.model.book.Book;
import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Data
public class SampleDataLoaderImpl implements SampleDataLoader {

    private LanguageSampleDataLoader languageSampleDataLoader;
    private KeywordSampleDataLoader keywordSampleDataLoader;
    private BookSampleDataLoader bookSampleDataLoader;

    public SampleDataLoaderImpl(LanguageSampleDataLoader languageSampleDataLoader, KeywordSampleDataLoader keywordSampleDataLoader, BookSampleDataLoader bookSampleDataLoader) {
        this.languageSampleDataLoader = languageSampleDataLoader;
        this.keywordSampleDataLoader = keywordSampleDataLoader;
        this.bookSampleDataLoader = bookSampleDataLoader;
    }

    @Override
    public void load() {
        log.info("Sample data load - started");
        List<Language> loadedLanguages = languageSampleDataLoader.load();
        List<Keyword> loadedKeywords = keywordSampleDataLoader.load(loadedLanguages);
        List<Book> loadedBooks = bookSampleDataLoader.load(loadedLanguages);
        log.info("Sample data load - completed");
    }

    @Override
    public void clean() {
        log.info("Sample data clean - started");
        bookSampleDataLoader.clean();
        keywordSampleDataLoader.clean();
        languageSampleDataLoader.clean();
        log.info("Sample data clean - completed");
    }
}
