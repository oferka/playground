package com.example.data.sample.book;

import com.example.model.book.Book;
import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.example.data.sample.book.RandomBookSampleDataProviderUtils.generateRandomBook;

@Profile({"book_random_sample_data_provider", "default"})
@Service
public class RandomBookSampleDataProvider extends BookSampleDataProvider {

    @Override
    public List<Book> getSampleItems() {
        List<Book> books = new ArrayList<>();
        for(int i=0; i<100; i++) {
            books.add(generateRandomBook(getDependencies().get(Language.class), getDependencies().get(Keyword.class)));
        }
        return books;
    }

    @Override
    public Book getSampleItem() {
        return generateRandomBook(getDependencies().get(Language.class), getDependencies().get(Keyword.class));
    }
}
