package com.example.data.sample.book;

import com.example.model.book.Book;
import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import com.example.repository.book.BookRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@Data
public class BookSampleDataLoaderImpl implements BookSampleDataLoader {

    private BookSampleDataProvider bookSampleDataProvider;

    private BookRepository bookRepository;

    public BookSampleDataLoaderImpl(BookSampleDataProvider bookSampleDataProvider, BookRepository bookRepository) {
        this.bookSampleDataProvider = bookSampleDataProvider;
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> load(List<Language> loadedLanguages, List<Keyword> loadedKeywords) {
        log.info("Sample data load - books load - started");
        Map<Class, List> dependencies = new HashMap<>();
        dependencies.put(Language.class, loadedLanguages);
        dependencies.put(Keyword.class, loadedKeywords);
        bookSampleDataProvider.setDependencies(dependencies);
        List<Book> itemsToBeLoaded = bookSampleDataProvider.getSampleItems();
        List<Book> loadedItems = bookRepository.saveAll(itemsToBeLoaded);
        log.info("Sample data load - books load - loaded {} books", loadedItems.size());
        log.info("Sample data load - books load - completed");
        return loadedItems;
    }

    @Override
    public void clean() {
        log.info("Sample data clean - books clean - started");
        bookRepository.deleteAll();
        log.info("Sample data clean - books clean - completed");
    }
}
