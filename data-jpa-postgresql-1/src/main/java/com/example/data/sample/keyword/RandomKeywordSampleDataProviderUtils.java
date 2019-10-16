package com.example.data.sample.keyword;

import com.example.model.keyword.Keyword;
import com.example.model.language.Language;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.List;

import static org.apache.commons.lang3.RandomUtils.nextInt;

public class RandomKeywordSampleDataProviderUtils {

    public static Keyword generateRandomKeyword(List<Language> loadedLanguages) {
        return Keyword.builder()
                .text(generateRandomKeywordText())
                .dateDefined(generateRandomKeywordDateDefined())
                .language(loadedLanguages.get(nextInt(0, loadedLanguages.size())))
                .build();
    }

    private static String generateRandomKeywordText() {
        return new Faker().hacker().noun();
    }

    private static LocalDate generateRandomKeywordDateDefined() {
        return LocalDate.now().minusDays(nextInt(0, 10000));
    }
}
