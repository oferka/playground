package com.example.data.sample.book;

import com.example.model.book.Book;
import com.example.model.book.Format;
import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.model.book.Book.NUMBER_OF_PAGES_MAX;
import static com.example.model.book.Book.NUMBER_OF_PAGES_MIN;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public class RandomBookSampleDataProviderUtils {

    public static Book generateRandomBook(List<Language> loadedLanguages, List<Keyword> loadedKeywords) {
        return Book.builder()
                .title(generateRandomBookTitle())
                .datePublished(generateRandomBookDatePublished())
                .numberOfPages(generateRandomBookNumberOfPages())
                .familyFriendly(generateRandomBookFamilyFriendly())
                .isbn(generateRandomBookIsbn())
                .format(generateRandomBookFormat())
                .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                .keywords(generateKeywordsSet(loadedKeywords))
                .build();
    }

    private static String generateRandomBookTitle() {
        return new Faker().book().title();
    }

    private static LocalDate generateRandomBookDatePublished() {
        return LocalDate.now().minusDays(nextInt(0, 10000));
    }

    private static int generateRandomBookNumberOfPages() {
        return nextInt(NUMBER_OF_PAGES_MIN, NUMBER_OF_PAGES_MAX);
    }

    private static boolean generateRandomBookFamilyFriendly() {
        return nextBoolean();
    }

    private static String generateRandomBookIsbn() {
        return (nextBoolean()? (new Faker().code().isbn10(nextBoolean())) : (new Faker().code().isbn13(nextBoolean())));
    }

    private static Format generateRandomBookFormat() {
        Format[] formats = Format.values();
        return formats[nextInt(0, formats.length)];
    }

    private static Set<Keyword> generateKeywordsSet(List<Keyword> loadedKeywords) { //todo
        Set<Keyword> result = new HashSet<>();
        result.add(loadedKeywords.get(0));
        result.add(loadedKeywords.get(1));
        result.add(loadedKeywords.get(2));
        result.add(loadedKeywords.get(3));
        return result;
    }
}
