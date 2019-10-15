package com.example.data.sample.book;

import com.example.model.book.Book;
import com.example.model.language.Language;
import com.example.repository.book.BookRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<Book> load(List<Language> loadedLanguages) {
        log.info("Sample data load - books load - started");
        List<Book> itemsToBeLoaded = bookSampleDataProvider.getSampleItems(loadedLanguages);
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
