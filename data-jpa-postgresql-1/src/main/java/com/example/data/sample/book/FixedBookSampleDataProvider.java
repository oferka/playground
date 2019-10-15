package com.example.data.sample.book;

import com.example.model.book.Book;
import com.example.model.language.Language;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static com.example.model.book.Book.Format.*;
import static java.util.Arrays.asList;
import static org.apache.commons.lang3.RandomUtils.nextInt;

@Profile("book_fixed_sample_data_provider")
@Service
public class FixedBookSampleDataProvider extends BookSampleDataProvider {

    @Override
    public List<Book> getSampleItems() {
        List<Language> loadedLanguages = getDependencies().get(Language.class);
        return asList(
                Book.builder()
                        .title("Java")
                        .datePublished(LocalDate.of(2005, 6, 12))
                        .numberOfPages(278)
                        .familyFriendly(true)
                        .isbn("ISBN 978-0-596-52068-7")
                        .format(AUDIO_BOOK)
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .build(),
                Book.builder()
                        .title("NodeJS")
                        .datePublished(LocalDate.of(2016, 3, 9))
                        .numberOfPages(193)
                        .familyFriendly(true)
                        .isbn("0-596-52068-9")
                        .format(HARD_COVER)
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .build(),
                Book.builder()
                        .title("Python")
                        .datePublished(LocalDate.of(2003, 8, 25))
                        .numberOfPages(546)
                        .familyFriendly(false)
                        .isbn("ISBN-10: 0-596-52068-9")
                        .format(PAPER_BACK)
                        .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                        .build()
        );
    }

    @Override
    public Book getSampleItem() {
        List<Language> loadedLanguages = getDependencies().get(Language.class);
        return Book.builder()
                .title("Java")
                .datePublished(LocalDate.of(2005, 6, 12))
                .numberOfPages(278)
                .familyFriendly(true)
                .isbn("ISBN 978-0-596-52068-7")
                .format(AUDIO_BOOK)
                .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                .build();
    }
}
