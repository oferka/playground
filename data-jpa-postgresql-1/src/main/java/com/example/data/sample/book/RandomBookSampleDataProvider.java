package com.example.data.sample.book;

import com.example.model.book.Book;
import com.example.model.book.Book.Format;
import com.example.model.language.Language;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.example.model.book.Book.NUMBER_OF_PAGES_MAX;
import static com.example.model.book.Book.NUMBER_OF_PAGES_MIN;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Profile({"book_random_sample_data_provider", "default"})
@Service
public class RandomBookSampleDataProvider extends BookSampleDataProvider {

    @Override
    public List<Book> getSampleItems() {
        List<Language> loadedLanguages = getDependencies().get(Language.class);
        List<Book> books = new ArrayList<>();
        for(int i=0; i<100; i++) {
            books.add(generateRandomBook());
        }
        return books;
    }

    @Override
    public Book getSampleItem() {
        return generateRandomBook();
    }

    public Book generateRandomBook() {
        List<Language> loadedLanguages = getDependencies().get(Language.class);
        return Book.builder()
                .title(generateRandomBookTitle())
                .datePublished(generateRandomBookDatePublished())
                .numberOfPages(generateRandomBookNumberOfPages())
                .familyFriendly(generateRandomBookFamilyFriendly())
                .isbn(generateRandomBookIsbn())
                .format(generateRandomBookFormat())
                .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                .build();
    }

    private static String generateRandomBookTitle() {
        return new Faker().book().title();
    }

    public static LocalDate generateRandomBookDatePublished() {
        return LocalDate.now().minusDays(nextInt(0, 10000));
    }

    public static int generateRandomBookNumberOfPages() {
        return nextInt(NUMBER_OF_PAGES_MIN, NUMBER_OF_PAGES_MAX);
    }

    public static boolean generateRandomBookFamilyFriendly() {
        return nextBoolean();
    }

    public static String generateRandomBookIsbn() {
        return (nextBoolean()? (new Faker().code().isbn10(nextBoolean())) : (new Faker().code().isbn13(nextBoolean())));
    }

    private static Format generateRandomBookFormat() {
        Format[] formats = Format.values();
        return formats[nextInt(0, formats.length)];
    }
}
