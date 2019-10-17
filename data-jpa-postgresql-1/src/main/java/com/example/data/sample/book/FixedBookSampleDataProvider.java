package com.example.data.sample.book;

import com.example.model.book.Book;
import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.example.model.book.Book.Format.*;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Profile("book_fixed_sample_data_provider")
@Service
public class FixedBookSampleDataProvider extends BookSampleDataProvider {

    @Override
    public List<Book> getSampleItems() {
        List<Language> loadedLanguages = getDependencies().get(Language.class);
        List<Keyword> loadedKeywords = getDependencies().get(Keyword.class);
        return asList(
                Book.builder()
                        .title("Java")
                        .datePublished(LocalDate.of(2005, 6, 12))
                        .numberOfPages(278)
                        .familyFriendly(true)
                        .isbn("ISBN 978-0-596-52068-7")
                        .format(AUDIO_BOOK)
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .keywords(generateKeywordsSet(loadedKeywords))
                        .build(),
                Book.builder()
                        .title("NodeJS")
                        .datePublished(LocalDate.of(2016, 3, 9))
                        .numberOfPages(193)
                        .familyFriendly(true)
                        .isbn("0-596-52068-9")
                        .format(HARD_COVER)
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .keywords(generateKeywordsSet(loadedKeywords))
                        .build(),
                Book.builder()
                        .title("Python")
                        .datePublished(LocalDate.of(2003, 8, 25))
                        .numberOfPages(546)
                        .familyFriendly(false)
                        .isbn("ISBN-10: 0-596-52068-9")
                        .format(PAPER_BACK)
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .keywords(generateKeywordsSet(loadedKeywords))
                        .build()
        );
    }

    @Override
    public Book getSampleItem() {
        List<Language> loadedLanguages = getDependencies().get(Language.class);
        List<Keyword> loadedKeywords = getDependencies().get(Keyword.class);
        return Book.builder()
                .title("Java")
                .datePublished(LocalDate.of(2005, 6, 12))
                .numberOfPages(278)
                .familyFriendly(true)
                .isbn("ISBN 978-0-596-52068-7")
                .format(AUDIO_BOOK)
                .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                .keywords(generateKeywordsSet(loadedKeywords))
                .build();
    }

    private Set<Keyword> generateKeywordsSet(List<Keyword> loadedKeywords) { //todo
        Set<Keyword> result = new HashSet<>();
        result.add(loadedKeywords.get(0));
        result.add(loadedKeywords.get(1));
        result.add(loadedKeywords.get(2));
        result.add(loadedKeywords.get(3));
        return result;
    }
}
